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
                        <div id="joinForm">
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
                                <div class="id-message">
                                </div>
                            </div>
                            <div class="form-group">
                                <label>비밀번호</label>
                                <input type="password" name="password" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>비밀번호 확인</label>
                                <input type="password" name="password_confirm" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>이름</label>
                                <input type="text" name="name" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <%--<button type="button" class="btn btn-primary float-right" onclick="checkJoin()">회원가입</button>--%>
                                <button class="btn btn-primary float-right join-btn">회원가입</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</section>
<script type="text/javascript" src="/resources/js/user.js"></script>
<script>
    $(document).ready(function () {
        var joinForm = $("#joinForm");
        $(".join-btn").on("click", function (e) {
            e.preventDefault();
            var user ={
                id: joinForm.find("input[name='id']").val(),
                password: joinForm.find("input[name='password']").val(),
                password_confirm: joinForm.find("input[name='password_confirm']").val(),
                name: joinForm.find("input[name='name']").val()
            }
            userService.join(user, function (result) {
                alert(result);
                document.location.href = "/main"
            })
        });
    });
</script>
<script>
    function checkUserId() {
        var joinForm = $("#joinForm");
        joinForm.find("input[name='user_exist']").val('false');
        var id = joinForm.find("input[name='id']").val();
        if (id.length == 0) {
            var message = "<p style='color:red;'>아이디를 입력해 주세요.</p>";
            $(".id-message").html(message)
            return;
        }
        userService.checkUserExist(id, function(result){
            console.log(result);
            if (result == 'true') {
                joinForm.find("input[name='user_exist']").val('true');
                var message = "<p>사용할 수 있는 아이디입니다.</p>";
                $(".id-message").html(message)
            } else {
                joinForm.find("input[name='user_exist']").val('false');
                var message = "<p>사용할 수 없는 아이디입니다.</p>";
                $(".id-message").html(message)
            }
        });
    }
</script>


<script>


</script>
<%@include file="../includes/footer.jsp" %>



