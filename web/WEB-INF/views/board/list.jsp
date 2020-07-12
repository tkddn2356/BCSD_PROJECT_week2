<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp" %>
<div class="container" style="margin-top: 100px">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title"><c:out value="${board_info_name}"/></h4>
            <table class="table table-hover" id='board_list'>
                <thead>
                <tr>
                    <th class="text-center d-none d-md-table-cell">글번호</th>
                    <th class="w-50">제목</th>
                    <th class="text-center d-none d-md-table-cell">작성자</th>
                    <th class="text-center d-none d-md-table-cell">작성날짜</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var='board' items="${list}">
                    <tr>
                        <td class="text-center d-none d-md-table-cell"><c:out value="${board.id}"/></td>
                        <td class="w-50"><a href="${board.id}">
                            <c:out value="${board.title}"/></a></td>
                        <td class="text-center d-none d-md-table-cell"><c:out value="${board.writer}"/></td>
                        <td class="text-center d-none d-md-table-cell"><c:out value="${board.regdate}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="text-right">
                <a href="/board/write?board_info_id=${board_info_id}"
                   class="btn btn-primary">글쓰기</a>
            </div>
            <div class="d-flex justify-content-center">
                <nav aria-label="Page navigation example ">
                    <ul class="pagination">
                        <c:if test="${page.prev}">
                            <li class="page-item"><a class="page-link" href="${page.startPage-1}">Previous</a></li>
                        </c:if>
                        <c:forEach var="num" begin="${page.startPage}" end="${page.endPage}">
                            <li class="page-item ${page.cri.pageNum == num ? "active" :""}"><a class="page-link"
                                                                                               href="${num}">${num}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${page.next}">
                            <li class="page-item"><a class="page-link" href="${page.endPage+1}">Next</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
            <div class="d-flex justify-content-center" style="margin-top: 15px">
                <form class="form-inline" action="/board/list" method="get">
                    <select class="custom-select mr-2"  name="type">
                        <option value="TC" selected>제목 + 내용</option>
                        <option value="T" <c:out value="${page.cri.type eq 'T'?'selected':''}"/>>제목만</option>
                        <option value="W" <c:out value="${page.cri.type eq 'W'?'selected':''}"/>>글작성자</option>
                    </select>
                    <input class="form-control mr-2" type="text" name="keyword" value='<c:out value="${page.cri.keyword}"/>'>
                    <input class="form-control mr-2" type="hidden" name="board_info_id" value='<c:out value="${board_info_id}"/>'>
                    <input class="form-control mr-2" type="hidden" name="pageNum" value='<c:out value="${page.cri.pageNum}"/>'>
                    <input class="form-control mr-2" type="hidden" name="amount" value='<c:out value="${page.cri.amount}"/>'>
                    <button class="btn btn-primary mr-2" type="submit">찾기</button>
                </form>
            </div>
        </div>
    </div>
    <form id='moveForm' action="/board/list" method="get">
        <input type='hidden' name='board_info_id' value='<c:out value="${board_info_id}"/>'>
        <input type='hidden' name='id' value='<c:out value="${board.id}"/>'>
        <input type='hidden' name='pageNum' value='<c:out value="${page.cri.pageNum}"/>'>
        <input type='hidden' name='amount' value='<c:out value="${page.cri.amount}"/>'>
        <input type='hidden' name='keyword' value='<c:out value="${page.cri.keyword}"/>'>
        <input type='hidden' name='type' value='<c:out value="${page.cri.type}"/>'>
    </form>
</div>


<%--<script type="text/javascript">--%>
<%--    $(document).ready(function () {--%>
<%--        var result = '<c:out value="${result}"/>';--%>
<%--        checkResult(result);--%>
<%--        history.replaceState({}, null, null);--%>

<%--        function checkResult(result) {--%>
<%--            if (result === '' || history.state) {--%>
<%--                return;--%>
<%--            }--%>
<%--            else if(result === 'success') {--%>
<%--                alert("처리가 완료되었습니다")--%>
<%--            }--%>
<%--            else {--%>
<%--                alert("게시글 "+result + " 이(가) 등록되었습니다")--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>

<script>
    $(document).ready(function () {
        var moveForm = $("#moveForm");

        $('.page-item a').on("click", function (e) {
            e.preventDefault();
            moveForm.find("input[name='pageNum']").val($(this).attr("href"));
            moveForm.find("input[name='id']").remove();
            moveForm.submit();
        });

        $('.w-50 a').on("click", function (e) {
            e.preventDefault();
            moveForm.attr("action", "/board/read");
            moveForm.find("input[name='id']").val($(this).attr("href"));
            moveForm.submit();
        });
    });

</script>


<%@include file="../includes/footer.jsp" %>
<%--<c:import url="/WEB-INF/views/include/bottom_info.jsp" />--%>






