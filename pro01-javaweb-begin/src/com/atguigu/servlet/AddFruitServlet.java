package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AddFruitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        /*
        Tomcat8之前对于get方式汉字乱码的处理
         String xxx = req.getParameter("xxx");
        byte[] xxxBytes = xxx.getBytes
                ("ISO-8859-1");
        xxx = new String(xxxBytes, "UTF-8");
         */
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        String fnumber = req.getParameter("fnumber");
        String fprice = req.getParameter("fprice");

        System.out.println(fname + " " + fnumber + " " + fprice);
    }
}
