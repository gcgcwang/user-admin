<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<%@ include file="/WEB-INF/views/head.jsp" %>
<%@ include file="/WEB-INF/views/cssHelper.jsp" %>
<%@ include file="/WEB-INF/views/jsHelper.jsp" %>

</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <b>登陆</b>LOGIN
  </div>
  <div class="login-box-body">
    <p class="login-box-msg">前台登陆界面</p>

    <form action="${basePath}/sys/ValidLogin.do" method="post">
      <div class="form-group has-feedback">
        <input type="text" name="loginName" class="form-control" placeholder="用户名">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" name="password" class="form-control" placeholder="密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox">
            <label>
           <input name="rememberMe" type="checkbox">记住我
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

    <div class="social-auth-links text-center">
    <p class="bg-warning text-danger">${msg}</p>
    </div>
    <!-- /.social-auth-links -->
	<div class=row>
	<div class="col-xs-4 text-left" >
    <a href="#"><i class="fa fa-info-circle"></i> 忘记密码</a>
    </div>
    
    <div class="col-xs-4 col-xs-offset-4 text-right">
    <a href="#" class="text-center"><i class="fa fa-tag"></i>注册账号</a>
	</div>
  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<script>
</script>

</body>
</html>