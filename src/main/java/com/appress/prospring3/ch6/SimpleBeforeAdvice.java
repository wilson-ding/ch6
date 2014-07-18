package com.appress.prospring3.ch6;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		
		System.out.println("Before method: " + method.getName());
	}

	public static void main(String[] args) {

		MessageWriter target=new MessageWriter();
		ProxyFactory pf=new ProxyFactory();
		pf.addAdvice(new SimpleBeforeAdvice());
		pf.setTarget(target);
		
		MessageWriter proxy=(MessageWriter) pf.getProxy();
		proxy.writeMessage();
	}

}
