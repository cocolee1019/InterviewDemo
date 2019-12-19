package chapter13;

import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 也学了这么久的Dubbo，今天写一个Spring项目和Spring Boot项目来检验一下成果。
 * 1、Spring项目是提供者。Spring Boot项目是消费者。
 * 这是一个消费者。
 * 第一步，先在Pom中将Spring5.2引入。
 * 第二步，在pom中引入Dubbo和Zookeeper客服端。
 * 第三个，配置chapter13_applicationContext.xml，在xml中声明远程调用。
 */
public class ConsumerDubbo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chapter13/chapter13_applicationContext.xml");
        GoodsService service = (GoodsService)ctx.getBean("goodsService");
        System.out.println(service.findAll());
    }

}
