<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>商品編集一覧画面</title>
<link rel="stylesheet" href="https://newcss.net/new.min.css">
<link rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>編集する商品を選んでください。</h3>
	<br />
	<table>
		<tr>
			<th>商品名</th>
			<th>商品の色</th>
			<th>メーカー名</th>
			<th>価格</th>
		</tr>

		<c:forEach var="item" items="${items}">
			<tr>
				<td><a href='itemedit?itemId=${item.itemId}'>
				 <c:out	value="${item.name}" /></a>
					 <c:if test="${item.recommended == true}">
							<sup>オススメ!</sup>
					</c:if>
				</td>
				<td><c:out value="${item.color}" /></td>
				<td><c:out value="${item.manufacturer}" /></td>
				<td><c:out value="${item.price}" /></td>
			</tr>
		</c:forEach>

	</table>
	<br />

	<!--<c:choose>
		<c:when test="${page == 1}">
    	前へ
  		</c:when>
		<c:otherwise>
		<a href='search?keyword=${keyword}&category=${category}&page=${page-1}'>前へ</a>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="1" end="${pagecount}" step="1">
	<c:choose>
	<%-- 表示ページと違う数字はリンクありにする--%>
		<c:when test="${page != i}">
    		<a href='search?keyword=${keyword}&category=${category}&page=${i}'>
			<c:out	value="${i}" /></a>
  		</c:when>
		<c:otherwise>
			<c:out	value="${i}" />
		</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${page == pagecount}">
    	次へ
  		</c:when>
		<c:otherwise>
		<a href='search?keyword=${keyword}&category=${category}&page=${page+1}'>次へ</a>
		</c:otherwise>
	</c:choose>-->
	<br />
	<br />
	<a href='index'>商品検索</a>へ
	<a href='shoplogin'>商品登録</a>へ
	<br />
</body>
</html>