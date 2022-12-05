<%--
  Created by IntelliJ IDEA.
  User: abg14
  Date: 2022-12-02
  Time: 오전 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>
<html>
<head>
    <title>Title</title>

    <!--jQuery-->
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
    </script>

    <!--bootstrap-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
            crossorigin="anonymous"></script>

</head>
<body>
<form name="memberRegisterFrm" action="<%= ctxPath%>/beginSpring/memberRegister.action" method="POST">

    <input type="text" name="userid" value="" required />

    <input type="password" name="passwd" value="" required />

    <input type="text" name="name" value="" required />

    <input type="email" name="email" value="" required />

    <input type="tel" name="tel" value="" required placeholder="-없이입력하세요"/>

    <input type="submit" value="가입하기" />

    <input type="reset" value="취소하기" />

</form>
</body>
</html>
