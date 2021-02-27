package chapter11;

/**
 * Spring的事件发布机制
 * 一、事件机制的使用场景：
 * 事件机制的作用是触发某动作后，相关动作被执行。
 *
 * 二、Spring中有六大标准事件：
 * ContextRefreshedEvent：ConfigurableApplicationContext.refresh()方法执行时触发。
 * ContextStartedEvent：ConfigurableApplicationContext.start()方法执行后触发。
 * ContextStoppedEvent：ConfigurableApplicationContext.stop()方法执行后触发。
 * ContextClosedEvent：ConfigurableApplicationContext.close()方法执行后触发。
 * RequestHandledEvent：一个特定于web的事件，告诉所有Bean HTTP请求已得到服务，请求完成后，将发布该事件。
 * ServletRequestHandledEvent：该类的子类RequestHandleEvent添加了特定于Servlet的上下文信息。
 *
 * 三、事件的继承关系：
 * EventObject  ->  ApplicationEvent ->  ApplicationContextEvent  ->   ContextRefreshedEvent
 * ps：ApplicationContextEvent构造方法传入一个ApplicationContext。
 *
 * 四、事件的发布：
 * 要发布自定义的ApplicationEvent，需要调用ApplicationEventPublisher.publishEvent()，通常可以通过ApplicationEventPublisher-
 * Aware的实现类的bean完成。
 *
 * 五、事件的监听：
 * 1）、要接收到发布的事件，可以创建一个实现ApplicationListener并实现此方法onApplicationEvent的监听器，并注册为一个Spring bean。
 * 2）、或使用注解@EventListener。
 *      扩展：@Async为异步监听注解，当异步方法出现异步时，不会传播到调用者。更多信息参阅AsyncUncaughtExceptionHandler
 * ApplicationListener  ->  EventListener
 *
 * 六、问题：TODO
 *      1、事件发布器（ApplicationEventPublisher）由框架注入，是在框架的哪个步骤实现？
 *      2、事件发布-侦听-处理机制，内部原理如何？
 */
public class Course3_Events {
}
