<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/reply.css">

    <script type="text/javascript" src="/resources/js/user.js"></script>
    <script>
        $(document).ready(function () {
            $('.logout-a').on("click", function (e) {
                e.preventDefault();
                userService.logout(function (result) {
                    alert(result);
                    document.location.href = "/main"
                });
            });
        });
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-dark navbar-dark fixed-top">
    <a class="navbar-brand" href="/">BCSD_PROJECT</a>
    <%--    화면 작아졌을 때 생기는 아이콘--%>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navMenu">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navMenu">
        <ul class="navbar-nav">
            <li class="nav-item  dropdown">
                <a class="nav-link  dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    커뮤니티
                </a>
                <div class="dropdown-menu ">
                    <a href='/board/list?category=자유게시판' class='dropdown-item'>자유게시판</a>
                    <a href='/board/list?category=유머게시판' class='dropdown-item'>유머게시판</a>
                    <a href='/board/list?category=스포츠게시판' class='dropdown-item'>스포츠게시판</a>
                    <a href='/board/list?category=중고거래게시판' class='dropdown-item'>중고거래게시판</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <c:if test="${loginUser.id != null}">
                <li class="nav-item">
                    <p3 class="navbar" style="color:white">[${loginUser.name}(${loginUser.id})님 안녕하세요!]</p3>
                </li>
                <li class="nav-item">
                    <a href="/message/list?mode=receive" class="nav-link">쪽지함[${uncheckMessage}]</a>
                </li>
                <li class="nav-item">
                    <a href="/user/modify" class="nav-link">정보수정</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link logout-a">로그아웃</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a href="/user/login" class="nav-link">로그인</a>
            </li>
            <li class="nav-item">
                <a href="/user/join" class="nav-link">회원가입</a>
            </li>
        </ul>
    </div>
</nav>




