<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="../includes/header.jsp" %>
<section>
    <div class="container" style="margin-top:100px">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">회원가입</h4>
                        <form:form id="userForm" action="/user/join" method="post" modelAttribute="joinUser">
                            <input type="hidden" name="user_exist"/>
                            <div class="form-group">
                                <label>아이디</label>
                                <div class="input-group">
                                    <input type="text" name="id" class="form-control"/>
                                    <div class="input-group-append">
                                        <button type="button" class="btn btn-primary" onclick="checkUserId()">중복확인
                                        </button>
                                    </div>
                                </div>
                                <form:errors path="id"/>
                                <div class="id-message">

                                </div>
                            </div>
                            <div class="form-group">
                                <label>비밀번호</label>
                                <input type="password" name="password" class="form-control"/>
                                <form:errors path="password"/>
                                <div class="password-message">

                                </div>
                            </div>
                            <div class="form-group">
                                <label>비밀번호 확인</label>
                                <input type="password" name="password_confirm" class="form-control"/>
                                <form:errors path="password_confirm"/>
                                <div class="password_confirm-message">

                                </div>
                            </div>
                            <div class="form-group">
                                <label>이름</label>
                                <input type="text" name="name" class="form-control"/>
                                <form:errors path="name"/>
                                <div class="name-message">

                                </div>
                            </div>
                            <div class="form-group">
                            <%--<button type="button" class="btn btn-primary float-right" onclick="checkJoin()">회원가입</button>--%>
                                <button class="btn btn-primary float-right">회원가입</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</section>

<script>
    function checkUserId() {
        var userForm = $("#userForm");
        userForm.find("input[name='user_exist']").val('false');
        var id = userForm.find("input[name='id']").val();
        if (id.length == 0) {
            var message = "<p style='color:red;'>아이디를 입력해 주세요.</p>";
            $(".id-message").html(message)
            return;
        }
        $.ajax({
            url: '/user/checkUserId/' + id,
            type: 'get',
            dataType: 'text',
            success: function (result) {
                console.log(result);
                if (result == 'true') {
                    userForm.find("input[name='user_exist']").val('true');
                    var message = "<p>사용할 수 있는 아이디입니다.</p>";
                    $(".id-message").html(message)
                } else {
                    userForm.find("input[name='user_exist']").val('false');
                    var message = "<p>사용할 수 없는 아이디입니다.</p>";
                    $(".id-message").html(message)
                }
            }
        })
    }
</script>
<script>
    // function checkUserId() {
    //     var userForm = $("#userForm");
    //     var id = userForm.find("input[name='id']").val();
    //     if (id.length == 0) {
    //         var message = "<p style='color:red;'>아이디를 입력해 주세요.</p>";
    //         $(".id-message").html(message)
    //         return;
    //     }
    //     $.ajax({
    //         url: '/user/checkUserId/' + id,
    //         type: 'get',
    //         dataType: 'text',
    //         success: function (result) {
    //             if (result == 'true') {
    //                 var message = "<p style='color:blue;'>사용할 수 있는 아이디입니다.</p>";
    //                 $(".id-message").html(message)
    //                 checkId = true;
    //             } else {
    //                 var message = "<p style='color:red;'>사용할 수 없는 아이디입니다.</p>";
    //                 $(".id-message").html(message)
    //                 checkId = false;
    //             }
    //         }
    //     })
    // }
    // function checkJoin() {
    //     var userForm = $("#userForm");
    //     if (checkId == false){
    //         var message = "<p style='color:red;'>중복 확인을 하셔야 합니다.</p>";
    //         $(".id-message").html(message)
    //     }
    //     else if(checkId == true)
    //     {
    //         userForm.submit();
    //     }
    // }

    // function checkJoin() {
    //     var userForm = $("#userForm");
    //     if (checkId == false){
    //         var message = "<p style='color:red;'>중복 확인을 하셔야 합니다.</p>";
    //         $(".id-message").html(message)
    //     }
    //     if(userForm.find("input[name='password']").val()==="")
    //     {
    //         var message = "<p style='color:red;'>비밀번호를 입력해 주세요.</p>";
    //         $(".password-message").html(message)
    //         checkPassword=false;
    //     }
    //     else if(userForm.find("input[name='password']").val())
    //     {
    //         $(".password-message").children().remove();
    //         checkPassword=true;
    //     }
    //     if(userForm.find("input[name='name']").val()==="")
    //     {
    //         var message = "<p style='color:red;'>이름을 입력해 주세요.</p>";
    //         $(".name-message").html(message)
    //         checkName = false;
    //     }
    //     else if(userForm.find("input[name='name']").val())
    //     {
    //         $(".name-message").children().remove();
    //         checkName=true;
    //     }
    //     if(userForm.find("input[name='password']").val()!==userForm.find("input[name='password_confirm']").val()){
    //         var message = "<p style='color:red;'>비밀번호가 일치하지 않습니다.</p>";
    //         $(".password_confirm-message").html(message)
    //         checkPassword_confirm=false;
    //     }
    //     else if(userForm.find("input[name='password']").val()===userForm.find("input[name='password_confirm']").val()){
    //         $(".password_confirm-message").children().remove();
    //         checkPassword_confirm=true;
    //     }
    //     if(checkId && checkPassword && checkPassword_confirm && checkName)
    //     {
    //         userForm.submit();
    //     }
    // }

</script>
<%@include file="../includes/footer.jsp" %>



