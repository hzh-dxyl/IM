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
import java.io.*;
import java.util.Random;

@WebServlet(name = "UserRegisterServlet", urlPatterns = "/userRegister")
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        String json = request.getParameter("json");
        User user = JSONObject.parseObject(json, User.class);
        user.setStatus(2);
        /*Dao dao = new Dao();
        UserMapper mapper = (UserMapper) dao.getMapper(UserMapper.class);*/
        UserMapper mapper= DaoUtils.getMapper(UserMapper.class);
        try {
            int exist = mapper.checkPhone(user);
            if (exist == 1) {
                response.getWriter().print("{msg:\"already exist\"}");
                System.out.println("注册账号已存在");
                DaoUtils.close();
                return;
            }
            //判断是否上传了头像
            if (user.getImg_data() == null) {//使用默认头像
                int random = new Random().nextInt(4);
                user.setUser_img("default" + random + ".png");
                user.setImg_hex(Value.defaultHeadMd5[random]);
            } else {
                String md5Hex = DigestUtils.md5Hex(user.getImg_data());
                String imgName = mapper.checkHead(md5Hex);
                user.setImg_hex(md5Hex);
                if (imgName == null) {
                    user.setUser_img(md5Hex+"."+user.getUser_img().split("\\.")[1]);
                    FileUtils.bytesToFile(user.getImg_data(),Value.imgLocalPath+"head/",user.getUser_img());
                } else {
                    user.setUser_img(imgName);
                }
            }
            int result = mapper.createUser(user);
            if (result == 1) {
                response.getWriter().print("{msg:\"register success\"}");
                System.out.println("注册成功：" + JSONObject.toJSONString(user));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.getWriter().print("{msg:\"register failed\"}");
        }
        finally {
            //dao.closeSession();
            DaoUtils.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
