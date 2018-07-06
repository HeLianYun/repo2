package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheiam.domian.Product;
import com.itheima.service.ProductService;
import com.itheima.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    //   注入dao
    @Autowired
    private ProductDao productDao;

    /*查询所有*/
    public List<Product> finAll() {
        return productDao.finAll();
    }

    /*新增用户*/
    public void save(Product product) {
        productDao.save(product);
    }

    /*修改用户*/
    public Product findById(String id) {
        return productDao.findById(id);
    }

    /*修改用户*/
    public void update(Product product) {
        productDao.update(product);
    }

    /*删除用户*/
    public void delete(String id) {
        productDao.delete(id);
    }

    /*分页查询商品信息*/
    public PageInfo<Product> pageAll(int pageNum, int pageSize) {
        /*设置当前页  和条数*/
        PageHelper.startPage(pageNum, pageSize);
        /*获得查询出所有的集合*/
        List<Product> products = productDao.finAll();
        /*将分页数据返回给页面*/
        PageInfo<Product> productPageInfo = new PageInfo<>(products);
        return productPageInfo;
    }
}
