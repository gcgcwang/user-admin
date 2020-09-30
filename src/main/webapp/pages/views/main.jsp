<%@ page language="java"  pageEncoding="UTF-8"%>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
  <head>
    
    <title>主页</title>
    <%@ include file="/pages/views/head.jsp"%>
    <%@ include file="/pages/views/cssHelper.jsp"%>
     <%@ include file="/pages/views/jsHelper.jsp"%>
     <style type="text/css">
     .offset{
     	width:30px;
     }
     .onset{
     	margin-left:30px;
     }
     .active-app a{
     	color:	#D00000 !important;
     }
     .flex-ul{
    	 display: -webkit-flex; 
    	 display: -ms-flexbox;  
     	display：flex;
     }
     .flex-ul>li{
     	width: 100px;
     }
     </style>
  </head>
<script type="text/javascript">
</script>
 <body class="hold-transition skin-blue">
<!-- Site wrapper -->
<div id="appDiv">
<div class="layout-top-nav">
  <header class="main-header" >
        <nav class="navbar navbar-static-top"  >
            <div class="container" >
                <!-- 导航 -->
                <div class="collapse navbar-collapse pull-left" id="navbar-collapse" >
                    <ul class="nav navbar-nav">
                        <li id="hli1"><a  href="${basePath}/">首页</a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
                <!-- Navbar Right Menu -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav" >
                        <li ><a  href="${basePath}/trainCourse/">Message</a></li>
                        <!--登录-->
						
                        <!-- 用户信息 -->
        <!--   用户 -->
                        <li class="dropdown user user-menu">
                            <!-- Menu Toggle Button -->
                            <a  href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <!-- The user image in the navbar-->
                                <img src='${ctx}' onerror="javascript:this.src='${ctx}/resources/img/fac.png'" class="user-image"
                                     alt="User Image">
                                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                <span class="hidden-xs"> 
                                <shiro:principal property="loginName"/></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- The user image in the menu -->
                                <li class="user-header">
                                    <img src='${ctx}/resources/img/fac.png'  onerror="javascript:this.src='${ctx}/resources/img/fac.png'"  class="img-circle"
                                         alt="User Image">
                                    <p>
                                     	<shiro:principal property="displayName"/>
                                        <small> <shiro:principal property="orgName"/></small>
                                    </p>
                                </li>
                                <!-- Menu Body -->
                                <li class="user-body">
                                    <div class="row">
                                        <div class="col-xs-6 text-center">
                                            <a  class="btn btn-app" href ="${basePath}/studyonline">
                                                <i class="fa fa-edit"></i> 线上学习
                                            </a>
                                        </div>
                                        <div class="col-xs-6 text-center">
                                            <a  class="btn btn-app" href="${basePath}/exam/findAll">
                                                <i class="fa fa-edit"></i> 线上考试
                                            </a>
                                        </div>
                                        <div class="col-xs-6 text-center">
                                            <a  class="btn btn-app" href="${basePath}/personal/information">
                                                <i class="fa fa-edit"></i> 个人资料
                                            </a>
                                        </div>
                                        <div class="col-xs-6 text-center">
                                            <a  class="btn btn-app" href="${basePath}/certificateHours/certificatelist">
                                                <i class="fa fa-edit"></i> 我的证书
                                            </a>
                                        </div>
s                                    </div>
                                    <!-- /.row -->
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-right">
                                        <a  href="${basePath}/sys/logout.do"  class="btn btn-default btn-flat">退出</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                 <!--登录begin --> 
     <!--     访客 -->
     <shiro:notAuthenticated>
            <li class="dropdown">
              <a href="#"  class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">登录 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu" >
                <li><a href="${basePath}/sys/toLogin.do">登录</a></li>
                
              </ul>
            </li>
       </shiro:notAuthenticated>
            <!--登录end -->   
                    <!--注册begin -->   
       
            <li class="dropdown">
              <a href="#"  class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">注册 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="${basePath}/personRegister">个人注册</a></li>
                 <li class="divider"></li>
                <li><a href="${basePath}/companyRegister">企业注册</a></li>
              </ul>
            </li>
      
            <!--注册end --> 
                    </ul>
                </div>
                <!-- /.navbar-custom-menu -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        <div id="appDiv"  class="navbar navbar-default" style="background:#5B2B4D;position:relative;"  >
          <span class="fa fa-chevron-left bg-primary" 
	   			 style="position:absolute;top:5px;left:30px;padding:4px 3px;border-radius:4px;"
	    		 @click="leftArrow"></span>
 		 <div class=" container">
			<ul class="nav navbar-nav" style="height:36px;overflow:hidden">
			 	  <li  v-for="(app,index) in appList" :class="{'active-app':index==app_index}"><a  href="javascript:;" v-on:click="loadMenus(app.appID,index)">{{app.appName}}</a></li>
			</ul>
		
 		 </div>
 		 <span class="fa fa-chevron-right bg-primary" 
	    	style="position:absolute;top:5px;right:30px;padding:4px 3px;border-radius:4px;"
	     @click="rightArrow"></span>
	</div>
    </header>
 </div>
