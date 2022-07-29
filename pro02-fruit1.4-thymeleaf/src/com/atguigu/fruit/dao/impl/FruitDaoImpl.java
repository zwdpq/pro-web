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
}
