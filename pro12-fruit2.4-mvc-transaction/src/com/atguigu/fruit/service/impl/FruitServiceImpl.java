package com.atguigu.fruit.service.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.fruit.service.FruitService;
import com.atguigu.myssm.basedao.ConnUtil;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null ;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        //此处是为了验证每一次事务的Connection是否为同一个
        //System.out.println("getFruitList ->" +ConnUtil.getConnection());
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
        /*
        此处为了测试事务的回滚是否成功  故意在新增Fruit的代码之后调用了更新Fruit的代码 然后这个更新Fruit所执行的底层sql是错误的 从而产生错误进而导致回滚
         */
       /* Fruit fruit2 = fruitDAO.getFruitByFid(2);
        fruit2.setFcount(99);
        fruitDAO.updateFruit(fruit2);*/
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        //此处是为了验证每一次事务的Connection是否为同一个
        //System.out.println("getPageCount ->" +ConnUtil.getConnection());
        int count = fruitDAO.getFruitCount(keyword);
        int pageCount = (count+5-1)/5 ;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
