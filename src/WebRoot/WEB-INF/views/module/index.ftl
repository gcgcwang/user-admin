<%@ page language="java"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<%@ include file="/WEB-INF/views/head.jsp" %>
<%@ include file="/WEB-INF/views/cssHelper.jsp" %>
<%@ include file="/WEB-INF/views/jsHelper.jsp" %>
<script type="text/javascript">

	function onClick(){
	$.ajax({
		url:"${basePath}/sysResource/remove.do",
		type:"POST",
		data:{
			'id':453,
			'name':'name04',
			'code':'4号'
		},
		success:function(data){
			console.log(data);
		},	
		error:function(){
		console.log("error");
		}
	})
	}

</script>
</head>
<body >
<div id="app" class="">
<div class="panel ">
<div class="panel-heading">
	<form class="form-inline ">
  <div class="form-group ">
    <label for="appName"> 应用名：</label>
    <input type="text" class="form-control  input-sm" id="appName" >
  </div>
  <div class="form-group ">
    <label for="appCode"> 应用代码：</label>
    <input type="text" class="form-control  input-sm"  id="appCode"  >
  </div>
   <div class="form-group ">
   <shiro:hasPermission name="app:select">
  	<button type="submit" class="btn btn-primary btn-sm">查询</button>
  </shiro:hasPermission>
   <shiro:hasPermission name="app:delete">
  	<button type="button" class="btn btn-primary btn-sm">删除</button>
  </shiro:hasPermission>
     	<button onclick="onClick()" type="button" class="btn btn-primary btn-sm">合同</button>
   
  </div>
</form>
</div>
<div class="panel-body">
<div class="row">
  <table id="dg" class="table table-condensed  table-bordered table-hover col-sm-12">
                <thead  >
                <tr class="">
                	<th class="text-center" width=30px ></th>
                	<th width=30px></th>
                  <th>应用代码</th>
                  <th>应用名</th>
                  <th>图标</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                 <tr  :class="{'active':curr_app==app}"
                  v-on:click="setCurrApp(app)" v-for="(app,index) in  appPager.rows">
                  <td class="text-center">{{index+1}}</td>
                  <td class="text-center"><input type="checkbox" name="app"  v-bind:checked="curr_app==app" /></td>
                  <td>{{app.appCode}}</td>
                  <td>{{app.appName}}</td>
                  <td>{{app.icon}}</td>
                  <td>{{app.valid==1?'有效':'无效'}}</td>
                   <td><a v-if="app.valid==1" class="btn btn-warning btn-xs">
                   <span class="fa fa-wrench"></span>修改</a></td>
                </tr>
                </tbody>
                </table>
                </div>
                <div class="row">
                <div class="col-sm-7" >
                	第  <span class="text-primary">{{appPager.pageNum}}</span>  页,每页显示<select v-on:change="fetchAppList(1,appPager.pageSize)" v-model="appPager.pageSize">
                	<option value="2" >2</option>
                	<option value="5" >5</option>
                	<option value="10" >10</option>
                	<option value="15" selected>15</option>
                	<option value="30">30</option>
                	<option value="50">50</option>
                	</select>
                	,共  <span class="text-primary">{{appPager.total}}</span> 
                	条数据,当前显示 <span class="text-primary">{{appPager.beginNum}}~{{appPager.endNum>appPager.total?appPager.total:appPager.endNum}}</span>
                	
                </div>
                  <ul class="pagination pagination-sm  col-sm-5">
                 <li  >
				      <button class="btn btn-default btn-sm" v-bind:disabled="appPager.pageNum<=appPager.beginPageNum"  v-on:click="fetchAppList(appPager.pageNum-1,appPager.pageSize)">
				        <span ><<</span>
				      </button>
				    </li>
				     <li >
				      <button class="btn btn-default btn-sm" v-on:click="fetchAppList(1,appPager.pageSize)" 
				      v-bind:disabled="appPager.pageNum<=appPager.beginPageNum">
				        <span >首页</span>
				      </button>
				    </li>
				    <li  v-for="num in appPager.endPageNum" v-if="num >= appPager.beginPageNum">
				     <button :class="{'active':num==appPager.pageNum}" class="btn btn-default btn-sm" v-on:click="fetchAppList(num,appPager.pageSize)" >{{num}}</button></li>
				    <li>
				     <li >
				      <button class="btn btn-default btn-sm" v-bind:disabled="appPager.pageNum>=appPager.endPageNum"  v-on:click="fetchAppList(appPager.facPageLength,appPager.pageSize)" >
				        <span >尾页</span>
				      </button>
				    </li>
				    <li >
				      <button class="btn btn-default btn-sm" v-bind:disabled="appPager.pageNum>=appPager.endPageNum" v-on:click="fetchAppList(appPager.pageNum+1,appPager.pageSize)">
				        <span>>></span>
				      </button>
   			 </li>
                </ul>
                </div>
              </div>
          <!-- / pane-body -->
             </div>
               
               </div>
</body>
<script type="text/javascript">
var vm = new Vue({
	el:"#app",
	data:{
		curr_app:[],
		appPager:{
			pageNum:1,
			pageSize:15,
			total:0,
			rows:[],
			beginNum:0,
			endNum:0,
			beginPageNum:0,
			endPageNum:0,
			facPageLength:1
		}
	},
	methods:{
		fetchAppList:function(num,size){
			this.$http.post('${basePath}/app/findPageApp.do',{pageNum:num,pageSize:size},{emulateJSON:true}).then(
			function(res){
				this.appPager=res.data;
			}, function(res){
			
			});
		},
		setCurrApp:function(app){
			this.curr_app=app;
		}
	},
	computed:{
	},
	watch:{
	
	},
	mounted:function() {
        this.fetchAppList(1,this.appPager.pageSize);
      }

});
</script>
</html>