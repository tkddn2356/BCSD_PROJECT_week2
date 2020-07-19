<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../includes/header.jsp" %>
<div class="container" style="margin-top: 100px">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title"><c:out value="${category}"/></h4>
            <table class="table table-hover" id='board_list'>
                <thead>
                <tr>
                    <th class="text-center d-none d-md-table-cell">글번호</th>
                    <th class="w-50">제목</th>
                    <th class="text-center d-none d-md-table-cell">작성자</th>
                    <th class="text-center d-none d-md-table-cell">작성날짜</th>
                    <th class="text-center d-none d-md-table-cell">추천</th>
                    <th class="text-center d-none d-md-table-cell">비추천</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var='board' items="${list}">
                    <tr>
                        <td class="text-center d-none d-md-table-cell"><c:out value="${board.id}"/></td>
                        <td class="w-50 board-title"><a href="${board.id}">
                            <c:out value="${board.title}"/> [<c:out value="${board.reply_count}"/>]</a></td>
                        <td class="text-center d-none d-md-table-cell"><c:out value="${board.writer}"/></td>
                        <td class="text-center d-none d-md-table-cell"><fmt:formatDate pattern="yyyy-MM-dd" value="${board.created_at}"/></td>
                        <td class="text-center d-none d-md-table-cell"><c:out value="${board.hit}"/></td>
                        <td class="text-center d-none d-md-table-cell"><c:out value="${board.hit_not}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="text-right">
                <a href="/board/write?category=${category}"
                   class="btn btn-primary">글쓰기</a>
            </div>
            <div class="d-flex justify-content-center">
                <nav aria-label="Page navigation example ">
                    <ul class="pagination">
                        <c:if test="${boardPage.prev}">
                            <li class="page-item"><a class="page-link" href="${boardPage.startPage-1}">Previous</a></li>
                        </c:if>
                        <c:forEach var="num" begin="${boardPage.startPage}" end="${boardPage.endPage}">
                            <li class="page-item ${boardPage.cri.pageNum == num ? "active" :""}"><a class="page-link"
                                                                                               href="${num}">${num}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${boardPage.next}">
                            <li class="page-item"><a class="page-link" href="${boardPage.endPage+1}">Next</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
            <div class="d-flex justify-content-center" style="margin-top: 15px">
                <form class="form-inline" action="/board/list" method="get">
                    <select class="custom-select mr-2"  name="type">
                        <option value="TC" selected>제목 + 내용</option>
                        <option value="T" <c:out value="${boardPage.cri.type eq 'T'?'selected':''}"/>>제목만</option>
                        <option value="W" <c:out value="${boardPage.cri.type eq 'W'?'selected':''}"/>>글작성자</option>
                    </select>
                    <input class="form-control mr-2" type="hidden" name="category" value='<c:out value="${category}"/>'>
                    <input class="form-control mr-2" type="text" name="keyword" value='<c:out value="${boardPage.cri.keyword}"/>'>
                    <input class="form-control mr-2" type="hidden" name="pageNum" value='<c:out value="${boardPage.cri.pageNum}"/>'>
                    <input class="form-control mr-2" type="hidden" name="amount" value='<c:out value="${boardPage.cri.amount}"/>'>
                    <button class="btn btn-primary mr-2" type="submit">찾기</button>
                </form>
            </div>
        </div>
    </div>
    <form id='moveForm' action="/board/list" method="get">
        <input type='hidden' name='category' value='<c:out value="${category}"/>'>
        <input type='hidden' name='pageNum' value='<c:out value="${boardPage.cri.pageNum}"/>'>
        <input type='hidden' name='amount' value='<c:out value="${boardPage.cri.amount}"/>'>
        <input type='hidden' name='keyword' value='<c:out value="${boardPage.cri.keyword}"/>'>
        <input type='hidden' name='type' value='<c:out value="${boardPage.cri.type}"/>'>
    </form>
</div>

<script>
    $(document).ready(function () {
        var moveForm = $("#moveForm");

        $('.page-item a').on("click", function (e) {
            e.preventDefault();
            moveForm.find("input[name='pageNum']").val($(this).attr("href"));
            moveForm.submit();
        });

        $('.board-title a').on("click", function (e) {
            e.preventDefault();
            moveForm.append("<input type='hidden' name='id' value='" + $(this).attr("href") + "'>");
            moveForm.attr("action", "/board/read");
            moveForm.submit();
        });
    });

</script>


<%@include file="../includes/footer.jsp" %>
<%--<c:import url="/WEB-INF/views/include/bottom_info.jsp" />--%>






