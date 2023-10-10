package first;

import second.Student;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/09/26  15:44
 */
public class mysqlTest {
    private static Connection con; //声明 Connection 对象
    private static PreparedStatement pStmt;//声明预处理 PreparedStatement 对象
    private static ResultSet res;//声明结果 ResultSet 对象

    private static String url = "jdbc:mysql://localhost:3306/mysql";// 协议：子协议：//目标IP地址：端口/数据库  在这里test1是之前创建的数据库名
    private static String user = "root";
    private static String password = "123456";

    public Connection getConnection() {//建立返回值为 Connection 的方法

        //代码块（1）：加载数据库驱动类
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        //代码块（2）：通过访问数据库的URL获取数据库连接对象
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

//    public static void main(String[] args) {//主方法
//        mysqlTest h = new mysqlTest();//创建本类对象
//        con = h.getConnection();//与数据库建立连接
//        queryData("SELECT * FROM mysql.classone  where 性别 = '女' union SELECT * FROM mysql.classtwo  where 性别 = '女'");//查询数据
//
//        //addData();//增添数据
//        //updateData();//更新数据
//        //deleteData();//删除数据
//    }

    //代码块（3）：运用SQL语句进行操作
    public static Student queryData(String s) {
        Student a=new Student();
        try {   //mysql查询语句
            String sql = s;
            //代码块（4）：得到结果集
            pStmt = con.prepareStatement(sql);
            res = pStmt.executeQuery();
            while (res.next()) {//如果当前语句不是最后一条，则进入循环
                //代码块（5）：展示数据集
                Student b=new Student(
                        res.getBigDecimal("学号").toString(),
                                 res.getString("名称") ,
                                 res.getString("性别") ,
                                 res.getString("班级"),
                                 res.getString("移动电话") ,
                                 res.getString("邮箱"));
                a=b;
//                System.out.println(
//                        res.getBigDecimal("序号") + " "
//                        + res.getBigDecimal("学号")+" "
//                        + res.getString("名称") + " "
//                        + res.getString("性别") + " "
//                        + res.getString("班级")+ " "
//                        + res.getString("移动电话") + " "
//                        + res.getString("邮箱")
//                );
                // System.out.println(rs.getString("姓名"));
                // System.out.println(rs.getString("性别"));
                // System.out.println( rs.getString("学号") + rs.getString("姓名"));
            }

            res.close();//释放资源
            pStmt.close();


        } catch (SQLException e) {//捕获异常
            e.printStackTrace();
        }
        return a;
    }


    public static void addData() {//添加数据操作
        try {
            //pStmt = con.prepareStatement

            pStmt = con.prepareStatement("insert into student (学号, 姓名, 性别，分数) values('6','熊二','女','95')");
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateData() {//更新数据操作
        try {
            pStmt = con.prepareStatement("update student set 姓名 = '熊三' where 学号 = 1");
            // pStmt.setString(1, "周礼");通配符
            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteData() {//删除数据操作
        try {
            Statement stmt = con.createStatement();//创建Statement对象
            stmt.executeUpdate("delete from student where 学号=4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
