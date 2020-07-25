<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp" %>

<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">쪽지보내기</h4>
                    <div id="insertForm">
                        <div class="form-group">
                            <label>작성자</label>
                            <input type="text" name="sender_id" value = "${loginUser.id}" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>수신 아이디</label>
                            <input type="text" name ="recipient_id" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea name="content" id="content" class="form-control" rows="10"
                                      style="resize:none"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <button class="btn btn-primary submit-btn">완료</button>
                                <a href="/message/list?mode=receive"
                                   class="btn btn-primary">되돌아가기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3"></div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/message.js"></script>
<script>
    $(document).ready(function () {
        <%--var mode = '${mode}';--%>
        var insertForm = $("#insertForm");
        <%--var user_id = '${loginUser.id}';--%>
        $('.submit-btn').on("click", function(e){
            var message ={
                sender_id: insertForm.find("input[name='sender_id']").val(),
                recipient_id : insertForm.find("input[name='recipient_id']").val(),
                title: insertForm.find("input[name='title']").val(),
                content: insertForm.find("textarea[name='content']").val()
            }
            messageService.write(message, function(result){
                alert(result);
                document.location.href = "/message/list?mode=send";
            }, function(){
                alert("사용자 id와 일치하지 않습니다.");
            });
        });
    });


</script>
<%@include file="../includes/footer.jsp" %>


