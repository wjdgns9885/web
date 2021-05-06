<%@ page import="java.util.List" %>
<%@ page import="kr.mjc.junghoon.web.dao.Article" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 목록</h3>
<p><a href="./loginForm">로그인</a> <a href="./getForm">상세보기</a></p>
<%
  List<Article> articleList = (List<Article>) request.getAttribute("articleList");
  for (Article article : articleList) {%>
<p><%= article %>
</p>
<%
  }
%>
</body>
</html>
