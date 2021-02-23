package chapter11;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring core的顶层设计
 * 核心接口：
 * 一、BeanFactory：Spring容器的根接口，该接口由持有bean定义的对象实现,BeanFactory是应用程序组件的中央注册表，
 * 并集中了应用程序组件的配置
 *
 * 二、ApplicationContext：中央接口，为应用程序提供配置。并且有下列五个能力：
 *      1、用于访问应用程序组件的bean工厂方法，继承至ListableBeanFactory。
 *      2、以通用方式加载文件资源的能力，继承至ResourceLoader
 *      3、发布事件到监听器的能力，继承至ApplicationEventPublisher
 *      4、解析信息、支持国际化的能力，继承至MessageSource接口
 *      5、从父context继承。 在子context中的定义将始终优先。
 *
 *  三、ConfigurableApplicationContext：从ApplicationContext、LifeCycle继承。
 *
 *
 */
public class Course2_SpringAnalysis {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("main/java/chapter11");


    }
}
