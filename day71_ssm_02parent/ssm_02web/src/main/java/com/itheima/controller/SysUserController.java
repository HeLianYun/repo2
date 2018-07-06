package com.itheima.controller;


import com.itheiam.domian.Role;
import com.itheiam.domian.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/sysUser")
/*访问这个类必须满足 盖饭发所拥有的角色  java提供类*/
//@RolesAllowed("ROLE_ADMIN")
//<!--security框架提供的设置权限的方法-->
//@Secured(value = {"ROLE_ADMIN"})
//    <!--spring 框架提供的设置权限的方法-->
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class SysUserController {

    /*注入用户的信息*/
    @Autowired
    private SysUserService sysUserService;
    /*注入角色的所有信息*/
    @Autowired
    private RoleService roleService;

    /*查询所有*/
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll() {
        /*从SecurityContext中获取上下文对象*/
        SecurityContext securityContext = SecurityContextHolder.getContext();
        /*获取认证对象*/
        Authentication authentication = securityContext.getAuthentication();
        /*获取user（主角）对象*/
        User user = (User) authentication.getPrincipal();
        /*user对象中获取用户名*/
        System.out.println("用户名称叫美美哒：" + user.getUsername());

        List<SysUser> list = sysUserService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("ulist", list);
        mv.setViewName("user-list");
        return mv;
    }

    /*增加用户前先条到controller页面*/
    @RequestMapping(path = "userSave.do")
    public String userSave() {
        return "user-add";
    }

    /*增加用户方法一  不经过controlice方法*/
    @RequestMapping("save.do")
    public String save(SysUser sysUser) {
        sysUserService.save(sysUser);
        return "redirect:/sysUser/findAll.do";
    }

    /*查看详情*/
    @RequestMapping("findById.do")
    public ModelAndView findById(String id) {
        SysUser sysUser = sysUserService.findById(id);
        ModelAndView md = new ModelAndView();
        md.addObject("sysUser", sysUser);
        md.setViewName("user-show");
        return md;
    }
    /*需求：查询用户角色 在自己角色的复选框中打勾
     * 需要查询当前用户  查询用户角色  查询所有的角色*/

    @RequestMapping("initAddRole.do")
    public ModelAndView initAndRole(String id) {
//       根据id查询当前
        SysUser sysUser = sysUserService.findById(id);
        sysUser.setId(id);
//       根据用户查询出该用户所包含的所有角色
        List<Role> roles = sysUser.getRoles();
//        将用户的所有角色拼接字符串  链接起来
        StringBuffer sb = new StringBuffer();
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                sb.append(role.getRoleName());
                sb.append(",");
            }
        }
//        得到所拼接后的字符串
        String rolesNameStr = sb.toString();
        System.out.println(rolesNameStr);
//       查询所有的角色
        List<Role> roleList = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", sysUser);
//        用户角色拼接的字符串
        mv.addObject("rolesNameStr", rolesNameStr);
//        系统当中所有的角色
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    /*拿到用户的主键和说要封装的ID*/
    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(String userId, String[] ids) {
//        用户拿不到主键ID的原因是  用户表中的id和角色表中的ID一样重名所导致的
        System.out.println("主键：" + userId);
        System.out.println(Arrays.toString(ids));
        sysUserService.addRoleToUser(userId, ids);
        return "redirect:/sysUser/findAll.do";
    }
}
