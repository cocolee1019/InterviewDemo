package chapter2;

import javafx.beans.binding.StringBinding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 字符输入流。
 * 1、为什么有了字节流还会出现字符流？
 * 答：Reader不是用来代替InputStream而设计，Reader和Writer的出现主要是为了方便处理java中的Unicode字符，
 * 比如在从一个txt文本读取数据流时，假如使用“字节输入流”，那么一般的情况是将所有数据读入在byte[]
 * （需指定数组大小），然后再编码为String，但常常会出现乱码，问题一般是出现在编码这个过程，因为在
 * 对字节编码为字符时，不一定是一串完整的字节
 */

public class Course5_Reader {

    public static void main(String[] args) throws IOException {
        errorByteToString();
    }
    /**
     * 使用字节流读取字符时，编码错误导致的乱码示例
     */
    private static void errorByteToString() throws IOException {
        StringBuilder sb = new StringBuilder();
        FileInputStream in = new FileInputStream("e:/Desktop/a.TXT");
        byte[] bytes = new byte[2];
        while(in.read(bytes) != -1) {
            sb.append(new String(bytes));
        }
        System.out.println(sb.toString());
    }
}
