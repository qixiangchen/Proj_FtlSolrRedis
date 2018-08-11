<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/js/base.css">
<script type="text/javascript" src="/js/jquery.min.js"></script>
<title>菜单列表</title>
<script>
	function dosave()
	{
		addfrm.submit();
	}
	function doback()
	{
		window.open('/init','_self');
	}
</script>
</head>
<body>
	<div>
		<form name="addfrm" action="/savemenu" method="post">
			<table>
				<input type="hidden" id="id" name="id" value="${menu.id}"/>
				<tr>
					<td>名称</td>
					<td><input type="text" id="name" name="name" value="${menu.name}" }/></td>
				</tr>
				<tr>
					<td>价格</td>
					<td><input type="text" id="price" name="price" value="${menu.price}" /></td>
				</tr>
				<tr>
					<td>上架时间</td>
					<td><input type="text" id="dt" name="dt" value="${menu.dt}" /></td>
				</tr>
				<tr>
					<td>菜系</td>
					<td>
						<c:forEach items="${typeList}" var="t">
							<input type="checkbox" id="tid" name="tid" value="${t.id}" ${t.checked }>${t.name}
						</c:forEach>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<input type="button" onclick="dosave()" value="保存"/>
					<input type="button" value="返回" onclick="doback()"/>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>