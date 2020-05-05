package chapter13;

import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 也学了这么久的Dubbo，今天写一个Spring项目和Spring Boot项目来检验一下成果。
 * 1、Spring项目是提供者。Spring Boot项目是消费者。
 * 这是一个消费者。
 * 第一步，先在Pom中将Spring5.2引入。
 * 第二步，在pom中引入Dubbo和Zookeeper客服端。
 * 第三个，配置chapter13_applicationContext.xml，在xml中声明远程调用。
 *
 * 改造一下：
 * 将ClassPathXmlApplicationContext类改造为BeanFactory工厂。
 *
 */
public class ConsumerDubbo {

    public static void main(String[] args) {

        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chapter13/chapter13_applicationContext.xml");
        BeanFactory factory =  new XmlBeanFactory(new ClassPathResource("chapter13/chapter13_applicationContext.xml"));
        GoodsService service = (GoodsService)factory.getBean("goodsService");
        System.out.println(service.findAll());
    }

}
