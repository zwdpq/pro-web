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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer pageNo;
        String pageNoStr,keyword,oper;
        FruitDao fruitDao = new FruitDaoImpl();
        HttpSession session = req.getSession();
        //关键字搜索
        oper = req.getParameter("oper");
        keyword = req.getParameter("keyword");
        if (StringUtil.isNotEmpty(oper) && "serach".equalsIgnoreCase(oper)){
            //点击表单查询过来的情况
            //搜索框情况分类
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }else {
            //点击分页按钮或直接url访问的情况
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj == null){
                keyword = "";
            }else {
                keyword = (String)keywordObj;
            }
        }
        //进行分页操作
        pageNoStr = req.getParameter("pageNo");
        if (pageNoStr == null)pageNoStr = "1";
        pageNo = Integer.parseInt(pageNoStr);
        session.setAttribute("pageNo", pageNo);
        //获取总页数
        Integer totalCount = fruitDao.getTotalCount(keyword);
        Integer pageTotalNo = (totalCount + 5 - 1)/5;
        session.setAttribute("pageTotalNo",pageTotalNo);
        //获取商品数据
        List fruits = fruitDao.getFruitsByPageNo((pageNo -1)*5,keyword);
        session.setAttribute("fruits", fruits);
        // 行代码相当于进行了一次转发
        super.processTemplate("index",req,resp);
    }
}
