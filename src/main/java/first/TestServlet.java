package first;

import ai.AuthService;
import second.Student;
import utils.ImageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 功能描述
 *
 * @author lenovo
 * @date 2023/10/05  13:35
 */
@WebServlet("/testServlet")
public class TestServlet  extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public TestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String filePath = request.getParameter("filePath");
        Student currentUser = (Student) request.getSession().getAttribute("currentUser");
        System.out.println(filePath);
        AuthService service = new AuthService();
        String access_token = service.getAuth();

        if(filePath==null||filePath.equals("")) {

        }else {
            String imgFile = filePath;     // 需要前端传入照片的路径
            System.out.println(imgFile);
            ImageUtils.uploadImg(currentUser.getId(), imgFile);
            System.out.println("access_token为："+access_token);
            request.getRequestDispatcher("/commonUser.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
