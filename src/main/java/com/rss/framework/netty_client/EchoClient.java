package com.rss.framework.netty_client;

import com.rss.framework.ConnectIP;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 客户端ERP
 *
 * @author Yu Tongxin
 * @date 2019/04/5
 */
public class EchoClient {
    // private final static String HOST = "localhost";
    //  private final static int PORT = 12306;
    private String IP;
    private int PORT;
    private ClientBusiness clientBusiness;
    //  private String controlSystem;
    public static ClientHandler cH;
    private static EchoClient echoClient = null;


    private EchoClient(String IP, int PORT, ClientBusiness clientBusiness) {
        // this.controlSystem = controlSystem;
        this.clientBusiness = clientBusiness;
        this.IP = IP;
        this.PORT = PORT;
    }

    public static EchoClient getInstance() {
        if (echoClient == null) {
            synchronized (EchoClient.class) {
                if (echoClient == null) {
                    ConnectIP connectIP = (ConnectIP) ApplicationContextHelper.getBean("connectIP");
                    echoClient = new EchoClient(connectIP.getIP(), connectIP.getPort(), new CommonBusiness());
                }
            }
        }
        return echoClient;

    }

    void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(IP, PORT))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new MyMessageDecoder());
                        cH = new ClientHandler(clientBusiness);
                        socketChannel.pipeline().addLast(cH);
                    }
                });
        try {
            ChannelFuture f = bootstrap.connect().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(String message) {
        cH.sendToch(message);
    }
}

