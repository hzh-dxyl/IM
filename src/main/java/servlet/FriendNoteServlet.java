package servlet;

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

@WebServlet(name = "FriendNoteServlet",urlPatterns = "/friendNote")
public class FriendNoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("utf-8");
        String json=request.getParameter("json");
        Friend friend= JSONObject.parseObject(json,Friend.class);
        UserMapper mapper= DaoUtils.getMapper(UserMapper.class);
        try {
            int i=mapper.updateFriend(friend);
            System.out.println("修改好友备注：结果="+i+"  "+friend.getUser_id()+","+friend.getFriend_id()+":"+friend.getFriend_note()+","+friend.getNotice_rank());
            if(i==1) response.getWriter().print("{msg:\"update success\"}");
            else response.getWriter().print("{msg:\"update failed\"}");
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DaoUtils.close();
        }
    }
}
