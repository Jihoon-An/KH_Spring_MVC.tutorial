<%--
  Created by IntelliJ IDEA.
  User: abg14
  Date: 2022-11-27
  Time: 오후 6:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>tables</title>

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
    <%--  datatables  --%>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/v/dt/jq-3.6.0/dt-1.13.1/b-2.3.3/rg-1.3.0/sp-2.1.0/sl-1.5.0/datatables.min.css"/>

    <script type="text/javascript"
            src="https://cdn.datatables.net/v/dt/jq-3.6.0/dt-1.13.1/b-2.3.3/rg-1.3.0/sp-2.1.0/sl-1.5.0/datatables.min.js"></script>

    <style>
        .title-btn {
            border: none;
            outline: none;
            box-shadow: none;
            cursor: pointer;
            background: none;
        }
        .title-btn:focus,
        .title-btn:active{

        }
    </style>
</head>
<body>
<div class="container">
    <form method="post" action="/">
        <button>main으로</button>
    </form>
    <c:if test="${loginId != null}">
    <form method="post" action="/board/toPosting">
        <button>글 작성하기</button>
    </form>
    </c:if>
    <table id="table_id" class="table table-bordered">
        <thead>
        <tr>
            <th>Seq</th>
            <th>writer</th>
            <th>title</th>
            <th>write date</th>
            <th>view count</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${boardList}">
            <tr>
                <td>${board.freeBoard_seq}</td>
                <td>${board.writer}</td>
                <td>
                    <form method="post" action="/board/toDetail">
                        <input type="hidden" name="seq" value="${board.freeBoard_seq}">
                        <button class="title-btn">${board.title}</button>
                    </form>
                </td>
                <td>${board.write_date}</td>
                <td>${board.view_count}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>

<script>
    var lang_kor = {
        "decimal": "",
        "emptyTable": "데이터가 없습니다.",
        "info": "_START_ - _END_ (총 _TOTAL_ 개의 게시물)",
        "infoEmpty": "0개",
        "infoFiltered": "(전체 _MAX_ 개 중 검색결과)",
        "infoPostFix": "",
        "thousands": ",",
        "lengthMenu": "_MENU_ 개씩 보기",
        "loadingRecords": "로딩중...",
        "processing": "처리중...",
        "search": "검색 : ",
        "zeroRecords": "검색된 데이터가 없습니다.",
        "paginate": {
            "first": "첫 페이지",
            "last": "마지막 페이지",
            "next": "다음",
            "previous": "이전"
        },
        "aria": {
            "sortAscending": " :  오름차순 정렬",
            "sortDescending": " :  내림차순 정렬"
        }
    };
    $(document).ready(function () {
        $('#table_id').DataTable({
            language: lang_kor //or lang_eng
        });
    });
</script>
</html>
