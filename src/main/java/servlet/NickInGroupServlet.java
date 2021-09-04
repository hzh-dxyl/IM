package servlet;

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

@WebServlet(name = "NickInGroupServlet",urlPatterns = "/nickInGroup")
public class NickInGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String json =request.getParameter("json");
        Member member =JSONObject.parseObject(json,Member.class);
        GroupMapper mapper= DaoUtils.getMapper(GroupMapper.class);
        try {
            int i=mapper.updateMember(member);
            System.out.println("修改群内消息："+i+"/"+member.getFriend_note()+"/"+member.getRank());
            if(i==1) response.getWriter().print("{msg:\"update success\"}");
            else response.getWriter().print("{msg:\"update failed\"}");
        }
        finally {
            DaoUtils.close();
        }
    }
}
