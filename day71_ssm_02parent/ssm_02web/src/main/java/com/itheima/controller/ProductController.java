package com.itheima.controller;


import com.github.pagehelper.PageInfo;
import com.itheiam.domian.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
/*访问这个类必须是满足以下的角色  java提供的类*/
@RolesAllowed("ROLE_USER")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*查询所有*/
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        List<Product> products = productService.finAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("plist", products);
        mv.setViewName("product-list");
        return mv;
    }

    /*跳转到新增用户*/
    @RequestMapping("xinzen.do")
    public String xinzen() {
        return "product-add";
    }

    /*新增用户*/
    @RequestMapping("save.do")
    public String save(Product product) {
        productService.save(product);
//重定向
        return "redirect:/product/findAll.do";
    }

    /*跳转到修改用户页面*/
    @RequestMapping("intiUpdate.do")
    public String intiUpdate(String id, Model model) throws Exception {
        Product product = productService.findById(id);
//        把product存如request对象中
        model.addAttribute("product", product);

        return "product-update";
    }

    /*修改用户*/
    @RequestMapping("update.do")
    public String update(Product product) {
        productService.update(product);
        return "redirect:/product/findAll.do";
    }

    /*删除用户*/
    @RequestMapping("delete.do")
    public String delete(String id) {
        productService.delete(id);
        return "redirect:/product/findAll.do";
    }


    /*分页查询用户*/
    @RequestMapping("pageAll.do")
    public ModelAndView pageAll(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "5") int pageSize) {
        PageInfo<Product> product = productService.pageAll(pageNum, pageSize);

        ModelAndView mv = new ModelAndView();
        mv.addObject("product", product);
        mv.setViewName("product-list");
        return mv;
    }
}
