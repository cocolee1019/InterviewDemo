package chapter4;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理练习  （code generate library）
 *
 * 原理是通过实现子类，完成对代理对象的引用
 * 1、创建被代理类。 （如果为final类型，则不可被继承）
 * 2、创建代理类，并持有被代理类，实现MethodInterceptor，并覆写intercept()方法，在此方法写具体代理逻辑。
 * 3、使用Enhancer类，设置被代理类的superClass和CallBack（MethodIntercept继承至CallBack），并调用create()方法生成代理对象。
 * 4、使用代理对象。
 */
public class CglibDynamic_LianXi {

    public static void main(String[] args) {
        FuA a = new FuA();
        ProxyFuA b = new ProxyFuA(a);
        FuA enhanceFuA = (FuA) Enhancer.create(FuA.class,  b);
        System.out.println(enhanceFuA.method(1, 2));
    }
}

class FuA {
    Integer method(Integer a, Integer b) {
        return a.intValue() + b.intValue();
    }
}

//创建代理类
class ProxyFuA implements MethodInterceptor {

    Object target;

    public ProxyFuA(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前置方法");
        //执行原方法
        Object result = method.invoke(target, args);
        System.out.println("执行被代理方法");
        System.out.println("执行后置方法");
        return result;
    }
}