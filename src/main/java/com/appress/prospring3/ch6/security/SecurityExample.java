package com.appress.prospring3.ch6.security;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityExample {

	public static void main(String[] args) {
		// get the security manager
		SecurityManager mgr = new SecurityManager();
		// get the bean
		SecurityBean bean = getSecureBean();
		// try as clarence
		mgr.login("clarence", "pwd");
		bean.writeSecureMessage();
		mgr.logout();
		// try as janm
		try {
		mgr.login("janm", "pwd");
		bean.writeSecureMessage();
		} catch(SecurityException ex) {
		System.out.println("Exception Caught: " + ex.getMessage());
		} finally {
		mgr.logout();
		}
		// try with no credentials
		try {
		bean.writeSecureMessage();
		} catch(SecurityException ex) {
		System.out.println("Exception Caught: " + ex.getMessage());
		}
		}
		private static SecurityBean getSecureBean() {
		// create the target
		SecurityBean target = new SecurityBean();
		// create the advice
		SecurityAdvice advice = new SecurityAdvice();
		// get the proxy
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(target);
		factory.addAdvice(advice);
		SecurityBean proxy = (SecurityBean)factory.getProxy();
		return proxy;
		}
}
