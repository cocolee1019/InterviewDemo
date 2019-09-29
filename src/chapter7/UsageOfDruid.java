package chapter7;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author ljj
 * 2019/9/27 16:09
 *
 * 使用druid管理数据库连接池
 *
 * 问题：
 * 1、druid如何管理数据连接的？
 * 答：druid用于管理数据库连接（connection），设置好基本连接属性后，
 *    调用getConnection方法，可获得连接。
 *
 *
 */
public class UsageOfDruid {


    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.put("druid.url", "jdbc:mysql://172.16.10.232:3306/mobile_pro_4.0_whz?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        properties.put("druid.username", "test");
        properties.put("druid.password", "1234");

        Properties properties2 = new Properties();
        properties2.put("druid.url", "jdbc:mysql://172.16.10.232:3306/mobile_pro_4.0_whz?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        properties2.put("druid.username", "root");
        properties2.put("druid.password", "zhicall@2018");

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.configFromPropety(properties);
        dataSource.configFromPropety(properties2);
        Connection connection = dataSource.getConnection();
        Connection connection2 = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from view_test");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            println(resultSet);
        }

        connection.close();
    }

    private static void println(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getLong(1) + ", " + resultSet.getString(2));
    }
}
