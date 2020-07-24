<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../includes/header.jsp" %>
<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">로그인</h4>
                    <%--                    <c:if test="${loginResult=='fail'}">--%>
                    <%--                    <div class="alert alert-danger" role="alert">--%>
                    <%--                       <ul>--%>
                    <%--                           <li class="text-left">로그인 실패! 아이디와 비밀번호를 확인해주세요</li>--%>
                    <%--                       </ul>--%>
                    <%--                    </div>--%>
                    <%--                    </c:if>--%>
                    <div id="loginForm">
                        <div class="form-group">
                            <label>아이디</label>
                            <input type="text" name="id" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>비밀번호</label>
                            <input type="password" name="password" class="form-control"/>
                        </div>
                        <div class="form-group text-right">
                            <label style="float:left">
                                <input type="checkbox" name="remember_me"> 로그인유지
                            </label>
                            <button class="btn btn-primary login-btn">로그인</button>
                            <button class="btn btn-primary join-btn">회원가입</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/user.js"></script>
<script>
    $(document).ready(function () {
        var loginForm = $("#loginForm");
        $(".login-btn").on("click", function (e) {
            e.preventDefault();
            var loginInfo = {
                id: loginForm.find("input[name='id']").val(),
                password: loginForm.find("input[name='password']").val(),
                remember_me: ($('input:checkbox[name="remember_me"]').is(":checked") ==  true)
            }
            userService.login(loginInfo, function (result) {
                alert(result);
                document.location.href = "/main"
            })
        });
        $(".join-btn").on("click", function (e) {
            document.location.href = "/user/join"
        });

    });
</script>
<%@include file="../includes/footer.jsp" %>
