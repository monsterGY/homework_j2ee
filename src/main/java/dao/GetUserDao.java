package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import second.Admin;
import second.Student;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/02  17:52
 */
public class GetUserDao {
    private List<Admin> admins;
    private List<Student> duters;


    /*
     * 从数据库中获得admin信息，进行管理员校验
     */
    public List<Admin> getAdmin() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        // 通过select 查询获得结果
        String sql = "select * from admin";
        admins = qr.query(sql, new BeanListHandler<Admin>(Admin.class));
        // 检查结果
        //System.out.println(admin);
        return admins;
    }

    /*
     * 获得所有Duters的信息，进行搜索与管理
     */
    public List<Student> getDuters() throws SQLException{
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        //String sql = "select * from classone";
        String sql = "select * from classtwo";
        duters = qr.query(sql, new BeanListHandler<Student>(Student.class));
        // 检查结果
    	for(Student p: duters) {
			System.out.println(p);
		}
        System.out.println("数据库中一共有"+duters.size()+"条学生信息");
        return duters;
    }

}
