package com.rss.framework.netty_client;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
 public class ApplicationContextHelper implements ApplicationContextAware, InitializingBean {
    private static ApplicationContext appCtx;

    /**
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
     *
     * @param applicationContext ApplicationContext 对象.
     * @throws BeansException
     * @author wangdf
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }

    /**
     * 获取ApplicationContext
     *
     * @return
     * @author wangdf
     */
    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     *
     * @param beanName bean的名字
     * @return 返回一个bean对象
     * @author wangdf
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
                String[] names = appCtx.getBeanDefinitionNames();
                 for (String name : names) {
                         System.out.println(">>>>>>" + name);
                     }
                System.out.println("------\nBean 总计:" + appCtx.getBeanDefinitionCount());
             }


}
