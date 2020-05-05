package chapter10;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TopicSubscriber;

/**
 * @author ljj
 * 持久化的订阅读取
 * 2019-11-05
 *
 * 使用TopicSubscriber订阅者可以订阅主题，在订阅主题之前，
 * TopicSubscriber需要先为connection设置一个clientId，在订阅可持久投送模式的topic之后，
 * 可以离线后，重新连接也能接收到消息。
 *
 */
public class _3DurableSubscriberDemo {

    private static final String name = "topic_of_users";

    public static void main(String[] args) throws JMSException {
        //1、创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        //2、创建connection
        Connection connection = factory.createConnection();
        connection.setClientID("1111112");
        //3、创建session
        Session session = connection.createSession(false, 1);
        //4、创建DurableSubscriber
        TopicSubscriber subscriber = session.createDurableSubscriber(session.createTopic(name), "sssss");
        //5、读取消息
        subscriber.setMessageListener((t) -> {
            System.out.println(t);
        });
        connection.start();
        System.out.println("主线程结束");
    }
}
