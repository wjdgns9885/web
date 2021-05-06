<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 상세보기</h3>
<form action="getArticle" method="post">
  <p><input type="number" name="articleId" placeholder="게시글 번호" required /></p>
    <button type="submit">상세보기</button>
  </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>
