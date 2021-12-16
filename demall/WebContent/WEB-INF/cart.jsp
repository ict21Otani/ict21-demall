<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>ショッピングカート</title>
<link rel="stylesheet" href="https://newcss.net/new.min.css">
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>ショッピングカート内の商品一覧</h3>
	<br />
	<table>
		<tr>
			<th>商品名</th>
			<th>商品の色</th>
			<th>メーカー名</th>
			<th>単価</th>
			<th>数量</th>
			<th></th>
		</tr>
		<c:forEach var="cart" items="${cart}">
			<tr>
				<td><c:out value="${cart.items.name}" /></td>
				<td><c:out value="${cart.items.color}" /></td>
				<td><c:out value="${cart.items.manufacturer}" /></td>
				<td><c:out value="${cart.items.price}" /></td>
				<td><c:out value="${cart.amount}" /></td>
				<td><a href='cartremoveconfirm?itemId=${cart.itemId}'>削除</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${size == 0}"><p style="text-align: center">からっぽ</p></c:if>
	合計<c:out value="${total}" />円
	<br />
	<c:if test="${size != 0}">
	<form action='purchaseconfirm' method='POST'>
		<input type='submit' value='購入する' />
	</form>
	</c:if>
	<br />
	<a href='index'>商品検索</a>へ
	<br />
</body>
</html>