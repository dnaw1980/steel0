package com.rss.framework.netty_client;

import com.rss.framework.netty_client.framework.business.BusinessObjectEntity;
import io.netty.channel.ChannelHandlerContext;

public interface ClientBusiness {
    /**
     * 执行客户端的业务
     *
     * @param businessObjectEntity 信息实体
     * @param channelHandlerContext
     */
    void execute(ChannelHandlerContext channelHandlerContext, BusinessObjectEntity businessObjectEntity);
    String firstMessage(String type);
}
