package chapter10;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * 向topic发送消息
 *
 * 问题：
 *  1、如何向topic发送消息。
 *      答：同向queue发送消息一样，有如下几个步骤
 *      1、创建工厂。
 *      2、创建connection
 *      3、创建session
 *      4、创建生产者
 *      5、发送消息
 *
 *  2、新的订阅者，能收取之前的消息吗?
 *      答：不能，topic的历史消息，不会被旧的订阅者而获取。并且topic的消息会一次广播到所有订阅者，
 *         假如topic有消息，而没有接收者时，会直接被丢弃。
 */
public class _2Topic_ProducerDemo {

    private static final String name = "toptic_of_users";

    public static void main(String[] args) throws JMSException {
        //1、创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        //2、创建connection
        Connection connection = factory.createConnection();
        //3、创建session
        Session session = connection.createSession(false, 1);
        //4、创建生产者
        MessageProducer producer = session.createProducer(new ActiveMQTopic(name));
        //5、发送消息
        ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage();
        activeMQTextMessage.setText("~~~~~~!!!!!");
        producer.send(activeMQTextMessage);
        producer.send(activeMQTextMessage);
        producer.send(activeMQTextMessage);
        producer.close();
        session.close();
        connection.close();

        System.out.println("向topic发送消息成功，一共3条");
    }
}
