<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function logout(){
		if(confirm('您确定要退出系统吗?')){
			window.location.href="${pageContext.request.contextPath}/user/logout.do";
		}
	}
</script>
<div class="list-group">
  <a href="#" class="list-group-item active">
    系统菜单
  </a>
  <c:if test="${currentUser.roleName=='管理员' }">
	  <a href="${pageContext.request.contextPath}/car/preSave.do" class="list-group-item">车辆入库</a>
	  <a href="${pageContext.request.contextPath}/user/list.do" class="list-group-item">用户管理</a>
	  <a href="${pageContext.request.contextPath}/carPlace/list.do" class="list-group-item">停车位管理</a>
	  <a href="${pageContext.request.contextPath}/car/list.do" class="list-group-item">停车管理</a>
	  <a href="javascript:logout()" class="list-group-item">安全退出</a>
  </c:if>
  <c:if test="${currentUser.roleName=='操作员' }">
	 <a href="${pageContext.request.contextPath}/car/preSave.do" class="list-group-item">车辆入库</a>
	  <a href="${pageContext.request.contextPath}/carPlace/list.do" class="list-group-item">停车位管理</a>
	  <a href="${pageContext.request.contextPath}/car/list.do" class="list-group-item">停车管理</a>
	  <a href="javascript:logout()" class="list-group-item">安全退出</a>
  </c:if>
  
</div>