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
	<form action='itemregistconfirm' method='POST'>
		<table>
			<tr>
				<th><c:if test="${namenull== '1'}">
						<span style="color: red">※商品名を入力してください</span>
						<br>
					</c:if> <c:if test="${name255== '1'}">
						<span style="color: red">※商品名は255文字までです。</span>
						<br>
					</c:if>商品名</th>
				<td><input type='text' name='name' class='text' value="${name}" /></td>
			</tr>
			<tr>
				<th>種類</th>
				<td><select name="category" id="category">
						<option value="1">帽子</option>
						<option value="2">鞄</option>
				</select></td>
			</tr>
			<tr>
				<th><c:if test="${manunull== '1'}">
						<span style="color: red">※製造元を入力してください</span>
						<br>
					</c:if> <c:if test="${manu255== '1'}">
						<span style="color: red">※製造元は255文字までです。</span>
						<br>
					</c:if> 製造元</th>
				<td><input type='text' name='manufacturer'
					value="${manufacture}" /></td>
			</tr>
			<tr>
				<th><c:if test="${colornull== '1'}">
						<span style="color: red">※色を入力してください</span>
						<br>
					</c:if> <c:if test="${color32== '1'}">
						<span style="color: red">※色は32文字までです。</span>
						<br>
					</c:if>色</th>
				<td><input type='text' name='color' class='text'
					value="${color}" /></td>
			</tr>
			<tr>
				<th><c:if test="${stocknull== '1'}">
						<span style="color: red">※在庫を入力してください</span>
						<br></c:if>在庫</th>
				<td><input type='text' name='stock' class='text'
					value="${stock}" /></td>
			</tr>
			<tr>
				<th><c:if test="${pricenull== '1'}">
						<span style="color: red">※価格を入力してください</span>
						<br>
					</c:if> <c:if test="${price32== '1'}">
						<span style="color: red">※価格は32文字までです。</span>
						<br>
					</c:if>価格</th>
				<td><input type='text' name='price' class='text'
					value="${price}" /></td>
			</tr>
			<tr>
				<th>レコメンド</th>
				<td><input type="checkbox" name='recomended' class='text'
					checked="checked" />オススメ商品にする</td>
			</tr>
			<tr>
				<td colspan='2'><input type='submit' value='登録確認' /></td>
			</tr>
		</table>
	</form>
	<a href='index'>商品検索</a>へ
	<a href='itemregistcsv'>CSV商品登録画面</a>へ
	<br />
</body>
</html>