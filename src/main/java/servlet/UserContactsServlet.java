package servlet;

import bean.Contact;
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
import java.util.ArrayList;

@WebServlet(name = "UserContactsServlet",urlPatterns = "/userContacts")
public class UserContactsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String json =request.getParameter("json");
        User user= JSONObject.parseObject(json,User.class);
        /*Dao dao=new Dao();
        UserMapper mapper= dao.getMapper(UserMapper.class);*/
        UserMapper mapper= DaoUtils.getMapper(UserMapper.class);
        /*
        ArrayList<Contact> contacts=mapper.getContacts2(user);
        for(int i=0;i<contacts.size();i++){
            Contact contact=contacts.get(i);
            if(contact.getType()==0)
                contact=mapper.getUserContact(contact);
            else
                contact=mapper.getGroupContact(contact);
            contacts.get(i).setContact_name(contact.getContact_name());
            contacts.get(i).setContact_img(contact.getContact_img());
        }
        */
        try {
            ArrayList<Contact> contacts = mapper.getContacts(user);
            user.setContacts(contacts);
            user.setStatus(2);
            String result = JSONObject.toJSONString(user);
            System.out.println("获取联系人：" + result);
            response.getWriter().print(result);
        }
        finally {
            DaoUtils.close();
            //dao.closeSession();
        }
    }
}
