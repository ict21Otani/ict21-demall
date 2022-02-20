<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jp.java.demall.search.Items"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>商品編集画面</title>
<link rel="stylesheet" href="https://newcss.net/new.min.css">
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>登録する商品についてを入力してください。（全て必須入力です）</h3>
	<br />
	<form action='itemreditconfirm' method='POST'>
		<%
			Items item = (Items) request.getAttribute("items");
		%>
		<table>
			<tr>
				<th>商品名</th>
				<%-- 商品名か商品IDを一覧で選べるようにする。--%>
				<td><input type='text' name='name' class='text'
					value="${items.name}" required /></td>
			</tr>
			<tr>
				<th>種類</th>
				<td><select name="category" id="category" required>

						<c:if test="${items.categoryId== '1'}">

							<option value="1" selected>帽子</option>
							<option value="2">鞄</option>
						</c:if>
						<c:if test="${items.categoryId== '2'}">
							<option value="1">帽子</option>
							<option value="2" selected>鞄</option>
						</c:if>
				</select></td>
			</tr>
			<tr>
				<th>製造元</th>
				<td><input type='text' name='manufacturer'
					value="${items.manufacturer}" required /></td>
			</tr>
			<tr>
				<th>色</th>
				<td><input type='text' name='color' class='text'
					value="${items.color}" required /></td>
			</tr>
			<tr>
				<th>在庫</th>
				<td><input type='number' name='stock' class='text'
					value="${items.stock}" required /></td>
			</tr>
			<tr>
				<th>
					<%--<c:if test="${pricenull== '1'}">
						<span style="color: red">※価格を入力してください</span>
						<br>
					</c:if> <c:if test="${price32== '1'}">
						<span style="color: red">※価格は32文字までです。</span>
						<br>
					</c:if> --%>価格
				</th>
				<td><input type='number' name='price' class='text'
					value="${items.price}" required /></td>
			</tr>
			<tr>
				<th>レコメンド</th>

				<td><c:if test="${items.recommended== 'true'}">
						<input type="checkbox" name='recomended' class='text'
							checked="checked" />オススメ商品にする</td>

				</c:if>
				<c:if test="${items.recommended== 'false'}">

					<input type="checkbox" name='recomended' class='text' />オススメ商品にする
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan='2'><input type='submit' value='変更する' /></td>
			</tr>
		</table>
	</form>
	<a href='index'>商品検索</a>へ
	<a href='itemedit'>商品情報変更</a>へ
	<a href='itemregistcsv'>CSV商品登録画面</a>へ
	<br />
</body>
</html>