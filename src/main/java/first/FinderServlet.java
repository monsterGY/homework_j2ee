package first;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import second.Student;
import second.Page;



/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/09/19  8:39
 */
public class FinderServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    // contact table
    private List<Map<String, Object>> contacts = new ArrayList<Map<String, Object>>();

    public void init() throws ServletException {
        try {
            String files = getInitParameter("contacts");
            //trim函数用于删除头尾空格符
            files = files.trim();
            files = files.replace('，', ',');
            String[] file_name_array = files.split(",");

            for (int i = 0; i < file_name_array.length; i++) {
                String file_name = file_name_array[i];
                file_name = file_name.trim();
                if (file_name.length() == 0) {
                    continue;
                }

                File file = new File(getServletContext().getRealPath("/WEB-INF/contacts/" + file_name));

                FileInputStream fis = new FileInputStream(file);
                //Workbook是Excel对象
                Workbook book = null;

                try {
                    //可以实现读写Excel
                    book = new XSSFWorkbook(fis);
                } catch (Exception ex) {
                    //读写新老版本不同的Excel
                    book = new HSSFWorkbook(fis);
                }


                Sheet sheetAt = book.getSheetAt(0);

                for (Row row : sheetAt) {
                    //第一行是表头，跳过
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    if (row == null) {
                        break;
                    }
                    //cell用于分块
                    Cell cell = row.getCell(0);

                    if (cell == null) {
                        break;
                    }


                    double no = row.getCell(0).getNumericCellValue();
                    String id = row.getCell(1).getStringCellValue();
                    String name = row.getCell(2).getStringCellValue();
                    String strClass = "";
                    String mobile = "";
                    String email = "";
                    //班级
                    cell = row.getCell(3);
                    if (cell != null) {
                        strClass = cell.getStringCellValue();
                    }
                    //电话
                    cell = row.getCell(4);
                    if (cell != null) {

                        cell.setCellType(CellType.STRING);
                        mobile = cell.getStringCellValue();
                    }
                    //邮箱
                    cell = row.getCell(5);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        email = cell.getStringCellValue();
                    }

                    //将改行的所有元素放入到Map集合
                    Map<String, Object> record = new HashMap<String, Object>();
                    record.put("id", id);

                    //判断性别
                    String gender=name.charAt(name.length()-1)=='*'?"女":"男";
                    record.put("gender", gender);
                    if("女".equals(gender)){
                        record.put("name", name.substring(0,name.length()-1));
                    }else if("男".equals(gender)){
                        record.put("name",name);
                    }

                    record.put("class", strClass);
                    record.put("mobile", mobile);
                    record.put("email", email);
                    //将map集合加入到List集合
                    contacts.add(record);

                }

                book.close();
                fis.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        //第一次作业使用
//        request.setCharacterEncoding("utf-8");
//
//        response.setContentType("text/html;charset=utf-8");
//
//        //获取浏览器传参
//        String param = request.getParameter("param");
//        PrintWriter out = response.getWriter();
//
//        //遍历每个学生
//        for(Map<String, Object> map:contacts) {
//            if (map.containsValue(param)) {
//                //检索的结果包括学生的学号，姓名，性别，班级，手机号码，邮件地址。
//                String id = (String) map.get("id");
//                String name = (String) map.get("name");
//                String gender = (String) map.get("gender");
//                String strClass = (String) map.get("class");
//                String mobile = (String) map.get("mobile");
//                String email = (String) map.get("email");
//                out.write("id: " + id + "<br>");
//                out.write("name: " + name + "<br>");
//                out.write("gender: " + gender + "<br>");
//                out.write("strClass: " + strClass + "<br>");
//                out.write("mobile: " + mobile + "<br>");
//                out.write("email: " + email + "<br>");
//            }
//
//        }
//        out.close();


        //第二次作业使用

//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//
//        //获取浏览器传参
//        String param = request.getParameter("param");
//
//        PrintWriter out = response.getWriter();
//        List<Student> res=new ArrayList<>();
//
//
//        //遍历每个学生
//        for(Map<String, Object> map:contacts){
//            //遍历每个学生的每个属性
//            for(Object val:map.values()){
//                String sv=(String)val;
//                int i = sv.indexOf(param);
//                if(i!=-1){
//                    String id=(String)map.get("id");
//                    String name=(String)map.get("name");
//                    String gender=(String)map.get("gender");
//                    String strClass=(String)map.get("class");
//                    String mobile=(String)map.get("mobile");
//                    String email=(String)map.get("email");
//                    Student student = new Student(id, name, gender, strClass, mobile, email);
//                    res.add(student);
//                }
//            }
//        }
//        // out.close();
//
//        //将集合转为数组，方便使用foreach进行循环
//        Student [] res2 = res.toArray(new Student[0]);
//        int currentPage=0;
//        int count=10;
//        //获取连接中的当前页数和一共页数
//        String current = request.getParameter("currentPage");
//        String countString = request.getParameter("count");
//        if(current!=null){
//            currentPage=Integer.parseInt(current);
//        }
//        if(countString!=null){
//            count=Integer.parseInt(countString);
//        }
//        currentPage = Math.max(currentPage, 0);
//        count =count>=0?count:10;
//
//
//        Page page = new Page(res2.length,currentPage,count,currentPage+count-1);
//
//        //将结果写入到request请求域
//        request.setAttribute("res",res2);
//        request.setAttribute("page",page);
//
//        //转发
//        request.getRequestDispatcher("/page.jsp").forward(request,response);


        //第三次作业使用

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        mysqlTest h = new mysqlTest();//创建本类对象
        Connection con = h.getConnection();//与数据库建立连接

        //获取浏览器传参
        String param = request.getParameter("param");
        System.out.println(param);

        String sql = "select * from classone where 名称 = '" + param + "'";
        System.out.println(sql);

        Student x=new Student();
        x=mysqlTest.queryData(sql);
        System.out.println(x.getName());


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}



