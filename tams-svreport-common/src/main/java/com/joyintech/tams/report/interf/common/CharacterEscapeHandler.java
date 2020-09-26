/*
 * 文件名：CharacterEscapeHandler.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送系统
 * 修改人：何阳阳
 * 修改时间：2017年10月24日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common;

import java.io.Writer;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * 不进行转义字符处理的类<br>
 * 由于 com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler 是jdk私有API，在maven编译时不通过
 * 该类采用cglib创建代理类来避免编译错误
 * 
 * @author 何阳阳
 * @version 1.0 2017年10月24日
 * @see CharacterEscapeHandler
 * @since 1.0
 */
public class CharacterEscapeHandler implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getInstance()
        throws ClassNotFoundException {
        enhancer.setSuperclass(
            Class.forName("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler"));
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
        throws Throwable {
        if (args.length == 5) {
            char[] ch = (char[])args[0];
            int start = (int)args[1];
            int length = (int)args[2];
            // boolean isAttVal = (boolean)args[3];
            Writer writer = (Writer)args[4];
            writer.write(ch, start, length);
        }
        return Void.TYPE;
    }
}
