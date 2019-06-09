package chapter4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 1��JDK�Ķ�̬��������Ҫʵ��InvocationHandler�ӿڡ�
 * 2�����������������ʵ����Ĺ�ϵ���ڱ����У�ͨ��bind�������а󶨡�
 * 3��ʵ�ִ����߼��ķ����� �ڱ����У������߼�ʵ����invoke�С�
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
		System.out.println("��������߼�����");
		System.out.println("�ڵ�����ʵ����֮ǰ�ķ���");
		Object obj = method.invoke(target, args);
		System.out.println("�ڵ���֮��");
		return obj;
	}

}
