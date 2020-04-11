package com.github.weilunz.beans;

import com.github.weilunz.web.mvc.Controller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: mini-spring
 * @description:
 * @author: wl.zhou
 * @create: 2020-04-11 21:28
 **/
public class BeanFactory {
    private static Map<Class<?>, Object> classToBean = new ConcurrentHashMap<>();

    public static Object getBean(Class<?> cls){
        return classToBean.get(cls);
    }

    public static void initBean(List<Class<?>> classList) throws Exception {
        List<Class<?>> toCreate = new ArrayList<>(classList);
        while (toCreate.size() != 0){
            int remainSize = toCreate.size();
            Iterator<Class<?>> iter = toCreate.iterator();
            while (iter.hasNext()){
                if (finishCreate(iter.next())){
                    iter.remove();
                }
            }
            if (toCreate.size() == remainSize){
                throw new Exception("cycle dependency");
            }
        }
    }

    private static boolean finishCreate(Class<?> cls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (!cls.isAnnotationPresent(Bean.class) && !cls.isAnnotationPresent(Controller.class)){
            return true;
        }
        Object bean = cls.getDeclaredConstructor().newInstance();
        for (Field field : cls.getDeclaredFields()){
            if (field.isAnnotationPresent(Autowired.class)){
                Class<?> fieldType = field.getType();
                Object reliantBean = BeanFactory.getBean(fieldType);
                if (reliantBean == null){
                    return false;
                }
                field.setAccessible(true);
                field.set(bean, reliantBean);
            }
        }
        classToBean.put(cls, bean);
        return true;
    }
}
