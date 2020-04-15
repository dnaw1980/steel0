package com.rss.framework.netty_client;

import com.rss.framework.netty_client.framework.business.JavaBeanAdapter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private ClientBusiness clientBusiness;
    //private String controlSystem;

    private ChannelHandlerContext channelHandlerContext;

    public ClientHandler(ClientBusiness clientBusiness) {
        super();
        this.clientBusiness = clientBusiness;
      //  this.controlSystem = controlSystem;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当被通知Channel是活跃的时候，发送一条消息(可以理解成tomcat启动之后)
        ctx.writeAndFlush(Unpooled.copiedBuffer(clientBusiness.firstMessage("beatMessage"), CharsetUtil.UTF_8));
        this.channelHandlerContext = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
       // System.out.println("ClientHandler接收到服务器的消息时间：" + Schedule.parseLong(System.currentTimeMillis()) +
        //        "\n内容："+byteBuf.toString(Charset.forName("utf-8")));
        clientBusiness.execute(channelHandlerContext, JavaBeanAdapter.getBusinessObjectFromMessage(byteBuf.toString(Charset.forName("utf-8"))));
        this.channelHandlerContext = channelHandlerContext;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void sendToch(String message){
        this.channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(message,CharsetUtil.UTF_8));
    }
}
