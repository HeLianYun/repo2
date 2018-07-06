package com.itheima.dao;

import com.itheiam.domian.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductDao {
    /*查询所有*/
    @Select("select * from product")
    public List<Product> finAll();

    /*新增用户*/
   @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)" +
           " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus}) ")
    void save(Product product);

   /*修改用户  先根据ID查询用户*/
   @Select("select * from product where id=#{id}")
   Product findById(String id);

   /*修改用户*/
   @Update({"update product  set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}"})
    void update(Product product);

   /*删除用户*/
   @Delete("delete product where id = #{id}")
    void delete(String id);
}
