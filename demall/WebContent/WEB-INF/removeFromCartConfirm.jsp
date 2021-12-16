<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>ショッピングカート内の商品を削除</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
		<br />
		<c:out value="${cart.items.name}"/><br />
		<c:out value="${cart.items.manufacturer}"/><br />
		<c:out value="${cart.items.price}"/>円<br />
		数量<c:out value="${cart.amount}"/>個<br /><br />
		<form action='cartremovecommit' method='POST'>
			<input type='hidden' name='itemId' value='${cart.itemId}' />
			<input type='submit' value='削除する' /><br />
		</form>
		<a href='index'>商品検索</a>へ<br />
	</body>
</html>