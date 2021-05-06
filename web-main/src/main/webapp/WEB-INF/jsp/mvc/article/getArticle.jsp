<%@ page import="java.util.List" %>
<%@ page import="kr.mjc.junghoon.web.dao.Article" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 상세보기</h3>
<p><a href="./articleList">게시글 목록</a></p>
<%
  Article article = (Article) request.getAttribute("getArticle");
  %>
<p><%= article %>
</p>
</body>
</html>
