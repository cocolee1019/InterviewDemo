package chapter11;

import org.springframework.context.annotation.Bean;

/**
 * 1、@Bean的作用是什么？能用在什么地方？
 *   答：可以用在方法与注解上。
 *
 * 2、@ResponseBody的作用是什么？
 *   答：指示方法返回值应该被序列化到web返回值的body体中。
 */
public class Course4_Annotation {

    @Bean
    public String method() {
        return "";
    }
}
