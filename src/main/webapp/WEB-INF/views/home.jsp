<%--
  Created by IntelliJ IDEA.
  User: abg14
  Date: 2022-11-24
  Time: 오후 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<a href="/netflix/toInput">toInput</a><br>
<a href="/netflix/toOutput">toOutput</a><br>
<a href="/toSignUp">signUp</a><br>
<a href="/toLogIn">logIn</a><br>
<a href="/toFile">file</a><br>

<form action="/testFileUpload" method="post" enctype="multipart/form-data">
    <input type="text" name="message">
    <input type="file" name="profileImg">
    <button>업로드</button>
</form>

</body>
</html>