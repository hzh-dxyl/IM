package servlet;

import bean.User;
import com.alibaba.fastjson.JSONObject;
import dao.Dao;
import dao.UserMapper;
import jdk.nashorn.internal.runtime.regexp.joni.exception.JOniException;
import utils.DaoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserSearchServlet",urlPatterns = "/userSearch")
public class UserSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String json=request.getParameter("json");
        User user= JSONObject.parseObject(json,User.class);
        UserMapper mapper=DaoUtils.getMapper(UserMapper.class);
        try{
            user=mapper.searchUser(user);
            String result=JSONObject.toJSONString(user);
            System.out.println("查询用户："+result);
            response.getWriter().print(result);  //存在时返回user信息，不存在时返回null
        }
        finally {
            DaoUtils.close();
        }
    }
}
