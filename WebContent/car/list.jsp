<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function carDelete(id){
		if(confirm("确定要删除这条记录吗?")){
			$.post("${pageContext.request.contextPath}/car/delete.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo);
					}else{
						alert("删除成功");
						window.location.href="${pageContext.request.contextPath}/car/list.do";
					}
				}
			);
		}
	}
	function giveFee(id){
		if(confirm("确定要付款吗?")){
			$.post("${pageContext.request.contextPath}/car/giveFee.do",{id:id},
				function(result){
					var result=eval('('+result+')');
					if(result.errorInfo){
						alert(result.errorInfo);
					}else{
						alert("付款成功");
						window.location.href="${pageContext.request.contextPath}/car/list.do";
					}
				}
			);
		}
	}
</script>
<div class="row search" >
  <div class="col-md-6">
	<form action="${pageContext.request.contextPath}/car/list.do" method="post">
	    <div class="input-group" style="width: 300px">
		      <input type="text" class="form-control" name="name"  value="${car.name }" placeholder="请输入要查询的车牌号...">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span>&nbsp;查询</button>
		      </span>
	    </div>
    </form>
  </div>
  <div class="col-md-6" >
  	<button type="button" class="btn btn-primary" style="float: right;" onclick="javascript:window.location.href='${pageContext.request.contextPath}/car/preSave.do'">添加</button>
  </div>
</div>
<div>
	<table class="table table-hover  table-bordered table-striped" style="margin-bottom: 0px;">
	  <tr>
	  	<th>序号</th>
	  	<th>车牌号</th>
	  	<th>停放位置</th>
	  	<th>停放状态</th>
	  	<th>入库时间</th>
	  	<th>出库时间</th>
	  	<th>实时金额</th>
	  	<th>操作</th>
	  </tr>
	  <c:forEach var="car" items="${carList }" varStatus="status">
	  	<tr>
	  		<td>${status.index+1 }</td>
	  		<td>${car.name }</td>
	  		<td>${car.typeName }</td>
	  		<c:if test="${car.state==1 }">
	  			<td>正在停放</td>
	  		</c:if>
	  		<c:if test="${car.state==2 }">
	  			<td>已付款离开</td>
	  		</c:if>
	  		<td>${car.getin_time }</td>
	  		<c:if test="${car.state==2 }">
	  			<td>${car.getout_time }</td>
	  		</c:if>
	  		<c:if test="${car.state==1 }">
	  			<td>停车中</td>
	  		</c:if>
	  		<td>${car.fee }</td>
	  		<td>
	  		<c:if test="${car.state==1 }">
	  			<button type="button" class="btn btn-info btn-xs" onclick="giveFee(${car.id })">付款</button>
	  	</c:if>
	  			<button type="button" class="btn btn-danger btn-xs" onclick="carDelete(${car.id })">删除</button>
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



