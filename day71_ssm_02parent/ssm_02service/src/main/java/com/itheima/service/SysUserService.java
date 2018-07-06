package com.itheima.service;

import com.itheiam.domian.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends UserDetailsService {
    /*查询所有*/
    List<SysUser> findAll();

    /*增加用户*/
    void save(SysUser sysUser);

    /*查看详情*/
    SysUser findById(String id);

    void addRoleToUser(String userId, String[] ids);
}


