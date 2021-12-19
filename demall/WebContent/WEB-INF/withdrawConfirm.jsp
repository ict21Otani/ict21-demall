<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>退会確認</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3><c:out value="${user.name}" />さん、本当に退会しますか？</h3>
		<form action='withdrawcommit' method='POST'>
			<td colspan="2"><input type='submit' value='退会' /></td>
		</form>
		<a href='index'>商品検索</a>へ<br />
	</body>
</html>