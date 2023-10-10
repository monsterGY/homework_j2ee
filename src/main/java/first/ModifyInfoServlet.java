package first;

import dao.ChangeInfoDao;
import second.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/02  18:29
 */
@WebServlet("/modifyInfoServlet")
public class ModifyInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifyInfoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String grade = request.getParameter("grade");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");

        Student p = new Student(id,name,gender,grade,mobile,email);
        System.out.println(p);
        ChangeInfoDao changeDao = new ChangeInfoDao();
        try {
            changeDao.changeInfo(p);
            List<Student> list = (List<Student>) request.getSession().getAttribute("ResultList");
            for(Student person:list) {
                if(person.getId().equals(p.getId())) {
                    person.setName(name);
                    person.setGender(gender);
                    person.setGrade(grade);
                    person.setMobile(mobile);
                    person.setEmail(email);
                    break;
                }
            }
            request.getSession().setAttribute("ResultList", list);
            // 获取当前页码
            int pageNum = (int) request.getSession().getAttribute("current_page");
            request.getRequestDispatcher("/infoManager.jsp?currentPageNum="+pageNum).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
