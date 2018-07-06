package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheiam.domian.Product;

;import java.util.List;

public interface ProductService {
    /*查询所有*/
    public List<Product> finAll();

    /*新增用户*/
    void save(Product product);

    /*根据ID查询用户*/
    Product findById(String id);

    /*修改用户*/
    void update(Product product);

    /*删除用户*/
    void delete(String id);

    /*分页查询*/
    PageInfo<Product> pageAll(int pageNum, int pageSize);
}

