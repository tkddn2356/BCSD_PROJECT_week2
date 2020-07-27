<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>

<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <c:choose>
                        <c:when test="${mode == 'send'}">
                            <h4 class="card-title">송신 쪽지함 > 내용보기</h4>
                        </c:when>
                        <c:when test="${mode == 'receive'}">
                            <h4 class="card-title">수신 쪽지함 > 내용보기</h4>
                        </c:when>
                    </c:choose>
                    <div class="message-info">
                        <div class="form-group">
                            <label>작성자</label>
                            <input type="text" name="send_id" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>수신 아이디</label>
                            <input type="text" name ="recipient_id" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>작성날짜</label>
                            <input type="text" name="created_at" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea name="content" class="form-control" rows="10"
                                      style="resize:none" readonly="readonly"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <a href="javascript:history.back();"
                                   class="btn btn-primary">뒤로가기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-3"></div>
</div>
<script type="text/javascript" src="/resources/js/message.js"></script>
<script>
    $(document).ready(function () {
        console.log("Ready");
        var id = '${id}';
        var message_info = $('.message-info');
        var mode = '${mode}';
        showMessage();
        function showMessage() {
            messageService.read(id, function (message) {
                message_info.find("input[name='send_id']").val(message.sender_id);
                message_info.find("input[name='recipient_id']").val(message.recipient_id);
                message_info.find("input[name='created_at']").val(messageService.displayTime(message.created_at));
                message_info.find("input[name='title']").val(message.title);
                message_info.find("textarea[name='content']").val(message.content);
                if(mode ==='receive' && (message.checked === 'false')){
                    messageService.check(id, function (result) {
                        alert("읽음 체크" + result);
                    });
                }
            });
        }

    });

</script>

<%@include file="../includes/footer.jsp" %>