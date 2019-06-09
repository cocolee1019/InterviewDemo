package chapter4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 1、JDK的动态代理，必需要实现InvocationHandler接口。
 * 2、建立代理对象与真实对象的关系，在本例中，通过bind方法进行绑定。
 * 3、实现代理逻辑的方法， 在本例中，代理逻辑实现在invoke中。
 */
public class JdkProxyExample implements InvocationHandler {

	private Object target = null;

	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("进入代理逻辑方法");
		System.out.println("在调度真实对象之前的服务");
		Object obj = method.invoke(target, args);
		System.out.println("在调度之后");
		return obj;
	}

}
