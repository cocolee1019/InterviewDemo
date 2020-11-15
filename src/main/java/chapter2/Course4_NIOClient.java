package chapter2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * 阻塞式客户端和非阻塞客户端示例
 */
public class Course4_NIOClient {

    //客户端
    @Test
    public void client() throws IOException {
        final SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        channel.configureBlocking(false);
        final ByteBuffer by = ByteBuffer.allocate(1024);
        by.put(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE).getBytes());
        by.flip();
        channel.write(by);
        by.clear();
        channel.close();
    }

    //服务端
    @Test
    public void nonBlockServer() throws IOException {
        //1、获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2、切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3、绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //4、获取选择器
        Selector selector = Selector.open();

        //5、将通道注册到选择器，并且指定“监听器”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6、轮询获取选择器上已经准备就绪的事件
        int i;
        while (selector.select() > 0) {
            //7、获取所有事件的key
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8、获取已“就绪”的事件
                SelectionKey key = it.next();
                //9、判断事件
                if (key.isAcceptable()) {
                    //10、若“接收就绪”， 获取客户端连接
                    SocketChannel socketChannel = ssChannel.accept();
                    //11、切换到非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12、将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //13、获取当前选择器上的“读就绪”通道
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //14、读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                //15、取消选择键
                it.remove();
            }
        }
    }

    @Test
    public void blockServer() throws IOException {

        //1、获取socket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2、绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));

        //3、接收
        SocketChannel accept = serverSocketChannel.accept();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int l;
        while ((l = accept.write(buffer)) > 0) {
            System.out.println(new String(buffer.array(), 0, l));
        }

        accept.close();
        serverSocketChannel.close();
    }
}
