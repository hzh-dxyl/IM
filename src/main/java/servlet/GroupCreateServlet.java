package servlet;

import bean.Group;
import bean.Member;
import bean.Value;
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
import java.util.List;

@WebServlet(name = "GroupCreateServlet",urlPatterns = "/groupCreate")
public class GroupCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("utf-8");
        String json=request.getParameter("json");
        JSONObject object=JSONObject.parseObject(json);
        String group_name= object.getString("group_name"); //应该有名字信息
        Group group=new Group();
        group.setGroup_name(group_name);
        group.setGroup_img("group.png");
        List<Member> members=object.getJSONArray("members").toJavaList(Member.class);
        GroupMapper mapper=DaoUtils.getMapper(GroupMapper.class);
        try {
            group.setGroup_img(Value.defaultGroupHead);
            int i=mapper.createGroup(group);
            int j=0;
            for (Member member:members){
                System.out.println(JSONObject.toJSONString(member));
                member.setGroup_id(group.getGroup_id());
                j+=mapper.addMember(member);
            }
            if(i==1&&j==members.size()){
                System.out.println("建群成功："+JSONObject.toJSONString(group));
                response.getWriter().print("{msg:\"create success\"}");
            }
            else {
                System.out.println("建群失败："+JSONObject.toJSONString(group));
                response.getWriter().print("{msg:\"create failed\"}");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("建群失败："+JSONObject.toJSONString(group));
            response.getWriter().print("{msg:\"create failed\"}");
        }
        finally {
            DaoUtils.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
