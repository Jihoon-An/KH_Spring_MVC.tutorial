<%--
  Created by IntelliJ IDEA.
  User: abg14
  Date: 2022-11-25
  Time: 오후 2:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <link rel="shortcut icon" type="image/x-icon" href="/resource/duck.ico">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous">
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
            crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
    <style>
        * {
            box-sizing: border-box;
        }
        .main{

        }
        .loginBox {
            position: absolute;
            width: 400px;
            height: 300px;
            left: 50%;
            top: 40px;
            transform: translate(-50%);
        }
        td>button {
            width: 100%;
            height: 100%;
        }
        th {
            padding: 10px;
        }
        #main{
            width: 500px;
        }
    </style>
</head>

<body>
<script>
    console.log("${loginId}");
    console.log("${loginId != null}");
</script>
<c:choose>
    <c:when test="${loginId != null}">
        <div class="container">
            <div class="row justify-content-center">
                <table id="main" class="text-center">
                    <tr>
                        <th colspan=4 style="text-align: center;">
                            <span>${loginId}님 안녕하세요.</span>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="/board/toBoard">
                                <button id="free-board" class="btn btn-outline-primary">자유게시판</button>
                            </form>
                        <td>
                            <form method="post" action="/member/toMypage">
                                <button id="my-page" class="btn btn-outline-primary">마이페이지</button>
                            </form>
                        <td>
                            <form method="post" action="/member/logout">
                                <button id="logout" class="btn btn-outline-primary">로그아웃</button>
                            </form>
                        <td>
                            <form id="member-out-Frm" method="post" action="/memberout.member">
                                <button id="memberout" class="btn btn-outline-primary">계정 삭제</button>
                            </form>
                    </tr>
                </table>
            </div>
        </div>
        <script>
            $("#memberoutFrm").on("submit", function () {
                let returnSubmit = false;
                const swalWithBootstrapButtons = Swal.mixin({
                    customClass: {
                        confirmButton: 'btn btn-success',
                        cancelButton: 'btn btn-danger'
                    },
                    buttonsStyling: false
                })
                swalWithBootstrapButtons.fire({
                    title: 'Are you sure?',
                    text: "돌이킬 수 없습니다!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: '지우기',
                    cancelButtonText: '취소',
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'Your account has been deleted.',
                            'success'
                        );
                        returnSubmit = true;
                    } else if (
                        /* Read more about handling dismissals below */
                        result.dismiss === Swal.DismissReason.cancel
                    ) {
                        swalWithBootstrapButtons.fire(
                            'Cancelled',
                            'Your imaginary account is safe :)',
                            'error'
                        )
                    }
                })
                return returnSubmit;
            });
        </script>
    </c:when>

    <c:otherwise>
        <div class="loginBox">
            <div class="container">
                <div class="row"><a href='/freeBoard.board?cpage=1'>
                    <button id="mypage" class="btn btn-outline-primary">자유게시판</button>
                </a></div>
                <div id="loginHeader" class="row text-center">
                    <span class="h1 fw-bold">Login Box</span>
                </div>
                <form method="post" action="/member/logIn" class="justify-content-center">
                    <div id="loginID" class="row input-group m-1 justify-content-center">
                        <span class="input-group-text col-3" id="basic-addon3">ID</span>
                        <input type="text" class="form-control col-9" id="id" name="id"
                               aria-describedby="basic-addon3">
                    </div>
                    <div id="loginPW" class="row input-group m-1 justify-content-center">
                        <span class="input-group-text col-3" id="basic-addon4">Password</span>
                        <input type="password" class="form-control col-9" id="pw" name="pw"
                               aria-describedby="basic-addon4">
                    </div>
                    <div class="text-center mt-3">
                        <input class="form-check-input" type="checkbox" value="" name="checkRemeberId"
                               aria-label="Radio button for following text input">
                        <span>Remember ID</span>
                    </div>
                    <div class="row justify-content-center pt-2">
                        <button id="loginBtn" class="col-3 btn btn-primary mx-2"
                                type="submit">Login</button>
                        <a class="col-3 btn btn-primary mx-2" href="member/signup.jsp" role="button">Sign
                            Up</a>
                    </div>
                </form>
                <script>
                    $("#loginBtn").on("loginSuccess", function () {
                        console.log("login success!");
                        $("#id").val("");
                        $("#pw").val("");
                        Swal.fire('Login Success!!');
                    })
                    $("#loginBtn").on("loginFail", function () {
                        console.log("login fail!");
                        $("#id").val("");
                        $("#pw").val("");
                        Swal.fire('Login Fail!!');
                    })
                </script>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>

</html>