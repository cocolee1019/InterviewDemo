package chapter11;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring core的顶层设计
 * 核心接口：
 * 一、BeanFactory：Spring容器的根接口，该接口由持有bean定义的对象实现,BeanFactory是应用程序组件的中央注册表，
 * 并集中了应用程序组件的配置
 *
 * 二、ApplicationContext：中央接口，为应用程序提供配置。并且有下列五个能力：
 *      1、用于访问应用程序组件的bean工厂方法，继承至ListableBeanFactory。
 *      2、以通用方式加载文件资源的能力，继承至ResourceLoader
 *  *      3、发布事件到监听器的能力，继承至ApplicationEventPublisher
 *      4、解析信息、支持国际化的能力，继承至MessageSource接口
 *      5、从父context继承。 在子context中的定义将始终优先。
 *
 *  三、ConfigurableApplicationContext：从ApplicationContext、LifeCycle扩展。
 *
 *  四、ResourceLoader：将给定的资源路径文件，解析成Resource对象
 *  Strategy interface for loading resources (e.. class path or file system resources).
 *  ApplicationContext需要提供此功能，以及扩展ResourcePatternResolver。DefaultResourceLoader是一个可以在
 *  ApplicationContext之外使用的独立实现，也可以由ResourceEditor使用。当在ApplicationContext中运行时，
 *  可以使用特定上下文的资源加载策略，从字符串填充类型为Resource和Resource array的Bean属性。
 *
 * 五、ResourcePatternResolver：扩展于ResourceLoader，解析本地pattern（如xml）到Resource对象的策略接口。
 *
 * 六、ApplicationEventPublisher：封装事件发布功能的接口。
 *
 *
 * ===============================================================================================
 * 扩充知识：
 * SPI： 全称为 (Service Provider Interface) ,是JDK内置的一种服务提供发现机制。
 * 一个服务(Service)通常指的是已知的接口或者抽象类，服务提供方就是对这个接口或者抽象类的实现，
 * 然后按照SPI 标准存放到资源路径META-INF/services目录下，文件的命名为该服务接口的全限定名。
 *
 * ===============================================================================================
 */

public class Course2_SpringAnalysis {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("chapter11");

        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("");

    }
}
