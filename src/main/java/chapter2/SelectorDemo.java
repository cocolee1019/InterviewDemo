package chapter2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class SelectorDemo {
	public static Map<Socket, Long> geym_time_stat = new HashMap<Socket, Long>(10240);

	static Selector selector;
	public static void main(String[] args) throws IOException {
		new SelectorDemo().startServer();
	}

	private void startServer() throws IOException {
		selector = SelectorProvider.provider().openSelector();
		ServerSocketChannel channel = ServerSocketChannel.open();
		
		//设置为非阻塞 
		channel.configureBlocking(false);
		
		InetSocketAddress isa = new InetSocketAddress(8000);
		channel.socket().bind(isa);
		
		//注册一个感兴趣的事件
		channel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true) {
			selector.select();
			Set<SelectionKey> readyKeys = selector.selectedKeys();
			
			Iterator i = readyKeys.iterator();
			while(i.hasNext()) {
				SelectionKey sk = (SelectionKey)i.next();
				i.remove();
				if(sk.isAcceptable()) {
					System.out.println("接收事件");
					doAccept(sk);
				}else if(sk.isValid() && sk.isReadable()) {
					System.out.println("读取事件");
				}else if(sk.isValid() && sk.isWritable()) {
					System.out.println("写入事件");
				}
			}
		}
	}
	
	private void doAccept(SelectionKey sk) {
		ServerSocketChannel server = (ServerSocketChannel)sk.channel();
		SocketChannel clientChannel;
		try {
			clientChannel = server.accept();
			clientChannel.configureBlocking(false);
			
			SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
			EchoClient client = new EchoClient();
			clientKey.attach(client);
			
			InetAddress clientAddress = clientChannel.socket().getInetAddress();
			System.out.println("从接收连接：" + clientAddress.getHostAddress());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doRead(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		ByteBuffer bb = ByteBuffer.allocate(1024);
		int len;
		try {
			len = channel.read(bb);
			if(len<0) {
				sk.cancel();
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
			sk.cancel();
			return;
		}
		bb.flip();
	}
	
	private void doWrite(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		EchoClient echoClient = (EchoClient) sk.attachment();
		LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
		
		ByteBuffer bb = outq.getLast();
		try {
			int len = channel.write(bb);
			if(len == -1) {
				//disconnect(sk);
				return;
			}
			
			if(bb.remaining() == 0) {
				outq.removeLast();
			}
		}catch (Exception e) {
			System.out.println("Faild to write to client.");
			e.printStackTrace();
			//disconnect(sk);
		}
	}
	
	class EchoClient {
		private LinkedList<ByteBuffer> outq;
		
		public EchoClient() {
			outq = new LinkedList<ByteBuffer>();
		}
		
		public LinkedList<ByteBuffer> getOutputQueue() {
			return outq;
		}
		
		public void enqueue(ByteBuffer bb) {
			outq.addFirst(bb);
		}
	}
	
	class HandleMsg implements Runnable {
		SelectionKey sk;
		ByteBuffer bb;
		public HandleMsg(SelectionKey sk, ByteBuffer bb) {
			this.sk = sk;
			this.bb = bb;
		}
		
		@Override
		public void run() {
			EchoClient echoClient = (EchoClient)sk.attachment();
			echoClient.enqueue(bb);
			sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			selector.wakeup();
		}
	}
}
