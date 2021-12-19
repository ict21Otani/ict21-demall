<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>会員登録</title>
<link rel="stylesheet" href="https://newcss.net/new.min.css">
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>登録する商品についてを入力してください。（全て必須入力です）</h3>
	<br />
	<form action='itemregistcsv' method='POST' enctype="multipart/form-data">
		 <input type="file" name="csv" accept=".csv" required>
  		<button type="submit">登録する</button>

	</form>
	<a href='index'>商品検索</a>へ
	<br />
</body>
</html>