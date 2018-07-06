package com.itheima.controller;

import com.itheiam.domian.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*系统日志的切面类*/
@Aspect  //声明切面类
@Component    //组件  用于声明
public class SysLogAspect {
    /*注入request作用域对象*/
    @Autowired
    private HttpServletRequest request;

    //操作方法名称
    private String meuthodName;

    @Autowired
    private SysLogService sysLogService;

    @Before("execution(public * com.itheima.controller.*Controller.*(..))")
    public void logBefre(JoinPoint pp) {
        System.out.println("前置通知");
//   获取到目标对象，获取到controller对象
        Class clazz = pp.getTarget().getClass();
//        获取内容   获取类名
        String clazzname = clazz.getSimpleName();
        System.out.println("类名" + clazzname);
//        获取方法名称
        String name = pp.getSignature().getName();
//        拼接正在操作的方法名和类名
        meuthodName = clazzname + "." + name;

    }

    @After("execution(public * com.itheima.controller.*Controller.*(..))")
    public void logAfter() {
        System.out.println("后置通知");
        SysLog sysLog = new SysLog();
        //        设置时间
        sysLog.setVisitTime(new Date());

        //        设置ip  从request域对象中获取   先要将ip注入到aoc容器中  配置request监听器对象
        String ip = request.getRemoteAddr();
        sysLog.setIp(ip);

        //  从security上下文对象中获取
        SecurityContext context = SecurityContextHolder.getContext();
        //获取authentication
        Authentication authentication = context.getAuthentication();
        //获取user对象
        User user = (User) authentication.getPrincipal();
        // 设置用户名称
        sysLog.setUsername(user.getUsername());
        //设置当前正在操作的方法
        sysLog.setMethod(meuthodName);
//        调用select保存日志
        sysLogService.save(sysLog);
    }
}
