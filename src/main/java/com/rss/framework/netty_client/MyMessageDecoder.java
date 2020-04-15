package com.rss.framework.netty_client;

import com.rss.framework.netty_client.util.currentUse.Schedule;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yu Tongxin
 * @date 2018/11/14
 * ***
 * 自定义解码器:
 */

public class MyMessageDecoder extends ByteToMessageDecoder {
    /**
     * PATTERN消息处理，Pattern.compile(">(\\s*|\t|\r|\n)去除掉<></>之间的空格换行等
     * messageBufferMap消息缓存，可能多个消息叠加存在一起
     * byteBufferMap对发来的消息进行字节缓存，如果转换成String后中间没有</order></response>等等，
     * 则不要把他们解码，等待下一次的消息再作处理
     */
    private final Pattern PATTERN = Pattern.compile(">(\\s*|\t|\r|\n)<");
    private static ConcurrentHashMap<ChannelHandlerContext, StringBuffer> messageBufferMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<ChannelHandlerContext, byte[]> byteBufferMap = new ConcurrentHashMap<>();

    /**
     * 把两个字节数组连接到一起
     */
    private static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;

    }

    /**
     * 解码业务处理
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        if(byteBufferMap.get(ctx)!=null && byteBufferMap.get(ctx).length>0){
            bytes = addBytes(byteBufferMap.get(ctx),bytes);
        }

        String message= new String(bytes, StandardCharsets.UTF_8);
        //String message = in.toString(Charset.forName("utf-8"));
        Matcher m = PATTERN.matcher(message);
        message = m.replaceAll("><");

        if ((message.contains("</order>")
                || message.contains("</request>")
                || message.contains("</response>"))) {


            //String message = new String(bytes,"utf-8");
            System.out.println("MyMessageDecoder--接收消息时间：" + Schedule.parseLong(System.currentTimeMillis()) + "receive Message from: as follows \n"
                    + message);
            ArrayList<String> resultList = this.messagePreHandle(ctx, message);
            if (resultList.size() > 0) {
                for (String result : resultList) {
                    out.add(Unpooled.copiedBuffer(result, Charset.forName("utf-8")));
                }
            }
        } else {
            byteBufferMap.put(ctx,bytes);
            System.out.println("拒绝处理：报文不全"+message);
        }
        in.skipBytes(in.readableBytes());
    }

    /**
     * 对发来的消息进行预处理，与之前缓存的消息合并，并提取得到消息
     */
    private ArrayList<String> messagePreHandle(ChannelHandlerContext ctx, String message) {
        ArrayList<String> resultList = new ArrayList<>();
        if (messageBufferMap.get(ctx) == null) {
            StringBuffer sb = new StringBuffer();
            messageBufferMap.put(ctx, sb);
        }
        StringBuffer bufferMap = messageBufferMap.get(ctx).append(message);
        String temp = bufferMap.toString();

        String markRequest = "request";
        String markResponse = "response";
        String markOrder = "order";

        this.addResultList(bufferMap, markRequest, resultList);
        this.addResultList(bufferMap, markResponse, resultList);
        this.addResultList(bufferMap, markOrder, resultList);

        return resultList;
    }

    private void addResultList(StringBuffer bufferMap, String mark, ArrayList resultList) {
        String result = this.extractResult(bufferMap, mark);
        if (result != null) {
            resultList.add(result);
            this.addResultList(bufferMap, mark, resultList);
        }
        return;
    }

    /**
     * 对发来的消息进行提取得到<request(response)>**</request(response)>
     */
    private String extractResult(StringBuffer bufferMap, String mark) {
        String result = null;
        String pivot = "</" + mark + ">";
        if (bufferMap.length() == 0) {
            return null;
        }
        if (bufferMap.indexOf(pivot) > 0) {
            int endIndex = bufferMap.indexOf("</" + mark + ">") + ("</" + mark + ">").length();
            String subString = bufferMap.substring(0, endIndex);
            /*重点错误*/
            int startIndex = subString.lastIndexOf("<" + mark + " ");
            if (startIndex >= 0) {
                result = bufferMap.substring(startIndex, endIndex);
                bufferMap.delete(startIndex, endIndex);
                Matcher m = PATTERN.matcher(result);
                result = m.replaceAll("><");
            } else {
                bufferMap.delete(0, bufferMap.indexOf("</" + mark + ">"));
            }
        }
        /**
         * 粘包处理问题
         *
         if(bufferMap.indexOf("<request ")<0 && bufferMap.indexOf("<response ")<0
         && bufferMap.indexOf("<order")<0){
         System.out.println("bufferMapDELETE"+bufferMap.substring(0,bufferMap.length()));
         System.out.println("beforebufferMapDELETE"+bufferMap.toString());
         bufferMap.delete(0,bufferMap.length());
         }
         **/
        return result;
    }
}
