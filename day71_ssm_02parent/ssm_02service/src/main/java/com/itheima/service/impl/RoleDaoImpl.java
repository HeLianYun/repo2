package com.itheima.service.impl;

import com.itheiam.domian.Role;
import com.itheima.dao.RoleDao;
import com.itheima.dao.SysUserDao;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDaoImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /*查询所有角色*/
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
