package com.origami.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author Origami
 * @description  通过类加载器或者resource的辅助类
 * @create 2025-02-25 14:18
 **/
public class Resources {

    /**
     * 将xml文件转换为 Reader 对象
     * @param resource 资源文件路径
     * @return Reader对象
     * @throws IOException
     */
    public static Reader getResourceAsReader(String resource) throws IOException {
        return new InputStreamReader(getResourceAsStream(resource));
    }

    /**
     * 通过类加载器加载资源文件 返回InputStream对象
     * @param resource 资源文件路径
     * @return InputStream对象
     * @throws IOException
     */
    public static InputStream getResourceAsStream(String resource) throws IOException{
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader:classLoaders){
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if (inputStream != null){
                return inputStream;
            }
        }
        throw new IOException("查找不到资源:"+resource);
    }


    /**
     * 返回两个类加载器 用于资源加载
     * @return
     */
    private static ClassLoader[] getClassLoaders() {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),   // 系统类加载器
                Thread.currentThread().getContextClassLoader() // 线程上下文类加载器
        };

    }

    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }


}
