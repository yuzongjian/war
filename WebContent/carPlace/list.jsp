<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function carPlaceDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/carPlace/delete.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo);
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/carPlace/list.do";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/carPlace/list.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="typeName"  value="${carPlace.typeName }" placeholder="请输入要查询的停车区域...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/carPlace/preSave.do'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>序号</th>
	  	<th>区域</th>
	    <th>停车位</th>
	    <th>已停车位</th>
	    <th>空车位</th>
	     <th>状态</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="carPlace" items="${carPlaceList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${carPlace.typeName }</td>
	  		<td>${carPlace.number }</td>
	  		<td>${carPlace.shiyong }</td>
	  		<td>${carPlace.kongwei }</td>
	  		<c:if test="${carPlace.numStatus==1 }">
	  			<td>未满</td>
	  		</c:if>
	  		<c:if test="${carPlace.numStatus==2 }">
	  			<td>已满</td>
	  			</c:if>
	  		<td>
	  			<button type="button" class="btn btn-info btn-xs" onclick="javascript:window.location.href='${pageContext.request.contextPath}/carPlace/preSave.do?id=${carPlace.id }'">修改</button>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="carPlaceDelete(${carPlace.id })">删除</button>
	  		</td>
	  	</tr>
	  </c:forEach>
	</table>
	<nav >
		<ul class="pagination">
			${pageCode }
		</ul>
	</nav>
</div>



