package uitls;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

    public static void main(String[] args) {

        long currentMills = System.currentTimeMillis();
        int sec = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(32);

        //一秒钟10次请求
        int i = 0;
        while (System.currentTimeMillis() - sec < currentMills) {
            executorService.submit(()->{
                try {
                    System.out.println(HttpUtils.generateRequest("http://139.224.133.41:8080/missu").doGet());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            i++;
        }
        System.out.println(i);
    }
}

