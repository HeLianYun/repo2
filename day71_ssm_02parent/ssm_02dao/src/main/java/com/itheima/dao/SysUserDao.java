package com.itheima.dao;

import com.itheiam.domian.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserDao {

    /*通过用户名查询*/
    @Select("select * from sys_user where username=#{username}")
    @Results(
            /*第一个参数是实体类中的属性  第二个参数是表中的字段  第三个参数是sql语句*/
            @Result(property = "roles", column = "id", many = @Many(select = "com.itheima.dao.RoleDao.findByUserId"))
    )
    SysUser findByUsername(String username);

    /*查询所有*/
    @Select("select * from sys_user")
    List<SysUser> findAll();

    /*新增用户*/
    @Insert("insert into sys_user (username,email,password,phoneNum,status) " +
            "values (#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(SysUser sysUser);

    /*查看详情*/
    @Select("select * from sys_user where id=#{id}")
    @Results({
            /*第一个参数是实体类中的属性  第二个参数是表中的字段  第三个参数是sql语句*/
            @Result(property = "id", column = "id", id = true),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", many = @Many(select = "com.itheima.dao.RoleDao.findByUserId"))
    })
    SysUser findById(String id);

    /*根据ID删除用户角色*/
    @Delete("delete from sys_user_role where userId=#{userId}")
    void delete(String userId);

    /*两个或者两个以上的参数  那么参数前面要加@param注解
    根据ID添加用户角色*/
    @Insert("insert into sys_user_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
