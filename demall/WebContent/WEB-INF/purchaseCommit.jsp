<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>商品の購入完了</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>以下の商品の購入を完了しました。</h3>
		<br />
		<table>
			<tr>
				<th>商品名</th>
				<th>商品の色</th>
				<th>メーカー名</th>
				<th>単価</th>
				<th>数量</th>
			</tr>
			<c:forEach var="item" items="${cart}">
				<tr>
					<td><a href='itemdetail?itemId=${item.itemId}'><c:out value="${item.items.name}"/></a></td>
					<td><c:out value="${item.items.color}"/></td>
					<td><c:out value="${item.items.manufacturer}"/></td>
					<td><c:out value="${item.items.price}"/>円</td>
					<td><c:out value="${item.amount}"/>個</td>
				</tr>
			</c:forEach>
		</table>
		合計 <c:out value="${total}"/> 円<br /><br />
		清算方法 代金引換<br /><br />
		配送先  <c:out value="${destination}"/><br /><br />
		<a href='index'>商品検索</a>へ<br />
	</body>
</html>