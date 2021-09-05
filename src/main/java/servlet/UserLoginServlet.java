package servlet;

import bean.User;
import com.alibaba.fastjson.JSONObject;
import dao.UserMapper;
import utils.DaoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", urlPatterns = "/userLogin")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String json = request.getParameter("json");
        User user= JSONObject.parseObject(json,User.class);
        /*Dao dao=new Dao();
        UserMapper mapper=(UserMapper)dao.getMapper(UserMapper.class);
        user=mapper.checkUser(user);
        if(user.getStatus()<2) user.cleanInfo();
        String result=JSONObject.toJSONString(user);
        System.out.println("登录结果："+result);
        response.getWriter().print(result);
        dao.closeSession();*/
        UserMapper mapper= DaoUtils.getMapper(UserMapper.class);
        try {
            user=mapper.checkUser(user);
            if(user.getStatus()<2) user.cleanInfo();
            String result=JSONObject.toJSONString(user);
            System.out.println("登录结果："+result);
            response.getWriter().print(result);
        }
        finally {
            DaoUtils.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
