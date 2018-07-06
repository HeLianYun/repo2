package com.itheima.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheiam.domian.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/order")
/*访问这个类必须是满足以下的角色  java提供的类*/
@RolesAllowed("ROLE_USER")
public class OrderController {

    @Autowired
    private OrderService orderService;

   /* 查询所有*/
    @RequestMapping("findAll.do")
    private ModelAndView findAll() {
        List<Order> list = orderService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("olist", list);
        mv.setViewName("order-list");
        return mv;
    }

    /*分页查询*/
    @RequestMapping("findPage.do")

    public ModelAndView pageAll(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "5") int pageSize) {
        /*根据当前页和每页查询总条数查询数据*/
        PageInfo<Order> page = orderService.pageAll(pageNum, pageSize);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page", page);
        mv.setViewName("order-list");
        return mv;
    }
}
