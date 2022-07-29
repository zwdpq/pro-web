package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.BaseDao;

import java.util.List;

public class FruitDaoImpl  extends BaseDao<Fruit> implements FruitDao{
    @Override
    public List<Fruit> getFruits() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public Fruit getFruitById(String fid) {
        return super.load("select * from t_fruit where fid = ?", fid);
    }

    @Override
    public void updateInfo(Fruit fruit) {
        super.executeUpdate("update t_fruit set fname = ? ,price = ?, fcount = ? ,remark = ? where fid = ?", fruit.getFname(),fruit.getPrice()
        ,fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.executeUpdate("delete from t_fruit where fid = ? ", fid);
    }

    @Override
    public void addFruit(String fname, Integer price, Integer fcount, String remark) {
        super.executeUpdate("insert into t_fruit values(0,?,?,?,?)",fname,price,fcount,remark);
    }

    @Override
    public int getTotalCount() {
        return ((Long)super.executeComplexQuery("select count(*) from t_fruit")[0]).intValue();
    }

    @Override
    public List<Fruit> getFruitsByPageNo(Integer pageStart) {
        return super.executeQuery("select * from t_fruit limit ?,5", pageStart);
    }
}
