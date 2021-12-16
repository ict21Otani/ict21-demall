<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>会員情報</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>会員情報</h3>
		<br />
		<form action='updateuserconfirm' method='POST'>
			<table>
				<tr>
					<th>会員ID</th>
					<td><c:out value="${user.userId}" /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' name='password1' class='password' value="${user.password}" /></td>
				</tr>
				<tr>
					<th>パスワード(確認)</th>
					<td><input type='password' name='password2' class='password' value="${user.password}"/></td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type='text' name='name' class='text' value="${user.name}"/></td>
				</tr>
				<tr>
					<th>ご住所</th>
					<td><input type='text' name='address' class='text' value="${user.address}"/></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='変更' /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${user.userId}">
		</form>
		<a href='withdrawconfirm'>退会する</a><br /><br />
		<a href='purchasehistory'>購入履歴</a>へ<br />
		<a href='index'>商品検索</a>へ<br />
	</body>
</html>