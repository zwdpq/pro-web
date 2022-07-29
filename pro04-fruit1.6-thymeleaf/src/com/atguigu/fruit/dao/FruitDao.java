package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDao {
    List getFruits();

    Fruit getFruitById(String fid);

    void updateInfo(Fruit fruit);

    void delFruit(Integer fid);

    void addFruit(String fname,Integer price,Integer fcount,String remark);

    int getTotalCount();

    List getFruitsByPageNo(Integer pageStart);
}
