package servlet;

import bean.Contact;
import bean.Friend;
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

@WebServlet(name = "AddFriendServlet",urlPatterns = "/addFriend")
public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("utf-8");
        String json=request.getParameter("json");
        Friend friend=JSONObject.parseObject(json,Friend.class);
        UserMapper mapper= DaoUtils.getMapper(UserMapper.class);
        try {
            int i=mapper.addFriend(friend);
            System.out.println("添加好友结果："+i);
            if(i==1) response.getWriter().print("{msg:\"add success\"}");
            else response.getWriter().print("{msg:\"add failed\"}");
        }
        finally {
            DaoUtils.close();
        }

    }
}
