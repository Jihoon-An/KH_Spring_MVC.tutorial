<%--
  Created by IntelliJ IDEA.
  User: abg14
  Date: 2022-11-24
  Time: 오후 4:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Input</title>
</head>
<body>
<form action="/netflix/insert">
    <input id="title" name="title" placeholder="title"/>
    <input id="genre" name="genre" placeholder="genre"/>
    <button>제출</button>
</form>
</body>
</html>
