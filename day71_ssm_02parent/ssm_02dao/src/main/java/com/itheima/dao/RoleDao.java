package com.itheima.dao;

import com.itheiam.domian.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
    @Select("select r.* from sys_user_role sur ,sys_role r where  sur.userid=r.id and sur.userid=#{id}")
    @Results(
            @Result(property = "permissions", column = "id", many = @Many(select = "com.itheima.dao.PermissionDao.findByRoleId"))
    )
    List<Role> findByUserId(String id);

    /*查询所有角色*/
    @Select("select * from sys_role")
    List<Role> findAll();
}
