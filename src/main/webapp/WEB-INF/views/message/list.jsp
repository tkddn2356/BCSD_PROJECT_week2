<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>
<div class="container" style="margin-top:100px">
    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${mode == 'send'}">
                    <h4 class="card-title">송신 쪽지함 > 목록보기</h4>
                </c:when>
                <c:when test="${mode == 'receive'}">
                    <h4 class="card-title">수신 쪽지함 > 목록보기</h4>
                </c:when>
            </c:choose>
            <table class="table table-hover" id='message_list'>
                <thead>
                <tr>
                    <th class="text-center d-none d-md-table-cell">번호</th>
                    <th class="w-50">제목</th>
                    <th class="text-center d-none d-md-table-cell">
                        <c:choose>
                            <c:when test="${mode == 'send'}">
                                수신자
                            </c:when>
                            <c:when test="${mode == 'receive'}">
                                송신자
                            </c:when>
                        </c:choose>
                    </th>
                    <th class="text-center d-none d-md-table-cell">등록일</th>
                    <th class="text-center d-none d-md-table-cell">읽음</th>
                    <c:if test="${mode == 'send'}">
                        <th class="text-center d-none d-md-table-cell">삭제</th>
                    </c:if>
                </tr>
                </thead>
                <tbody class="message_tbody">
<%--                showMessageList()--%>
                </tbody>
            </table>
            <div class="text-right">
                <c:choose>
                    <c:when test="${mode == 'send'}">
                        <button data-oper='receive'class="btn btn-primary oper-btn">수신 쪽지함</button>
                    </c:when>
                    <c:when test="${mode == 'receive'}">
                        <button data-oper='send' class="btn btn-primary oper-btn">송신 쪽지함</button>
                    </c:when>
                </c:choose>
                <button data-oper='write' class="btn btn-primary oper-btn">쪽지쓰기</button>
                <button data-oper='main' class="btn btn-primary oper-btn">되돌아가기</button>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/js/message.js"></script>
<script>
    $(document).ready(function () {
        console.log("Ready");
        var mode = '${mode}';
        var user_id = '${loginUser.id}';
        var message_tbody = $('.message_tbody');
        showMessageList();
        function showMessageList() {
            messageService.getList(mode, user_id, function (list) {
                console.log("mode = " + mode);
                var str = "";
                for (var i = 0, len = list.length; i < len; i++) {
                    str += "<tr>";
                    str += "<td class='text-center d-none d-md-table-cell'>" + list[i].id + "</td>";
                    str += "<td class='w-50 message-title'><a href='"+ list[i].id +"'>" + list[i].title + "</a></td>";
                    if(mode === 'send'){
                        str += "<td class='text-center d-none d-md-table-cell'>" + list[i].recipient_id + "</td>";
                    }
                    else if(mode === 'receive'){
                        str += "<td class='text-center d-none d-md-table-cell'>" + list[i].sender_id + "</td>";
                    }
                    str += "<td class='text-center d-none d-md-table-cell'>" + messageService.displayTime(list[i].created_at) + "</td>";
                    if(list[i].checked === 'false'){
                        str += "<td class='text-center d-none d-md-table-cell'>" + "안읽음" + "</td>";
                        str += "<td class='text-center d-none d-md-table-cell'><a class='btn btn-primary btn-sm remove-btn' href='"+ list[i].id +"'>"+
                        "삭제" + "</a></td>";
                    }
                    else if(list[i].checked === 'true'){
                        str += "<td class='text-center d-none d-md-table-cell'>" + "읽음" + "</td>";
                        str += "<td class='text-center d-none d-md-table-cell'>" + " " + "</td>";
                    }
                    str += "</tr>";
                }
                message_tbody.html(str);
            });
        }


        $(document).on("click", ".message-title a", function (e) {
            e.preventDefault();
            document.location.href = "/message/read?mode=" + mode+"&id="+ $(this).attr("href");
        });


        $(document).on("click", ".remove-btn", function (e) {
            e.preventDefault();
            var id = $(this).attr("href");
            console.log(id);
            messageService.remove(id, function (result) {
                alert(result);
                showMessageList();
            });
        });




        $('.oper-btn').on("click", function (e) {
            e.preventDefault();
            var operation = $(this).data("oper");
            console.log(operation);
            if (operation === 'send') {
                document.location.href = "/message/list?mode=send";
            } else if (operation === 'receive') {
                document.location.href = "/message/list?mode=receive";
            } else if (operation === 'main') {
                document.location.href = "/main";
            } else if (operation === 'write') {
                document.location.href = "/message/write";
            }
        });
    });

</script>

<%@include file="../includes/footer.jsp" %>