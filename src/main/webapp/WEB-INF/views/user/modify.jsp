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
                    <div id="modifyForm">
                        <div class="form-group">
                            <label>이름</label>
                            <input type="text" name="name" value="${loginUser.name}" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>아이디</label>
                            <input type="text" name="id" value="${loginUser.id}"class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>현재 비밀번호</label>
                            <input type="password" name="password_prev" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>새 비밀번호</label>
                            <input type="password" name="password" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>새 비밀번호 확인</label>
                            <input type="password" name="password_confirm" class="form-control"/>
                        </div>
                        <div class="form-group text-right">
                            <button data-oper='modify' class="btn btn-primary oper-btn">수정완료</button>
                            <button data-oper='back' class="btn btn-primary oper-btn">되돌아가기</button>
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
        $('.oper-btn').on("click", function (e) {
            e.preventDefault();
            var modifyForm =$("#modifyForm");
            var operation = $(this).data("oper");
            console.log(operation);
            if (operation === 'modify') {
                var user = {
                    id: modifyForm.find("input[name='id']").val(),
                    password_prev: modifyForm.find("input[name='password_prev']").val(),
                    password: modifyForm.find("input[name='password']").val(),
                    password_confirm: modifyForm.find("input[name='password_confirm']").val()
                }
                userService.modify(user, function (reuslt) {
                    alert(reuslt);
                    document.location.href = "/main";
                });
            } else if (operation === 'back') {
                history.back();
            }
        });

    });
</script>

<%@include file="../includes/footer.jsp" %>
