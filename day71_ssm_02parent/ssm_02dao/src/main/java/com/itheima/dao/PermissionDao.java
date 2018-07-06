package com.itheima.dao;

import com.itheiam.domian.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select sp.*  from sys_role_permission srp,sys_permission sp where srp.permissionid=sp.id and srp.roleid=#{id}")
    List<Permission> findByRoleId(String id);
}
