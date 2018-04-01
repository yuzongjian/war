<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String error = request.getParameter("error");
if(error!=null){

	 out.print("<script>alert('此车位已停满，请选择其他车位！');</script>");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 function add(){
		if(confirm("确定要入库吗?")){
		
			}			
		}


	function checkForm(){
		var name=$("#name").val();
		var typeId=$("#typeId").val();
		var state=$("#state").val();
		if(name==null || name==""){
			$("#error").html("车牌号码不能为空！");
			return false;
		}
	
		if(typeId==null || typeId==""){
			$("#error").html("请选择车库！");
			return false;
		}
		if(state==null || state==""){
			$("#error").html("请选择停车详情！");
			return false;
		}
		return true;
	}
	
	
	function resetValue(){
		$("#name").val("");
		$("#typeId").val("");
		$("#state").val("");
		
	}
</script>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">${actionName }</h3>
  </div>
  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/car/save.do" onsubmit="return checkForm()">
	  <div id="car_tip">
						<span id="car_err" class="sty_txt2">${error}</span>
					</div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">车牌号码：</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="name" name="name" value="${car.name }" style="width: 300px">
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">停放车库：</label>
	    <div class="col-sm-10">
	    	<select class="form-control" style="width: 300px" id="typeId" name="typeId">
	    		<option value="">请选择...</option>
	    		<c:forEach var="carPlace" items="${carPlaceList }">
	    			<option value="${carPlace.id }" ${carPlace.id==car.typeId?'selected':''}>${carPlace.typeName }</option>
	    		</c:forEach>
	    	</select>
	    </div>
	  </div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">停车详情：</label>
	    <div class="col-sm-10">
	    	<select class="form-control" style="width: 300px" id="state" name="state">
	    		<option value="">请选择...</option>
	    		<option value="1" ${1==car.state?'selected':''}>正常停车</option>	   
	    	</select>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <input type="hidden" id="id" name="id" value="${car.id }"/>
	      <button type="submit" class="btn btn-primary" onclick="add()">保存</button>&nbsp;&nbsp;
	      <button type="button" class="btn btn-primary" onclick="resetValue()">重置</button>&nbsp;&nbsp;
	      <font color="red" id="error"></font>
	    </div>
	  </div>
  </div>
</div>
</body>
</html>