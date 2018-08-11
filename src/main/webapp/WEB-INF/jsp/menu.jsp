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
	function checkall()
	{
		$('input[name="chkid"]').each(function(){
			if(this.checked)
				this.checked = false;
			else
				this.checked = true;
		});
	}
	function doadd()
	{
		window.open('/addmenu','_self');
	}
	function doedit(id)
	{
		window.open('/addmenu?mid='+id,'_self');
	}
	function dodeletes()
	{
		var ids = '';
		$('input[name="chkid"]:checked').each(function(){
			ids = ids + this.value + ',';
		});
		$.ajax({
			url:'/deletemenu?ids='+ids,
			success:function(data){
				window.open('/init','_self');
			}
		})
	}
	function dodelete(id)
	{
		$.ajax({
			url:'/deletemenu?ids='+id,
			success:function(data){
				window.open('/init','_self');
			}
		})
	}
	function doquery()
	{
		var q = $('#query').val();
		window.open('/init?name='+q,'_self');
	}
	function dohtml(id)
	{
		var url = '/html/'+id+'.html';
		window.open(url,'_blank');
	}
</script>
</head>
<body>
	<div>
		<table>
			<tr>
				<lable>名称查询:</lable>
				<input id="query" name="query" type="text" value="${query}"/>
				<input type="button" onclick="doquery()" value="查询"/>
				<input type="button" onclick="doadd()" value="新增"/>
				<input type="button" onclick="dodeletes()" value="批量删除"/>
			</tr>
		</table>
		<table>
			<tr>
				<th width="50"><input type="checkbox" id="chkall" name="chkall" onclick="checkall()"/>全选</th>
				<th>编号</th>
				<th>名称</th>
				<th>菜系</th>
				<th>价格</th>
				<th>上架时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${menuList}" var="m">
				<tr>
					<td><input type="checkbox" id="chkid" name="chkid" value="${m.id}"/></td>
					<td>${m.id}</td>
					<td>${m.name}</td>
					<td>${m.tname}</td>
					<td>${m.price}</td>
					<td>${m.dt}</td>
					<td>
						<input type="button" value="编辑" onclick="doedit(${m.id})"/>
						<input type="button" value="删除" onclick="dodelete(${m.id})"/>
						<input type="button" value="查看" onclick="dohtml(${m.id})"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		${pagediv}
	</div>
</body>
</html>