<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>会員ログイン</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>ログインしてください。</h3>
		<br />
		<c:if test="${loginfailed =='1'}"><h4>ID,パスワードが間違っているか登録されていません。</h4></c:if>
		<form action='login' method='POST'>
			<table>
				<tr>
					<th>会員ID</th>
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
			 <input type="checkbox" name='autologin' class='text' />次回から自動ログインする
		</form>
		<a href='userregist'>会員登録</a><br />
	</body>
</html>