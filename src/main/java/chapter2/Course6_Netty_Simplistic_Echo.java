package chapter2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty入门示例
 */
public class Course6_Netty_Simplistic_Echo {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new DiscardServer(port).run();
    }
}

/**
 * DiscardServerHandler扩展了ChannelInboundHandlerAdapter，它是ChannelInboundHandler的实现。
 * ChannelInboundHandler提供了可以覆盖的各种事件处理程序方法。目前，仅扩展ChannelInboundHandlerAdapter即可，而不是自己实现处理程序接口。
 */
class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 在这里重写channelRead()事件处理程序方法。每当从客户端接收到新数据时，就使用接收到的消息来调用此方法。在此示例中，接收到的消息的类型为ByteBuf。
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //为了实现DISCARD协议，处理程序必须忽略收到的消息。 ByteBuf是一个ReferenceCounte    d对象，必须通过release（）方法显式释放它。
        //释放任何传递给处理程序的引用计数对象是处理程序的责任。
        // Discard the received data silently.
        //((ByteBuf) msg).release();

        //为了证明服务正常工作，我们将打印此字符
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
//        } finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }
        ByteBuf in = (ByteBuf) msg;
        System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
        //实现一个echo服务
        ctx.write(msg);
        ctx.flush();
    }

    /**
     * 当Netty因I / O错误而引发异常，或者因处理事件时引发异常而由处理程序实现引发异常时，将使用Throwable调用exceptionCaught（）事件处理程序方法。
     * 在大多数情况下，应记录捕获的异常并在此处关闭其关联的通道，尽管此方法的实现可能会有所不同，具体取决于您要处理特殊情况时要采取的措施。
     * 例如，您可能想在关闭连接之前发送带有错误代码的响应消息。
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(111111111);
        cause.printStackTrace();
        ctx.close();
    }
}

class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //NioEventLoopGroup是处理I / O操作的多线程事件循环。Netty为不同类型的传输提供了各种EventLoopGroup实现。
        //在此示例中，我们正在实现服务器端应用程序，因此将使用两个NioEventLoopGroup。第一个通常称为“boss”，接受传入的连接。
        //第二个通常称为“worker”，一旦“boss”接受连接并将注册的连接注册给worker，便处理已接受连接的流量。
        //使用多少个线程以及如何将它们映射到创建的通道取决于EventLoopGroup实现，甚至可以通过构造函数进行配置
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //ServerBootstrap是设置服务器的帮助程序类。您可以直接使用频道设置服务器。
            //但是，请注意，这是一个单调乏味的过程，在大多数情况下，您无需这样做。
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    //指定使用NioServerSocketChannel类，该类用于实例化新的Channel来接受传入的连接。
                    .channel(NioServerSocketChannel.class) // (3)
                    //
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
