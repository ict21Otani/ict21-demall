<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>購入履歴</title>
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>購入履歴の一覧</h3>
	<br />
	<table>
		<c:forEach var="list" items="${list}">
		<tr>
			<th>注文日</th>
			<th>購入商品</th>
			<th>配送先</th>
			<th></th>
		</tr>
		<tr>
			<td><c:out value="${list.purchased_date}" /></td>
			<td>
				<table>
					<c:forEach var="details" items="${list.purchasesDetailsList}">
					<tr>
						<th>商品名</th>
						<th>色</th>
						<th>メーカー</th>
						<th>単価</th>
						<th>数量</th>
					</tr>
					<tr>
						<td><c:out value="${details.items.name}" /></td>
						<td><c:out value="${details.items.color}" /></td>
						<td><c:out value="${details.items.manufacturer}" /></td>
						<td><c:out value="${details.items.price}" />円</td>
						<td><c:out value="${details.amount}" />個</td>
					</tr>
					</c:forEach>
				</table>
			</td>
			<td><c:out value="${list.destination}" /></td>
			<%--キャンセル済みはキャンセルボタンを表示しない --%>
				<c:choose>
					<c:when test="${list.cancel== false}">
		    	<td><a href='purchasecancelconfirm?purchaseId=${list.purchase_id}'>キャンセル</a></td>
		  		</c:when>
					<c:otherwise>
						<td>キャンセル済み</td>
					</c:otherwise>
				</c:choose>

		</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<a href='index'>商品検索</a>へ
	<br />
</body>
</html>