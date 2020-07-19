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
                </tr>
                </thead>
                <tbody>
                <c:forEach var='message' items="${list}">
                    <tr>
                        <td class="text-center d-none d-md-table-cell">${message.id}</td>
                        <td class="w-50">${message.title}</td>
                        <td class="text-center d-none d-md-table-cell">
                            <c:choose>
                                <c:when test="${mode == 'send'}">
                                    ${message.recipient_id}
                                </c:when>
                                <c:when test="${mode == 'receive'}">
                                    ${message.sender_id}
                                </c:when>
                            </c:choose>
                        </td>
                        <td class="text-center d-none d-md-table-cell">
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${message.created_at}"/>
                        </td>
                    </tr>
                </c:forEach>
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

<%@include file="../includes/footer.jsp" %>