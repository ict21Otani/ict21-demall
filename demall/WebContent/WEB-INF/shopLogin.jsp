<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>ショップ管理者ログイン</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h1>ショップ管理者ログイン</h1>
		<h2>ログインしてください。</h2>
		<br />
		<c:if test="${loginfailed =='1'}"><h4>ID,パスワードが間違っているか登録されていません。</h4></c:if>
		<form action='shoplogin' method='POST'>
			<table>
				<tr>
					<th>管理者ID</th>
					<td><input type='text' class='id' name='id' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' class='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>
		</form>
		<%-- <a href='userregist'>会員登録</a><br />--%>
	</body>
</html>