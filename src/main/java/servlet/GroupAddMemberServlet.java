package servlet;

import bean.Member;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import dao.GroupMapper;
import utils.DaoUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GroupAddMemberServlet",urlPatterns = "/groupAddMember")
public class GroupAddMemberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String json =request.getParameter("json");
        Member member= JSONObject.parseObject(json,Member.class);
        GroupMapper mapper=DaoUtils.getMapper(GroupMapper.class);
        try {
            int i=mapper.addMember(member);
            System.out.println("加入群聊："+i+"/"+json);
            if(i==1) response.getWriter().print("{msg:\"add success\"}");
            else response.getWriter().print("{msg:\"add failed\"}");
        }
        finally {
            DaoUtils.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
