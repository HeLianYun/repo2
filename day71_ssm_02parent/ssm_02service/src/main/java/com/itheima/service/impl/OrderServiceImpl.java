package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheiam.domian.Order;
import com.itheima.dao.OrderDao;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAll() {

        return orderDao.findAll();
    }

    /* 分页查询*/
    public PageInfo<Order> pageAll(int pageNum, int pageSize) {
        /*设置当前页和条数*/
       PageHelper.startPage(pageNum, pageSize);
        /*查询所有数据*/
        List<Order> olist = orderDao.findAll();
        /*创建pageInfo对象返回*/
        PageInfo<Order> page = new PageInfo<>(olist);
        return page;
    }
}
