package com.itheima.dao;

import com.itheiam.domian.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface SysLogDao {

    @Insert("insert into sys_log (visitTime,username,ip,method) values(#{visitTime},#{username},#{ip},#{method})")
    void save(SysLog sysLog);
}
