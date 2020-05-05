package chapter10;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * 从队列接收消息
 */
public class _1Queue_ConsumerDemo {

    private static final String QUEUE_NAME = "queue_of_ssss";

    public static void main(String[] args) throws JMSException {

        /*
           从指定队列接收消息
         */
        ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory();
        Connection connection = connFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, 1);
        MessageConsumer consumer = session.createConsumer(new ActiveMQQueue(QUEUE_NAME));

        // 同步接收消息的方式
        /*
        while(true) {
            Message msg = consumer.receive();
            if (msg != null) {
                System.out.println(msg.toString());
            }
        }*/

        //异步接收消息的方式，使用MsgListener
        consumer.setMessageListener((msg) -> {
            System.out.println(msg.toString());
        });

        System.out.println("-------主线程结束-------");
    }
}
