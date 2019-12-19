package chapter13;

import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 提供者
 */
public class ProviderDubbo {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chapter13/chapter13_applicationContext_1.xml");
        ctx.start();
        System.in.read();
    }
}
