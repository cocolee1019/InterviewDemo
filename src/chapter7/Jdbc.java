package chapter7;

/**
 * @author ljj
 * 2019/9/20 17:14
 *
 * 使用jdbc步骤
 * 1、加载驱动
 * 2、获得连接
 * 3、操作数据库
 * 4、返回结果
 *
 * 问题：
 * 1、如果要做一个数据连接池，是在池中持有什么数据，连接还是会话？
 *
 */
public class Jdbc {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
