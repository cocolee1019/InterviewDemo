package chapter10;

import org.apache.activemq.broker.BrokerService;

import javax.swing.*;

/**
 * @author ljj
 * 2019-11-06
 * 内嵌式的JMS服务。
 */
public class _6EmbedBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61619");
        brokerService.start();
    }
}
