package com.itheima.dao;

import com.itheiam.domian.Order;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {

    /*查询所有*/
    @Select("select * from orders")
    @Results(value = {
        @Result(property = "product" ,column = "productId",
                one = @One(select = "com.itheima.dao.ProductDao.findById"))
    })

    List<Order> findAll();
}
