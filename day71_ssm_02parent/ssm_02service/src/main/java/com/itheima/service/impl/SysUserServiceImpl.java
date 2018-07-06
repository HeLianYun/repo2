package com.itheima.service.impl;

import com.itheiam.domian.Role;
import com.itheiam.domian.SysUser;
import com.itheima.dao.SysUserDao;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*spring-Security框架提供的从数据库获取用户名及密码的方法*/
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*创建用户  用于存入角色*/
        List<GrantedAuthority> list = new ArrayList<>();
       /*入门案例所需  角色是写死的
       list.add(new SimpleGrantedAuthority("ROLE_USER"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));*/
        /*通过数据库查询数据获得用户名和密码*/
        SysUser sysUser = sysUserDao.findByUsername(username);

//      通过SysUser获取角色表
        List<Role> roles = sysUser.getRoles();
//        当角色表非空时 遍历角色集合
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
//                拿到角色  添加到集合中
                list.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        /*创建user对象  采用明文的方式*/
//        User user = new User(sysUser.getUsername(), "{noop}" + sysUser.getPassword(), list);
//        采用密文的方式存储
        User user = new User(sysUser.getUsername(), sysUser.getPassword(), list);
        return user;
    }

    /*查询所有*/
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    @Override
    public void save(SysUser sysUser) {

        /*从用户中拿出数据*/
        /* passwordEncoder.encode(sysUser.getPassword());*/
        /*将拿出来的数据封装到用户里面*/
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserDao.save(sysUser);
    }

    @Override
    public SysUser findById(String id) {
        return sysUserDao.findById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) {
        /*根据ID删除用户角色*/
        sysUserDao.delete(userId);
        /*增加用户角色*/
        if(ids!=null){
            for (String roleId : ids) {
                sysUserDao.addRoleToUser(userId,roleId);
            }
        }
    }
}
