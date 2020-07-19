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
                    <h4 class="card-title">정보수정</h4>
                    <c:if test="${modifyResult=='fail'}">
                        <div class="alert alert-danger" role="alert">
                            <ul>
                                <li class="text-left">기존 비밀번호가 일치하지 않습니다.</li>
                            </ul>
                        </div>
                    </c:if>
                    <form:form id="modifyForm" action="/user/modify_pro" method="post" modelAttribute="modifyUser">
                        <div class="form-group">
                            <label>이름</label>
                            <input type="text" name="name" value="${loginUser.name}" class="form-control" readonly="readonly"/>
                            <form:errors path="name"/>
                        </div>
                        <div class="form-group">
                            <label>아이디</label>
                            <input type="text" name="id" value="${loginUser.id}"class="form-control" readonly="readonly"/>
                            <form:errors path="id"/>
                        </div>
                        <div class="form-group">
                            <label>현재 비밀번호</label>
                            <input type="password" name="password_prev" class="form-control"/>
                            <form:errors path="password_prev"/>
                        </div>
                        <div class="form-group">
                            <label>새 비밀번호</label>
                            <input type="password" name="password" class="form-control"/>
                            <form:errors path="password"/>
                        </div>
                        <div class="form-group">
                            <label>새 비밀번호 확인</label>
                            <input type="password" name="password_confirm" class="form-control"/>
                            <form:errors path="password_confirm"/>
                        </div>
                        <div class="form-group text-right">
                            <button class="btn btn-primary">수정완료</button>
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
