package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.myssm.Util.StringUtil;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet("/index")
public class FruitServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo;
        String pageNoStr;
        FruitDao fruitDao = new FruitDaoImpl();

        //进行分页操作
        HttpSession session = req.getSession();
        pageNoStr = req.getParameter("pageNo");
        if (pageNoStr == null)pageNoStr = "1";
        pageNo = Integer.parseInt(pageNoStr);
        session.setAttribute("pageNo", pageNo);
        //获取总页数
        Integer totalCount = fruitDao.getTotalCount();
        Integer pageTotalNo = (totalCount + 5 - 1)/5;
        session.setAttribute("pageTotalNo",pageTotalNo);

        List fruits = fruitDao.getFruitsByPageNo((pageNo -1)*5);
        session.setAttribute("fruits", fruits);
        // 行代码相当于进行了一次转发
        super.processTemplate("index",req,resp);
    }
}
