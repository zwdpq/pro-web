package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addFruit.do")
public class AddServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String fname = req.getParameter("fname");
        String remark = req.getParameter("remark");

        fruitDao.addFruit(fname,price,fcount,remark);

        resp.sendRedirect("index");
    }
}
