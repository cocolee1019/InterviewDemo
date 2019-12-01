package chapter11;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * Spring如何解决bean的循环引用。
 *
 */
@Configuration
@ComponentScan("chapter11")
public class Spring1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("chapter11");
        Object a = context.getBean("a");
        System.out.println(a);
    }

}

@Component
class A {
    public A() {
        System.out.println("init A~");
    }
}

class B {
    public B() {
        System.out.println("init B~");
    }
}

/**
 * BeanFactoryPostProcess接口，用于在初始化bean之前，获取BeanFactory，然后对Bean进行一系引自定义改动。
 */
@Component
class BeanFactoryPostProcessorImpl1 implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("进入PostProcess");
        GenericBeanDefinition aDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("a");
        aDefinition.setBeanClass(B.class);

    }
}
