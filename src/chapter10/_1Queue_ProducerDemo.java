package chapter10;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * active mq基础知识：
 * 是apache的项目，是灵活强大、多协议、开源的基于java的消息服务
 * mq有许多高级功能，包括消息负载均衡和数据高可用。
 *
 * 核心知识点：
 * 1、queue：列队，是消息的两种模式之一，也是消息的存放处，存在队列中的消息，遵循着队列的特性，先进先出，
 *          并且同一条消息，只能被消耗一次。同一个队列，如果被不同的消费者注册，那么消息会被依次分发。
 * 2、topic：主题，是消息的两种模式之一，是消息的另一个存放处，存在于主题中的消息会被广播到多个主要订阅者中。
 *
 * 如何使用：
 * 1、如何向队列发送消息：
 *      (1)、创建连接工厂。
 *      (2)、获得connection。
 *      (3)、创建session。
 *      (4)、通过session创建Producer。
 *      (5)、通过Producer向队列发送消息。
 *
 * 2、如何从队列中接收消息：
 *      前三步同上
 *       connection记得启动start。
 *      （4）、通过session创建Consumer。
 *      （5）、调用consumer.receive()方法。
 *
 * 重要：
 *      receive方法说明：
 *          receive方法有两个，第一个没有参数，表示会无限等待，直到接收到消息后，才往下走。
 *          第二个拥有一个long类型参数，表示等待毫秒数，过了这个时间，消息未到达，将会自动退出。
 *
 * 问题：
 *   1、根据实践，发现创建一个队列，最简单的方式是只需要指定名字即可，消费一个队列，最简单的方式也是只需要指定队列名称即可，
 *      这样做，会不会太不安全？假如会，该如何避免？
 *      答：在同一个connection中，访问queue或者订阅topic都已经没有安全机制，但是connection需要指定账号和密码。所以一
 *      般情况下，connection的连接需要修改默认密码。
 *
 *   2、consumer.receive()是同步方式，请问还有其它的接收方式吗？
 *      答：使用messageListener可以监听消息，可以异步接收。在主线程中，开启消息监听后，会立即退出，而不会阻塞。
 */
public class _1Queue_ProducerDemo {

    private static final String QUEUE_NAME = "queue_of_ssss";

    public static void main(String[] args) throws JMSException {

        /*
           向指定队列发送消息
         */
        ActiveMQConnectionFactory connFacotry = new ActiveMQConnectionFactory();
        Connection connection = connFacotry.createConnection();
        Session session = connection.createSession(false, 1);
        MessageProducer producer = session.createProducer(new ActiveMQQueue(QUEUE_NAME));
        for (int i=1; i<=3; i++) {
            ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage();
            activeMQTextMessage.setText("hhhhhhhhhhhhhhhhhhhhhhhhhhh");
            producer.send(activeMQTextMessage);
        }

        System.out.println("向消息队列发出3条消息成功");
        producer.close();
        session.close();
        connection.close();
    }
}
