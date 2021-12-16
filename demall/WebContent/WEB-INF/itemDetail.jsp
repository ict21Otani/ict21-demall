<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>商品詳細</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>商品の詳細表示</h3>
		<br />
		<table>
			<tr>
				<th>商品名</th>
				<td><c:out value="${item.name}"/></td>

			</tr>
			<tr>
				<th>商品の色</th>
					<td><c:out value="${item.color}"/></td>
			</tr>
			<tr>
				<th>メーカー名</th>
					<td><c:out value="${item.manufacturer}"/></td>
			</tr>
			<tr>
				<th>価格</th>
				<td><c:out value="${item.price}"/></td>
			</tr>
			<tr>
				<th>在庫数</th>
					<td><c:out value="${item.stock}"/></td>
			</tr>
		</table>
		<form action='cart' method='POST'>
			数量
			<select name='amount'>
				<option selected value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select><br />
			<input type='hidden' name='itemId' value='${item.itemId}'/>
			<input type='submit' value='ショッピングカートに入れる' /><br />
			※ログインしていない状態では、ボタンのクリック後、<a href='login'>ログイン画面</a>に転送される。<br />
		</form>
		<a href='index'>商品検索</a>へ<br />
	</body>
</html>