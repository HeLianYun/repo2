package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheiam.domian.Order;

import java.util.List;

public interface OrderService {
    /*查询所有*/
    List<Order> findAll();


    /*分页查询*/
    PageInfo<Order> pageAll(int pageNum, int pageSize);

}
