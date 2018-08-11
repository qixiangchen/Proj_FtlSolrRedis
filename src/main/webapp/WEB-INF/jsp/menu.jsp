<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单列表</title>
<link rel="stylesheet" type="text/css" href="/js/base.css">
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>
function doquery()
{
	listfrm.submit();
}
function dodelete()
{
	listfrm.action = '/delete'
	listfrm.submit();
}
function selectall()
{
	$('input[name="id"]').each(function(){
		this.checked = $('#idall').prop("checked");
	})
}
function doadd()
{
	var url = '/add';
	window.open(url,'_self');
}
function doedit(id)
{
	var url = '/modify?id='+id;
	window.open(url,'_self');
}
function doview(id)
{
	var url = '/view?id='+id;
	window.open(url,'_self');
}
function dorefresh()
{
	var url = '/list';
	window.open(url,'_self');
}
function dosingledel(id)
{
	listfrm.action = '/delete?id='+id
	listfrm.submit();
}
</script>
</head>
<body>
<form id="listfrm" action="/list" method="post">
<input type="text" id="name" name="name" value="${name }"/>
<input type="button" onclick="doquery()" value="查询"/>
<input type="button" onclick="dodelete()" value="批量删除"/>
<input type="button" onclick="doadd()" value="添加"/>
<input type="button" onclick="dorefresh()" value="刷新"/>
<table>
	<tr>
		<th><input type="checkbox" id="idall" name="idall" onclick="selectall()">全选</th>
		<th>编号</th>
		<th>名称</th>
		<th>材料</th>
		<th>价格</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${menuList}" var="m">
		<tr>
			<td><input type="checkbox" id="id" name="id" value="${m.id }"></td>
			<td>${m.id }</td>
			<td>${m.name }</td>
			<td>${m.sname }</td>
			<td>${m.price }</td>
			<td>
				<input type="button" value="编辑" onclick="doedit(${m.id })"/>
				<input type="button" value="查看" onclick="doview(${m.id })"/>
				<input type="button" value="删除" onclick="dosingledel(${m.id })"/>
			</td>
		</tr>
		</c:forEach>
</table>
	${pageDiv }
</form>
</body>
</html>