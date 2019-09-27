package chapter7;


import java.sql.*;

import com.mysql.cj.jdbc.Driver;


/**
 *
 * @author ljj
 * 2019/9/20 17:14
 *
 * 本章主要内容是原始jdbc的使用和使用durid来管理数据源。
 * 原始jdbc写法
 * 1、加载驱动
 *         Driver驱动版本升级了，从com.mysql.jdbc.Driver改到了com.mysql.cj.jdbc.Driver
 *         https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-api-changes.html
 * 2、获得连接
 * 3、操作数据库
 * 4、返回结果
 *
 *
 * 问题一：Statement对象的作用？
 * 答：Statement对象用于向db发送sql语句，并将结果返回。
 * 常用方法有
 *  i. boolean execute(String sql) throws SQLException;：该方法适合执行返回结果未知的sql，例如
 *     create、select、update或delete等语句。如果宁确定在执行sql后会有结果返回，可调用getResultSet
 *     获取结果集，调用getUpdateCount获取执行行数。
 *
 *  ii. executeQuery()：该方法返回ResultSet，典型的语句是select。
 *
 *  iii. executeUpdate()：该方法返回一个整数类型，一般用于insert、update、delete等。
 *
 * 问题二：Connection有几种Statement?分别的特别是什么？
 * 答：由com.mysql下，有以下几个Statement的扩展接口
 * i. PreparedStatement接口:该接口扩展了Statement，可以灵活设入参数，使sql更灵活、便捷。
 * i.i.CallableStatement接口：该接口可调用存储过程，也可以创建 Statement 对象和 PreparedStatement 对象。
 */
public class Jdbc {

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://172.16.10.232:3306/mobile_pro_4.0_whz?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "test", "1234");

            //usage of Statement
            Statement statement = connection.createStatement();
            statement.execute("select * from view_test");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                println(resultSet);
            }


            //usage of PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement("select * from view_test");
            preparedStatement.execute();
            ResultSet resultSet2 = preparedStatement.getResultSet();
            while (resultSet2.next()) {
                println(resultSet2);
            }

            //usage of PreparedStatement
            CallableStatement callableStatement = connection.prepareCall("select * from view_test");
            callableStatement.execute();
            ResultSet resultSet3 = callableStatement.getResultSet();
            while (resultSet3.next()) {
                println(resultSet3);
            }

            resultSet.close();
            resultSet2.close();
            resultSet3.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void println(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getLong(1) + ", " + resultSet.getString(2));
    }

}
