package chapter10;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class _2Topic_ConsumerDemo {

    private static final String name = "topic_of_users";
    private static final String url = "tcp://localhost:61619";

    public static void main(String[] args) throws JMSException {

        /*
           订阅topic
         */
        ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, 1);
        MessageConsumer consumer = session.createConsumer(new ActiveMQTopic(name));

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
//            msg.acknowledge();
        });

        System.out.println("-------主线程结束-------");
    }
}
