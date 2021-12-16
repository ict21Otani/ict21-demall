<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>注文のキャンセル完了</title>
		<link rel="stylesheet" href="https://newcss.net/new.min.css">
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>以下の注文をキャンセルしました。</h3>
		<br />
		<table>
			<c:forEach var="list" items="${list}">
			<tr>
				<th>注文日</th>
				<td><c:out value="${list.purchased_date}" /></td>
			</tr>
			<tr>
				<th>購入商品</th>
				<td>
					<table>
						<tr>
							<th>商品名</th>
							<th>色</th>
							<th>メーカー</th>
							<th>単価</th>
							<th>数量</th>
						</tr>
						<c:forEach var="details" items="${list.purchasesDetailsList}">
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
			</tr>
			<tr>
				<th>配送先</th>
				<td><c:out value="${list.destination}" /></td>
			</tr>
			</c:forEach>
		</table>
		<a href='index'>商品検索</a>へ<br />
	</body>
</html>