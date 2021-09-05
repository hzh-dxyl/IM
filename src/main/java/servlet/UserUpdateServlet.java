package servlet;

import bean.User;
import bean.Value;
import com.alibaba.fastjson.JSONObject;
import dao.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import utils.DaoUtils;
import utils.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserUpdateServlet",urlPatterns = "/userUpdate")
public class UserUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        request.setCharacterEncoding("utf-8");
        String json=request.getParameter("json");
        System.out.println("修改自己信息："+json);
        User user= JSONObject.parseObject(json,User.class);
        System.out.println(user.getUser_gender());
        UserMapper mapper= DaoUtils.getMapper(UserMapper.class);
        try {
            if(user.getImg_data()!=null) {
                String md5Hex = DigestUtils.md5Hex(user.getImg_data());
                String imgName=mapper.checkHead(md5Hex);
                if (imgName == null) {
                    user.setUser_img(md5Hex+"."+user.getUser_img().split("\\.")[1]);
                    FileUtils.bytesToFile(user.getImg_data(), Value.imgLocalPath+"head/",user.getUser_img());
                } else {
                    user.setUser_img(imgName);
                }
            }
            int i=mapper.updateUser(user);
            if(i==1) response.getWriter().print("{msg:\"update success\"}");
            else response.getWriter().print("{msg:\"update failed\"}");
        }
        catch (Exception e){
            e.printStackTrace();
            response.getWriter().print("{msg:\"update failed\"}");
        }
        finally {
            DaoUtils.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
