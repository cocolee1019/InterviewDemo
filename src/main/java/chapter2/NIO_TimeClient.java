package chapter2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author luwu
 * @date 7/20/23 16:49
 */
public class NIO_TimeClient {

    @Test
    public void server() throws IOException {
        // 用于监听客户端的连接，是所有客户端连接的父管道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 绑定监听端口
        socketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8888));
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 获取Selector轮询器
        Selector selector = Selector.open();
        // channel注册到selector;
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 调用select()方法会阻塞，直至有通道的事件已就绪或者被中断，
            // 下面方法使select每隔一秒被唤醒
            int num = selector.select(1000);
            if (num <= 0) {
                System.out.println("被唤醒检测");
                continue;
            }

            // select >= 1表示有连接进来
            // 获取已就绪的channel
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                // key是否有效;
                // key在创始时有效，直到key.cancel()、channel.closed()或者selector.closed()
                if (key.isValid()) {

                    // 测试此key的channel是否已准备好接受新的socket连接。
                    if (key.isAcceptable()) {
                        // 拿到该key的channel
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                        // 建立tcp连接，并获取客户端的socket。
                        // 问题1：ssc.accept()是否会阻塞？
                        // 答案：如果ServerSocketChannel是阻塞模式，则会阻塞，直到有新的连接进来。如果是非阻塞模式，并且当前没有连接，会立即返回null，有连接则返回当前连接。
                        SocketChannel clientChannel = ssc.accept();
                        clientChannel.configureBlocking(false);

                        // 将该连接注册到Selector中
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }

                    // 测试key的channel是否已准备好进行读取。
                    if (key.isReadable()) {
                        // 拿到该key的channel
                        SocketChannel clientChannel = (SocketChannel) key.channel();

                        // 使用buffer读取数据
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int byteNum = clientChannel.read(readBuffer);
                        if (byteNum > 0) {
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            System.out.println(new String(bytes));

                            // 向客户端写入数据
                            byte[] bytes1 = "10010101010".getBytes();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes1.length);
                            byteBuffer.put(bytes1);
                            byteBuffer.flip();
                            clientChannel.write(byteBuffer);
                        } else if (byteNum < 0) {
                            // 关闭通道
                            key.cancel();
                            clientChannel.close();
                        } else {
                            // 直接忽略
                        }

                    }
                }
            }


        }
    }

    @Test
    public void client() throws IOException {

        Selector selector = Selector.open();

        // 开启通道
        SocketChannel clientChannel = SocketChannel.open();
        // 设置为非阻塞
        clientChannel.configureBlocking(false);
        Socket configSocket = clientChannel.socket();
        configSocket.setReuseAddress(true);
        configSocket.setReceiveBufferSize(1024);
        configSocket.setSendBufferSize(1024);

        // 异步连接客户端
        boolean connect = clientChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
        if (!connect) {
            clientChannel.register(selector, SelectionKey.OP_CONNECT);
        }

        while (true) {
            // 开启select选择
            // 如果Socket未就绪，将阻塞
            selector.select();
            Iterator<SelectionKey> keys = selector.keys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
//                keys.remove();

                // 判断key是否可用
                if (key.isValid()) {
                    SocketChannel channel = (SocketChannel) key.channel();

                    // 连接就绪
                    if (key.isConnectable()) {
                        // 与服务端连接成功
                        if (channel.finishConnect()) {
                            // 如果连接成功
                            // 注册读取事件，用于读取服务端返回的消息
                            clientChannel.register(selector, SelectionKey.OP_READ);

                            // 向服务端发送数据
                            byte[] reqBody = "你好".getBytes();
                            ByteBuffer writeBuffer = ByteBuffer.allocate(reqBody.length);
                            writeBuffer.put(reqBody);
                            writeBuffer.flip();
                            clientChannel.write(writeBuffer);
                        }
                    }

                    // 读取就绪
                    if (key.isReadable()) {

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int read = channel.read(byteBuffer);
                        if (read > 0) {
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            System.out.println("服务端返回消息" + new String(bytes));
                        }
                    }

                    // 写入就绪
                    if (key.isWritable()) {
                        System.out.println("写入就绪");
                    }
                }

                key.cancel();
                if (key.channel() != null) {
                    key.channel().close();
                }
            }
        }
    }

}
