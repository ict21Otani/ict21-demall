<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>商品検索</title>
<link rel="stylesheet" href="https://newcss.net/new.min.css">
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>商品の検索を行います。</h3>
	<br />
	<form action='search' method='POST'>
		キーワード<br /> <input type='text' name='keyword' /><br /> カテゴリ<br />
		<select name='category'>
			<option selected value='0'>すべて</option>
			<option value='1'>帽子</option>
			<option value='2'>鞄</option>
		</select><br /> <input type='submit' value='検索' /><br />
	</form>
	<a href='cart'>ショッピングカートを見る</a>
	<br />
	<br />
	<c:if test="${logined != '1'}">
			ログインしていない場合<br />
		<a href='login'>ログイン</a>
		<br />
		<br />
	</c:if>
	<c:if test="${logined == '1'}">
		<a href='updateuser'>会員情報の変更</a>
		<br />
	</c:if>
</body>
</html>