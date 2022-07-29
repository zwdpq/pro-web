package com.atguigu.fruit.controllers;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.Util.StringUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
public class FruitController {
    private FruitDao fruitDao = new FruitDaoImpl();

    private String index(String keyword,String oper,Integer pageNo,HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (pageNo == null) {
            pageNo = 1;
        }
        if (StringUtil.isNotEmpty(oper) && "serach".equalsIgnoreCase(oper)) {
            //点击表单查询过来的情况
            //页面均从首页开始显示
            pageNo = 1;
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
        } else {
            keyword = (String) session.getAttribute("keyword");
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
        }
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("keyword", keyword);
        //获取总页数
        Integer totalCount = fruitDao.getTotalCount(keyword);
        Integer pageTotalNo = (totalCount + 5 - 1) / 5;
        session.setAttribute("pageTotalNo", pageTotalNo);
        //获取商品数据
        List fruits = fruitDao.getFruitsByPageNo((pageNo - 1) * 5, keyword);
        session.setAttribute("fruits", fruits);
        return "index";
    }

    private String addFruit(Integer price,Integer fcount,String fname,String remark) {
        fruitDao.addFruit(fname, price, fcount, remark);
        return "redirect:fruit.do";
    }

    private String delFruit(Integer fid) {
        if (fid != null) {
            fruitDao.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String editFruit(Integer fid,HttpServletRequest req) {
        if (fid != null) {
            Fruit fruit = fruitDao.getFruitById(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String updateFruit(Integer fid,Integer price,Integer fcount,String fname,String remark) {
        fruitDao.updateInfo(new Fruit(fid, fname, price, fcount, remark));
        return "redirect:fruit.do";
    }
}
