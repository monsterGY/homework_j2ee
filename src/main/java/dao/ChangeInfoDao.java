package dao;

import org.apache.commons.dbutils.QueryRunner;
import second.Admin;
import second.Student;
import utils.JDBCUtils;

import java.sql.SQLException;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/02  17:11
 */
public class ChangeInfoDao {
    /*
     * 删除某个学生信息
     */
    public boolean deleteInfoById(String id) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
       // String sql = "delete from classone where 学号=?";
        String sql = "delete from classtwo where id=?";
        int row = qr.update(sql, id);
        if(row>0) {
            return true;
        }else {
            return false;
        }
    }
    /*
     * 修改某个学生信息
     */
    public void changeInfo(Student p) throws SQLException{
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        //String sql = "update classone set 名称=?, 性别=?, 班级=?, 移动电话=?,邮箱=? where 学号=?";
        String sql = "update classtwo set name=?, gender=?, grade=?, mobile=?,email=? where id=?";
        Object[] params = {p.getName(), p.getGender(), p.getGrade(), p.getMobile(), p.getEmail(),p.getId()};
        int row = qr.update(sql, params);
        System.out.println("修改："+row);
    }
    /*
     * 添加学生信息
     */
    public boolean addNewInfo(Student p) throws SQLException{
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        //String sql = "insert into classone (学号,名称, 性别,班级 , 移动电话, 邮箱) values (?,?,?,?,?,?)";
        String sql = "insert into classtwo (id,name, gender,grade,mobile, email) values (?,?,?,?,?,?)";
        Object[] params = {p.getId(), p.getName(), p.getGender(), p.getGrade(),p.getMobile(), p.getEmail()};
        int row = qr.update(sql, params);
        if(row>0) {
            return true;
        }else {
            return false;
        }
    }
    /*
     * 修改某个管理员的信息
     */
    public void changeAdminInfo(Admin admin) throws SQLException{
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "update admin set name=?, realName=?, password=?, email=?, isSign=? where id=?";
        Object[] params = {admin.getName(), admin.getRealName(), admin.getPassword() , admin.getEmail(), admin.getIsSign() ,admin.getId()};
        int row = qr.update(sql, params);
        System.out.println("修改："+row);
    }


}
