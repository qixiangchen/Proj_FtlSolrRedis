<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单</title>
<link rel="stylesheet" type="text/css" href="/js/base.css">
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>
function dosave()
{
	addfrm.submit();
}
</script>
</head>
<body>
<form id="addfrm" action="/save" method="post">
<table>
	<input type="hidden" id="id" name="id" value="${menu.id}"/>
	<tr>
		<td>菜单名称</td>
		<td><input type="text" id="name" name="name" value="${menu.name}"/></td>
	</tr>
	<tr>
		<td>价格</td>
		<td><input type="text" id="price" name="price" value="${menu.price}"/></td>
	</tr>
	<tr>
		<td>材料</td>
		<td>
			<c:forEach items="${staffList }" var="s">
				<input type="checkbox" id="sid" name="sid" value="${s.id }" ${s.checked}>${s.name}
			</c:forEach>
		</td>
	</tr>
</table>
<input type="button" onclick="dosave()" value="保存">
<input type="button" onclick="window.open('/list','_self')" value="返回">
</form>
</body>
</html>