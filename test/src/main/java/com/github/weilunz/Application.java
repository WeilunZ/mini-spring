package com.github.weilunz;

import com.github.weilunz.starter.MiniApplication;

/**
 * @program: mini-spring
 * @description:
 * @author: wl.zhou
 * @create: 2020-04-11 15:30
 **/
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello World");
        MiniApplication.run(Application.class, args);
    }
}
