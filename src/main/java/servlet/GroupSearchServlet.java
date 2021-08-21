package servlet;

import bean.Group;
import bean.Member;
import com.alibaba.fastjson.JSONObject;
import dao.GroupMapper;
import utils.DaoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GroupSearchServlet",urlPatterns = "/groupSearch")
public class GroupSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String json =request.getParameter("json");  //传group_id
        JSONObject object=JSONObject.parseObject(json);
        int user_id=object.getInteger("user_id");
        Group group= JSONObject.parseObject(json,Group.class);
        GroupMapper mapper= DaoUtils.getMapper(GroupMapper.class);
        try {

            group = mapper.selectGroup(group);
            ArrayList<Member> members=mapper.getGroupMembers(group);
            for (Member member:members){
                member.setFriend_note(mapper.selectFriend_note(user_id,member.getUser_id()));
            }
            group.setMembers(members);
            String result=JSONObject.toJSONString(group);
            System.out.println(result);
            response.getWriter().print(result);
        }
        finally {
            DaoUtils.close();
        }
    }
}
