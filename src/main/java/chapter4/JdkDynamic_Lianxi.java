package chapter4;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

interface Sell {
    void sell();
}

/**
 * jdk动态代理的使用
 * 1、创建被代理类
 * 2、创建代理类，持有被代理对象，并实现invocationHandle接口并重写invoke方法，在invoke方法中，写具体逻辑
 * 3、实例化被代理对象、实例化InvocationHandler对象使用Proxy.newProxyInstance()获取代理对象
 * 4、调用代理对象
 *
 * 其原理是通过java在运行时，动态生成class文件实现。该class 继承proxy，实现公共接口。
 */
public class JdkDynamic_Lianxi {

    public static void main(String[] args) throws IOException {
        Sell o = (Sell) Proxy.newProxyInstance(JdkDynamic_Lianxi.class.getClassLoader(), WineSell.class.getInterfaces(), new ProxyClass(new WineSell()));
        o.sell();
    }
}

class ProxyClass implements InvocationHandler {

    Sell target;

    public ProxyClass(Sell target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //前置方法执行
        System.out.println("执行前置方法   1");
        final Object result = method.invoke(target, args);
        System.out.println("执行后置方法  2");
        return result;
    }


    public void a() throws IOException {
        int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "MyClass", WineSell.class.getInterfaces(), accessFlags);

        OutputStream out = new FileOutputStream("D:/MyClass.class");
        out.write(proxyClassFile);
        out.close();
    }
}

class WineSell implements Sell {
    @Override
    public void sell() {
        System.out.println("我是卖酒的");
    }
}


