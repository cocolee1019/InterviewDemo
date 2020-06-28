package chapter9;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 1、大概说下put操作的过程。
 * 2、如何保证他是线程安全的？
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        ConcurrentHashMap map = new ConcurrentHashMap<>();
        map.put("", 1);
    }
}
