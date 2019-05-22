<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
	Long userNo = 0L;
	if(authUser != null){
		userNo = authUser.getNo();
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='count' value='${fn:length(list) }' />
					<c:if test="${fn:length(list) > 0}">
						<c:forEach items='${list }' var='vo' varStatus='status'>
							<tr>
								<td>${count-status.index }</td>
								<!-- 원래는 0대신 vo.depth로 -->
								<td style="text-align:left; padding-left:${20*0}px">
									<a href="${pageContext.servletContext.contextPath }/board/view/${vo.no}">
								 		${vo.title }
								    </a>
								</td>
								<td>${vo.name}</td>
								<td>${vo.hit}</td>
								<td>${vo.regDate }</td>

								<c:set var='userNo' value='<%=userNo %>' />
								<c:if test="${vo.userNo == userNo }">
									<td><a href="" class="del">삭제</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
					<!-- 
               <tr>
                  <td>2</td>
                  <td>
                     <img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" alt="" />
                     <a href="">두 번째 글입니다.</a>
                  </td>
                  <td>안대혁</td>
                  <td>3</td>
                  <td>2015-10-02 12:04:12</td>
                  <td><a href="" class="del">삭제</a></td>
               </tr>
                -->

				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->
			<%
	          if(authUser != null){
	        %>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write"
						id="new-book">글쓰기</a>
				</div>
			<%
	         }
            %>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>