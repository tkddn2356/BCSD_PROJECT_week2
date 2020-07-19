<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                </tr>
                </thead>
                <tbody class="board-tbody">

<%--                showList();--%>

                </tbody>
            </table>
            <div class="text-right">
                <a href="/board/write?category=${category}"
                   class="btn btn-primary">글쓰기</a>
            </div>
            <div class="paging-div">
<%--                showListPaging()--%>
            </div>

            <div class="d-flex justify-content-center" style="margin-top: 15px">
                <form class="form-inline" id="searchForm" action="/board/list" method="get">
                    <select class="custom-select mr-2"  name="type">
                        <option value="TC" selected>제목 + 내용</option>
                        <option value="T" <c:out value="${boardPage.cri.type eq 'T'?'selected':''}"/>>제목만</option>
                        <option value="W" <c:out value="${boardPage.cri.type eq 'W'?'selected':''}"/>>글작성자</option>
                    </select>
                    <input class="form-control mr-2" type="hidden" name="category" value='<c:out value="${category}"/>'>
                    <input class="form-control mr-2" type="text" name="keyword" value='<c:out value="${boardPage.cri.keyword}"/>'>
                    <button class="btn btn-primary mr-2 search-btn" type="submit">찾기</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/js/board.js"></script>
<script>
    $(document).ready(function () {
        console.log("Ready");
        var category = '${category}';
        var pageNum = ${criteria.pageNum};
        var amount = ${criteria.amount};
        var type = '${criteria.type}';
        var keyword = '${criteria.keyword}';

        var board_tbody = $('.board-tbody');
        showList();
        function showList() {
            var criteria = {
                pageNum: pageNum,
                amount: amount,
                type: type,
                keyword: keyword
            };
            boardService.getList(category, criteria, function (list) {
                console.log("category = " + category);
                var str = "";
                for (var i = 0, len = list.length; i < len; i++) {
                    str += "<tr>";
                    str += "<td class='text-center d-none d-md-table-cell'>" + list[i].id + "</td>";
                    str += "<td class='w-50'>" + list[i].title + "</td>";
                    str += "<td class='text-center d-none d-md-table-cell'>" + list[i].writer + "</td>";
                    str += "<td class='text-center d-none d-md-table-cell'>" + boardService.displayTime(list[i].created_at) + "</td>";
                    str += "</tr>";
                }
                board_tbody.html(str);
                showListPaging();
            });
        }

        var paging_div = $('.paging-div');
        function showListPaging() {
            var criteria = {
                pageNum: pageNum,
                amount: amount
            };
            boardService.getPaging(category, criteria, function (paging) {
                var str = "";
                str += "<div class='d-flex justify-content-center'>";
                str += "<nav aria-label='Page navigation example '>";
                str += "<ul class='pagination'>";
                if (paging.prev) {
                    str += "<li class='page-item'><a class='page-link' href='" + (paging.startPage - 1) + "'>Previous</a></li>";
                }
                for (var i = paging.startPage; i <= paging.endPage; i++) {
                    var active = pageNum == i ? "active" : "";
                    str += "<li class='page-item " + active + "'><a class='page-link' href='" + i + "'>" + i + "</a></li>"
                }
                if (paging.next) {
                    str += "<li class='page-item'><a class='page-link' href='" + (paging.endPage + 1) + "'>Next</a></li>";
                }
                str += "</ul></nav></div>";
                console.log(str);
                paging_div.html(str);
            });
        }

        paging_div.on("click", "li a", function (e) {
            e.preventDefault();
            var targetPage = $(this).attr("href");
            pageNum = targetPage;
            console.log(pageNum);
            showList();
        });

        var searchForm = $("#searchForm");
        $('.search-btn').on("click", function (e) {
            e.preventDefault();
            type = searchForm.find("select[name='type']").val();
            keyword = searchForm.find("input[name='keyword']").val();
            console.log("type = " + type);
            console.log("keyword = " + keyword);
            showList();
        });
    });

</script>

<script>

</script>

<script>
    // $(document).ready(function () {
    //     var moveForm = $("#moveForm");
    //
    //     $('.page-item a').on("click", function (e) {
    //         e.preventDefault();
    //         moveForm.find("input[name='pageNum']").val($(this).attr("href"));
    //         moveForm.find("input[name='id']").remove();
    //         moveForm.submit();
    //     });
    //
    //     $('.w-50 a').on("click", function (e) {
    //         e.preventDefault();
    //         moveForm.attr("action", "/board/read");
    //         moveForm.find("input[name='id']").val($(this).attr("href"));
    //         moveForm.submit();
    //     });
    // });

</script>

<%@include file="../includes/footer.jsp" %>
