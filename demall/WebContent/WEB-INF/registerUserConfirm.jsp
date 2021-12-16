<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>会員登録の確認</title>
<link rel="stylesheet" href="https://newcss.net/new.min.css">
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>以下の情報で登録しますか？</h3>
	<br />
	<form action='userregistcommit' method='POST'>
		<table>
			<tr>
				<th>会員ID</th>
				<td><c:out value="${user.userId}" /></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><c:out value="${pwdstar}" /></td>
			</tr>
			<tr>
				<th>お名前</th>
				<td><c:out value="${user.name}" /></td>
			</tr>
			<tr>
				<th>ご住所</th>
				<td><c:out value="${user.address}" /></td>
			</tr>
			<tr>
				<td colspan='2'><input type='submit' value='登録' /></td>
			</tr>
		</table>
	</form>
	<a href='index'>商品検索</a>へ
	<br />
</body>
</html>