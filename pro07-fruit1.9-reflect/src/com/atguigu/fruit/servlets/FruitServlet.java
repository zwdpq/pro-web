package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.Util.StringUtil;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }


        Method[] methods = FruitServlet.class.getDeclaredMethods();
        try {
        for (Method m: methods) {
            String name = m.getName();
            if (operate.equalsIgnoreCase(m.getName())){
                    m.invoke(this,req,resp);
                    return; //退出循环
            }
        }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("operate参数无效或有错");
    }

    private void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer pageNo = 1;
        String keyword = null,oper = null;
        HttpSession session = req.getSession();
        FruitDao fruitDao = new FruitDaoImpl();

        oper = req.getParameter("oper");
        if (StringUtil.isNotEmpty(oper) && "serach".equalsIgnoreCase(oper)){
            //点击表单查询过来的情况
            //页面均从首页开始显示
            pageNo = 1;

            keyword = req.getParameter("keyword");
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
        }else {
            //点击分页按钮或直接url访问的情况
            String pageNoStr = req.getParameter("pageNo");
            //排除掉进入主页后第一次点击分页按钮的情况
            if (StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            keyword = (String)session.getAttribute("keyword");
            if (StringUtil.isEmpty(keyword)){
                keyword = "";
            }
        }
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("keyword", keyword);
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

    private void addFruit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FruitDao fruitDao = new FruitDaoImpl();

        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String fname = req.getParameter("fname");
        String remark = req.getParameter("remark");

        fruitDao.addFruit(fname,price,fcount,remark);

        resp.sendRedirect("fruit.do");
    }

    private void delFruit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FruitDao fruitDao = new FruitDaoImpl();

        Integer fid = Integer.parseInt(req.getParameter("fid"));
        fruitDao.delFruit(fid);

        resp.sendRedirect("fruit.do");
    }
    private void editFruit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FruitDao fruitDao = new FruitDaoImpl();

        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            Fruit fruit = fruitDao.getFruitById(fidStr);
            req.setAttribute("fruit",fruit);
            super.processTemplate("edit",req,resp);
        }
    }
    private void updateFruit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FruitDao fruitDao = new FruitDaoImpl();

        Integer fid = Integer.parseInt(req.getParameter("fid"));
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String fname = req.getParameter("fname");
        String remark = req.getParameter("remark");

        fruitDao.updateInfo(new Fruit(fid,fname,price,fcount,remark));
        //为了能够在页面上显示修改后的数据 需要使用重定向而非转发
        resp.sendRedirect("fruit.do");
    }
}
