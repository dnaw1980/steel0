package com.rss.framework.netty_client;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * tomcat启动之后监听器执行方法启动echolient

@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent arg0) {
            System.out.println("-----所有Bean载入完成---");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EchoClient.getInstance().start();
                }
            }).start();
        }
}
 */