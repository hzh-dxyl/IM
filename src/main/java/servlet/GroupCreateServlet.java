package servlet;

import bean.Group;
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

@WebServlet(name = "GroupCreateServlet",urlPatterns = "/groupCreate")
public class GroupCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String json=request.getParameter("json");
        Group group= JSONObject.parseObject(json,Group.class); //应该有名字信息
        GroupMapper mapper=DaoUtils.getMapper(GroupMapper.class);
        try {
            group.setGroup_img(Value.defaultGroupHead);
            int i=mapper.createGroup(group);
            if(i==1){
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
