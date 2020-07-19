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
                    <c:if test="${loginResult=='fail'}">
                    <div class="alert alert-danger" role="alert">
                       <ul>
                           <li class="text-left">로그인 실패! 아이디와 비밀번호를 확인해주세요</li>
                       </ul>
                    </div>
                    </c:if>
                    <form:form id="loginForm" action="/user/login_pro" method="post" modelAttribute="loginUser">
                        <div class="form-group">
                            <label>아이디</label>
                            <input type="text" name="id" class="form-control"/>
                            <form:errors path="id"/>
                        </div>
                        <div class="form-group">
                            <label>비밀번호</label>
                            <input type="password" name="password" class="form-control"/>
                            <form:errors path="password"/>
                        </div>
                        <div class="form-group text-right">
                            <button class="btn btn-primary">로그인</button>
                            <button class="btn btn-primary">회원가입</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>
<script>

</script>

<%@include file="../includes/footer.jsp" %>
