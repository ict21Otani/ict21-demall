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
	<h3>会員情報を入力してください。（全て必須入力です）</h3>
	<br />
	<c:if test="${pwdunmatch eq '1'}">
		<h4 style="color: red">※パスワードとパスワード（確認）が一致しません。</h4>
	</c:if>
	<form action='userregistconfirm' method='POST'>
		<table>
			<tr>
				<th><c:if test="${idnull== '1'}">
						<span style="color: red">※会員IDを入力してください</span>
						<br>
					</c:if> <c:if test="${id255== '1'}">
						<span style="color: red">※会員ID255文字までです。</span>
						<br>
					</c:if>会員ID</th>
				<td><input type='text' name='id' class='id' value="${id}" /></td>
			</tr>
			<tr>
				<th><c:if test="${pwdnull== '1'}">
						<span style="color: red">※パスワードを入力してください</span>
						<br>
					</c:if> <c:if test="${pwd255== '1'}">
						<span style="color: red">※パスワードは255文字までです。</span>
						<br>
					</c:if> パスワード</th>
				<td><input type='password' name='password1' class='password'
					value="${pwd1}" /></td>
			</tr>
			<tr>
				<th>パスワード(確認)</th>
				<td><input type='password' name='password2' class='password'
					value="${pwd2}" /></td>
			</tr>
			<tr>
				<th><c:if test="${namenull== '1'}">
						<span style="color: red">※お名前を入力してください</span>
						<br>
					</c:if> <c:if test="${name32== '1'}">
						<span style="color: red">※お名前は32文字までです。</span>
						<br>
					</c:if> お名前</th>
				<td><input type='text' name='name' class='text' value="${name}" /></td>
			</tr>
			<tr>
				<th><c:if test="${addressnull== '1'}">
						<span style="color: red">※ご住所を入力してください</span>
						<br>
					</c:if> <c:if test="${address255== '1'}">
						<span style="color: red">※ご住所は255文字までです。</span>
						<br>
					</c:if>ご住所</th>
				<td><input type='text' name='address' class='text'
					value="${address}" /></td>
			</tr>
			<tr>
				<td colspan='2'><input type='submit' value='登録確認' /></td>
			</tr>
		</table>
	</form>
	<a href='index'>商品検索</a>へ
	<br />
</body>
</html>