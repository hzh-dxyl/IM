package servlet;

import bean.Value;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ImageServlet",urlPatterns ={ "/image","*.png","*.jpg","*.jpeg"})
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url=request.getServletPath().split("images/")[1];
        System.out.println("打开图片："+url);
        String pattern=url.split("\\.")[1];
        String file = Value.imgLocalPath+url;
        FileInputStream fileInputStream = new FileInputStream(file);
        int size = fileInputStream.available();
        byte[] bytes = new byte[size];
        fileInputStream.read(bytes);
        fileInputStream.close();
        response.setContentType("image/"+pattern+";charset=utf-8");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.close();
    }
}
