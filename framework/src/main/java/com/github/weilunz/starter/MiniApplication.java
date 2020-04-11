package com.github.weilunz.starter;

import com.github.weilunz.beans.BeanFactory;
import com.github.weilunz.core.ClassScanner;
import com.github.weilunz.web.handler.HandlerManager;
import com.github.weilunz.web.server.TomcatServer;
import java.util.List;

/**
 * @program: mini-spring
 * @description:
 * @author: wl.zhou
 * @create: 2020-04-11 15:46
 **/
public class MiniApplication {
    public static void run(Class<?> cls, String[] args) {
        System.out.println("Hello mini-spring!");
        TomcatServer tomcatServer = new TomcatServer(args);
        try{
            tomcatServer.startServer();
            List<Class<?>> classList = ClassScanner.scanClass(cls.getPackage().getName());
            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach(it -> System.out.println(it.getName()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
