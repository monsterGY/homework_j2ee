package first;

import second.Student;
import utils.FindTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/02  18:27
 */
@WebServlet("/getDataServlet")
public class GetDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public int totalNum;
    public int pageNum;

    public GetDataServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String data = request.getParameter("data");
        data = data.trim();
        request.getSession().setAttribute("Data", data);

        List<Student> persons = FindTool.find(data);
        if (persons != null) {
            //存在查询结果
            totalNum = persons.size();
            request.getSession().setAttribute("Num", totalNum);
            request.getSession().setAttribute("Persons", persons);
            request.getServletContext().getRequestDispatcher("/showResult.jsp?currentPageNum=0&totalNum=" + totalNum).forward(request, response);
        } else {
            //查询结果为空
            request.getServletContext().getRequestDispatcher("/badResult.html").forward(request, response);
        }
    }
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request, response);
        }
}

