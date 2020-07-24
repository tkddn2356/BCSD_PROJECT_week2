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
                </tr>
                </thead>
                <tbody class="message_tbody">
<%--                showMessageList()--%>
                </tbody>
            </table>
            <div class="text-right">
                <button data-oper='send' href="#" class="btn btn-primary">수신 쪽지함</button>
                <button data-oper='receive' href="#" class="btn btn-primary">송신 쪽지함</button>
                <button data-oper='main' href="#" class="btn btn-primary">되돌아가기</button>
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
        var id =
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
                    }
                    else if(list[i].checked === 'true'){
                        str += "<td class='text-center d-none d-md-table-cell'>" + "읽음" + "</td>";
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
    });

</script>

<%@include file="../includes/footer.jsp" %>