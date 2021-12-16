<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>会員登録の完了</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3><c:out value="${username}"/>さんの会員登録が完了しました。</h3>
		<br />
		<a href='index'>商品検索</a>へ<br />
	</body>
</html>