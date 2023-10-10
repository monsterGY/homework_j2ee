package first;

import dao.GetUserDao;
import second.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/02  18:32
 */
@WebServlet("/searchInfoServlet")
public class SearchInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchInfoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("search_id");
        String name = request.getParameter("search_name");
        String gender = request.getParameter("search_gender");
        String grade = request.getParameter("search_grade");
        String mobile = request.getParameter("search_mobile");
        String email = request.getParameter("search_email");

        GetUserDao userDao = new GetUserDao();
        List<Student> duters = null;
        try {
            duters = userDao.getDuters();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(id==null&&name==null&&gender==null&&grade==null&&mobile==null&&email==null) {  // 回到最初状态
            request.getSession().setAttribute("ResultList", duters);
            request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
        }else {
            if(id.equals("")&&name.equals("")&&gender.equals("")&&grade.equals("")&&mobile.equals("")&&email.equals("")) { // 回到最初状态
                request.getSession().setAttribute("ResultList", duters);
                request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
            }else {
                // 依次查询
                if(id!=null&&!id.equals("")&&duters!=null) {   // id其实可以唯一搜索，但若后面的姓名输错也没有结果
                    // 注意：ArrayList循环删除时不能用forEach，否则容易抛出ConcurrentModifiedException
                    Iterator<Student> it = duters.iterator();
                    while(it.hasNext()) {
                        Student p = it.next();
                        if(!p.getId().contains(id)) {
                            it.remove();
                        }
                    }
                }
                if(name!=null&&!name.equals("")&&duters!=null) {   // 姓名支持模糊搜索
                    Iterator<Student> it = duters.iterator();
                    while(it.hasNext()) {
                        Student p = it.next();
                        if(!p.getName().contains(name)) {
                            it.remove();
                        }
                    }
                }
                if(gender!=null&&!gender.equals("")&&duters!=null) {
                    Iterator<Student> it = duters.iterator();
                    while(it.hasNext()) {
                        Student p = it.next();
                        if(!p.getGender().contains(gender)) {
                            it.remove();
                        }
                    }
                }
                if(grade!=null&&!grade.equals("")&&duters!=null) {
                    Iterator<Student> it = duters.iterator();
                    while(it.hasNext()) {
                        Student p = it.next();
                        if(!p.getGrade().contains(grade)) {
                            it.remove();
                        }
                    }
                }
                if(mobile!=null&&!mobile.equals("")&&duters!=null) {
                    Iterator<Student> it = duters.iterator();
                    while(it.hasNext()) {
                        Student p = it.next();
                        if(!p.getMobile().contains(mobile)) {
                            it.remove();
                        }
                    }
                }
                if(email!=null&&!email.equals("")&&duters!=null) {
                    Iterator<Student> it = duters.iterator();
                    while(it.hasNext()) {
                        Student p = it.next();
                        if(!p.getEmail().contains(email)) {
                            it.remove();
                        }
                    }
                }
                request.getSession().setAttribute("ResultList", duters);
                request.getRequestDispatcher("/infoManager.jsp").forward(request, response);
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
