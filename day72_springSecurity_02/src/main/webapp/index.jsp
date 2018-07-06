<%--
  Created by IntelliJ IDEA.
  User: LeaderXiaoBinBin
  Date: 2018/6/30
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>springScurity入门</h2><br/>
<h3>配置步骤</h3><br/>
<h4>
    1.先导入坐标<br/>
    2.在web.xml中配置监听器  监听security创建<br/>
    3.在web.xml中配置委派过滤器<br/>
    4.在security中设置需要过滤的内容：<br/>
    （1）配置需要放行的文件<br/>
    （2）配置放行的静态资源<br/>
    （3）设置拦截，键需要拦截的页面拦截下来，跳转到登录页面<br/>
    （4）自己设置登录页面，如果登录成功，跳转到登录成功页面，如果登录失败，跳转到登录失败页面<br/>
    （5）设置跨越<br/>
    （6）设置虚拟的登录账户名和密码<br/>
</h4>
</body>
</html>