<div class="wrapper">
	
  <aside   class="main-sidebar " style="padding-top:0 " :class="{'':isShowMenu,'offset':!isShowMenu}" >
      <div class="text-right" style="position:relative;top:3px;right:4px;padding:2px">
     	 <span style="padding:6px;" v-on:click="toggleShowMenu()" class="fa fa-bars text-primary fa-lg" :class="{'':isShowMenu,'fa-rotate-90':!isShowMenu}"></span>
      </div>
    <section class="sidebar"  :class="{'show':isShowMenu,' hide':!isShowMenu}">
      <ul class="sidebar-menu" data-widget="tree">
      
         <li v-for="rootMenu in rootMenus" class="treeview">
          <a href="#" >
            <i class="fa fa-dashboard"></i> <span>{{rootMenu.objectName}}</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
         
          <ul class="treeview-menu">
            <li v-for="conMenu in conMenus" v-if="conMenu.parentID==rootMenu.objectID"><a href="#" v-on:click="tabToCon(conMenu)"><i class="fa fa-circle-o"></i> {{conMenu.objectName}}</a></li>
			</ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>


  <!-- Content Wrapper. Contains page content -->
  <div  class="content-wrapper"   :class="{'':isShowMenu,'onset':!isShowMenu}"  >
	
	     <ul   class="nav nav-tabs flex-ul"
	      style="height:30px;position:relative;padding-left:30px;padding-right:30px;overflow:hidden"
	      >
	       <li  :class="{'active':!curr_tabMenu.link}" style="position:absolute;top:1px;left:2px;width:30px;">
	       <a style="padding:5px" title="回到首页" v-on:click="redirectToIndex()">
	       <span   class="fa  fa-home fa-lg"></span>
	    	</a></li>
	    	  <li style="position:absolute;top:1px;right:2px;width:30px;">
	       <a style="padding:5px" title="清空导航栏回到首页" v-on:click="emptyTabMenus()">
	       <span   class="fa  fa-remove fa-lg"></span>
	    	</a></li>
	    <li v-for="(tabMenu,index) in tabMenus" 
	   	 v-on:click="toggleTab(tabMenu)"
	   	 style="position:relative"
	     v-bind:class="{'active':tabMenu==curr_tabMenu}">
	    <a >{{tabMenu.objectName}} &nbsp;
	    <span v-on:click="closeTab(index)" style="position:absolute;top:5px;right:3px" class="fa  fa-close"></span>
	    </a></li>
		</ul>
    <iframe width="100%" frameborder="0" height="530px" scrolling="no" v-bind:src="basePath+(curr_tabMenu.link?curr_tabMenu.link:index_url)" style="background:#FFF"></iframe>
    
    
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer" style="padding:2px" :class="{'':isShowMenu,'onset':!isShowMenu}"> 
    <div class="pull-right hidden-xs">
      <b>design</b> simply
    </div>
    <strong>Copyright &copy; 2017-2018 <a href="#">Adminlte</a>.</strong> All rights
    reserved.
  </footer>

</div>
<!-- ./wrapper -->
</div>
<script type="text/javascript">

 new Vue({
   	el:"#appDiv",
   	data:{
   		index_url:'sys/toIndex.do',
   		basePath:'${basePath}'+'/',
   		isShowMenu:true,
   		isShowAppLeft:false,
   		isShowAppRight:false,
   		app_index:1,
   		curr_tabMenu:{},
 	  	appList:${appList},
 	  	rootMenus:${rootMenus},
   		conMenus:${conMenus},
   		tabMenus:[]
   	},
	methods:{
		loadMenus:function(appID,index){
			var me =this;
			  me.$http.post('${basePath}/sys/getMenusListByUser.do',{appID:appID},{emulateJSON:true}).then(function(res){
				 
				me.rootMenus=res.data.rootMenus;
				me.conMenus=res.data.conMenus;
				me.isShowMenu= true;
				me.app_index=index;
			}, function(res){
			
			});  
		
		},
		toggleShowMenu:function(){
   			this.isShowMenu= !this.isShowMenu;
   		},
   		tabToCon:function(conMenu){
   		var me =this;
	   		if(isInArray(me.tabMenus,conMenu)){
	   			return ;
	   		}
   	 	  me.tabMenus.push(conMenu);
   	 	  me.toggleTab(conMenu);
   		},
   		closeTab:function(index){
   			var me =this;
   			me.tabMenus.splice(index,1);
   			 me.toggleTab(me.tabMenus[index-1]);
   			 	
   		},
   		toggleTab:function(tabMenu){
   			 this.curr_tabMenu=tabMenu;
   		},
   		leftArrow:function(){
   		
   		},
   		rightArrow:function(){
   		},
   		emptyTabMenus:function(){
   			this.tabMenus=[];
   			this.redirectToIndex();
   		},
   		redirectToIndex:function(){
   			this.curr_tabMenu={};
   		}
   	}
   });
   
   function isInArray(arr,con){
   	if(arr && con){
   		for(var i = 0; i < arr.length; i++){
	       	 if(con === arr[i]){
	            return true;
	    	    }
	   	}
	  }
	  	return false;
   }
</script>
</body>