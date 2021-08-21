package servlet;

import bean.Contact;
import bean.Friend;
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

@WebServlet(name = "FriendSearchServlet",urlPatterns = "/friendSearch")
public class FriendSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String json=request.getParameter("json");
        //传user_id和friend_id
        Friend friend= JSONObject.parseObject(json,Friend.class);
        UserMapper mapper= DaoUtils.getMapper(UserMapper.class);
        try {
            friend = mapper.selectFriend(friend);
            User user = mapper.selectUser(friend.getFriend_id());
            JSONObject object = (JSONObject) JSONObject.toJSON(user);
            object.remove("user_pwd");
            object.put("friend_note", friend.getFriend_note());
            object.put("notice_rank", friend.getNotice_rank());
            String result = JSONObject.toJSONString(object);
            System.out.println("查看好友信息："+result);
            response.getWriter().print(result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DaoUtils.close();
        }
    }
}
