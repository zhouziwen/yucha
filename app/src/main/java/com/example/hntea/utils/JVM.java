package com.example.hnTea.utils;

import java.lang.reflect.Method;

/**
 * Created by 太能 on 2016/12/26.
 */
public class JVM {
    /**采用反射机制调用某个类的方法
     * @param methodOwner : 被调用方法的所有者
     * @param methodName ： 被调用的方法名
     * @param params ： 携带的参数
     * @return Object
     * @throws Exception
     */
    public static Object invoke(Object methodOwner, String methodName, Object[] params) throws Exception {

        Class<?> ownerClass = methodOwner.getClass();

        Class<?>[] argsClass = new Class<?>[params.length];

        for (int i = 0, len = params.length; i < len; i++) {
            argsClass[i] = params[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName,argsClass);

        return method.invoke(methodOwner, params);
    }

}
