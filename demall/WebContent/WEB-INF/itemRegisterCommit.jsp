<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>アイテム登録の完了</title>
<link rel="stylesheet" href="https://newcss.net/new.min.css">
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>以下の情報で登録しました。</h3>
	<br />
	<table>
		<tr>
			<th>商品名</th>
			<td><c:out value="${items.name}" /></td>
		</tr>
		<tr>
			<th>種類</th>
			<td><c:out value="${categoryName}" /></td>
		</tr>
		<tr>
			<th>製造元</th>
			<td><c:out value="${items.manufacturer}" /></td>
		</tr>
		<tr>
			<th>色</th>
			<td><c:out value="${items.color}" /></td>
		</tr>
		<tr>
			<th>在庫</th>
			<td><c:out value="${items.stock}" /></td>
		</tr>
		<tr>
			<th>価格</th>
			<td><c:out value="${items.price}" /></td>
		</tr>
		<tr>
			<th>レコメンド</th>
			<td><c:out value="${recomend}" /></td>
		</tr>

	</table>
	<a href='index'>商品検索</a>へ
	<br />
</body>
</html>