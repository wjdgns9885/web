<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html>
<body>
<h3>게시글 삭제</h3>
<form action="deleteArticle" method="post">
  <p><input type="number" name="articleId" placeholder="게시글 번호" required /></p>
  <p><input type="number" name="userId" placeholder="유저 아이디" required/></p>
  <p>
    <button type="submit">삭제</button>
  </p>
</form>
<p style="color:red;"><%= Optional.ofNullable(request.getParameter("msg"))
        .orElse("")%>
</p>
</body>
</html>
