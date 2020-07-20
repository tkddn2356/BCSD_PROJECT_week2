<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-body">
                    <div class="board-info">
                        <h4 class="card-title">${category} > 내용 보기
                        </h4>
                        <div class="form-group">
                            <label>작성자</label>
                            <input type="text" name="writer" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title" class="form-control" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea name="content" id="content "class="form-control" rows="10" style="resize:none"
                                      readonly="readonly"></textarea>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body" style="padding-bottom: 0px;">
                            <form id="replyForm" action="#" method="post">
                                <div class="form-group">
                                    <input type="text" name="writer" class="form-control reply-writer"
                                           placeholder="작성자"/>
                                    <textarea name="content" class="form-control reply-content" rows="4"></textarea>
                                </div>
                                <div class="form-group">
                                    <div class="text-right">
                                        <button class="btn btn-primary reply-registerBtn">등록</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card" style="margin: 10px 0px;">
                        <div class="card-body">
                            <ul class="reply-list">
                                <%--                                showList()--%>
                            </ul>
                            <div class="reply-paging">
                                <%--                              showListPaging()--%>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="text-right">
                            <button data-oper='modify' class="btn btn-primary oper-btn">수정</button>
                            <button data-oper='remove' class="btn btn-primary oper-btn">삭제</button>
                            <button data-oper='list' class="btn btn-primary oper-btn">목록</button>
                        </div>
                    </div>
                    <form id='operForm' action="/board/modify" method="get">
                        <input type='hidden' name='category' value='<c:out value="${category}"/>'>
                        <input type='hidden' name='id' value='<c:out value="${id}"/>'>
                        <input type='hidden' name='pageNum' value='<c:out value="${criteria.pageNum}"/>'>
                        <input type='hidden' name='amount' value='<c:out value="${criteria.amount}"/>'>
                        <input type='hidden' name='keyword' value='<c:out value="${criteria.keyword}"/>'>
                        <input type='hidden' name='type' value='<c:out value="${criteria.type}"/>'>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript" src="/resources/js/board.js"></script>
<script>
    $(document).ready(function () {
        var board_info = $('.board-info');
        var board_id = ${id};
        showBoard();
        function showBoard() {
            boardService.read(board_id, function (board) {
                board_info.find("input[name='writer']").val(board.writer);
                board_info.find("input[name='title']").val(board.title);
                board_info.find("#content").val(board.content);
            });
            showList(1);
        }
        var replyUL = $(".reply-list");
        function showList(page) {
            replyService.getList({board_id, page}, function (list) {
                console.log("list: " + list);
                console.log(list);
                if (list.length == 0 || list == null) {
                    return;
                }
                var str = "";
                for (var i = 0, len = list.length; i < len; i++) {
                    str += "<li class='reply-item' data-id='" + list[i].id + "' id='replyId" + i + "'>";
                    str += "<div class='reply-box'>";
                    str += "<div><span>" + list[i].writer + "</span>" +
                        "<span style='float:right'><a class='reply-modify'data-oper = " + i + " href='#'>" + "수정" + "</a></span>" +
                        "<span style='float:right'>" + "&nbsp" + "</span>" +
                        "<span style='float:right'><a class='reply-delete'data-oper = " + i + " href='#'>" + "삭제" + "</a></span></div>";
                    str += "<div>" + replyService.displayTime(list[i].created_at) + "</div>";
                    str += "<div><p>" + list[i].content + "</p></div>";
                    str += "<div><a class='comment-write' data-oper = " + i + " href='#'>" + "답글쓰기(미구현)" + "</a></div>";
                    str += "</div></li>";
                }
                replyUL.html(str);
                showListPaging();
            });
        }

        var pageNum = 1;
        var replyPagination = $(".reply-paging");

        function showListPaging() {
            replyService.getPage({board_id, pageNum}, function (page) {
                var str = "";
                str += "<div class='d-flex justify-content-center'>";
                str += "<nav aria-label='Page navigation example '>";
                str += "<ul class='pagination'>";
                if (page.prev) {
                    str += "<li class='page-item'><a class='page-link' href='" + (page.startPage - 1) + "'>Previous</a></li>";
                }
                for (var i = page.startPage; i <= page.endPage; i++) {
                    var active = pageNum == i ? "active" : "";
                    str += "<li class='page-item " + active + "'><a class='page-link' href='" + i + "'>" + i + "</a></li>"
                }
                if (page.next) {
                    str += "<li class='page-item'><a class='page-link' href='" + (page.endPage + 1) + "'>Next</a></li>";
                }
                str += "</ul></nav></div>";
                console.log(str);
                replyPagination.html(str);
            });
        }

        replyPagination.on("click", "li a", function (e) {
            e.preventDefault();
            var targetPage = $(this).attr("href");
            pageNum = targetPage;
            console.log(pageNum);
            showList(pageNum);
        });

        var replyForm = $("#replyForm");
        var RegisterBtn = $(".reply-registerBtn");
        RegisterBtn.on("click", function (e) {
            e.preventDefault();
            var reply = {
                content: replyForm.find("textarea[name='content']").val(),
                writer: replyForm.find("input[name='writer']").val(),
                board_id: board_id
            };
            console.log(reply);
            replyService.add(reply, function (result) {
                alert(result);
                replyForm.find("textarea[name='content']").val("");
                replyForm.find("input[name='writer']").val("");
                showList(1);
            });
        });

        $(document).on("click", ".reply-modify", function (e) {
            e.preventDefault();
            var operation = $(this).data("oper");
            console.log(operation);
            var replyId = "replyId" + operation;
            console.log(replyId);
            $('#' + replyId).children('.reply-box').hide();

            var id = $('#' + replyId).data("id");
            console.log(id);
            replyService.get(id, function (reply) {
                var str = "";
                str += "<div class='card-body' style='padding-bottom: 0px;'>";
                str += "<form id='replyModifyForm" + operation + "' action='#' method='post'>";
                str += "<div class='form-group'>";
                str += "<input type='text' name='writer' class='form-control reply-writer' readonly=readonly/>";
                str += "<textarea name='content' class='form-control reply-content' rows='4'></textarea></div>";
                str += "<div class='form-group'><div class='text-right'>";
                str += "<button class='btn btn-primary reply-cancelBtn mr-1'>취소</button>";
                str += "<button class='btn btn-primary reply-modifyBtn'>수정</button>";
                str += "</div></div></form></div>";
                $('#' + replyId).html(str);

                var replyModifyFormId = "replyModifyForm" + operation;
                var replyModifyForm = $('#' + replyModifyFormId);
                replyModifyForm.find("input[name='writer']").val(reply.writer);
                replyModifyForm.find("textarea").val(reply.content);

                console.log(replyModifyForm.find("input[name='writer']").val());
                console.log(replyModifyForm.find("textarea").val());

                $(".reply-cancelBtn").on("click", function (e) {
                    e.preventDefault();
                    showList(pageNum);
                });

                $(".reply-modifyBtn").on("click", function (e) {
                    e.preventDefault();
                    var reply = {
                        content: replyModifyForm.find("textarea").val(),
                        writer: replyModifyForm.find("input[name='writer']").val(),
                        board_id: board_id,
                        id: id
                    };
                    console.log(reply);

                    replyService.update(reply, function (result) {
                        alert(result);
                        showList(pageNum);
                    });
                });

            });
        });

        $(document).on("click", ".reply-delete", function (e) {
            e.preventDefault();
            var operation = $(this).data("oper");
            console.log(operation);
            var replyId = "replyId" + operation;
            console.log(replyId);
            var id = $('#' + replyId).data("id");

            replyService.remove(id, function (result) {
                alert(result);
                showList(pageNum);
            });

        });

        $(document).on("click", ".comment-write", function (e) {
            e.preventDefault();
            $(this).hide();
            var operation = $(this).data("oper");
            console.log(operation);
            var replyId = "replyId" + operation;
            console.log(replyId);

            var str = "";
            str += "<li class='comment-item reply-item'>";
            str += "<div class='card-body' style='padding-bottom: 0px;'>";
            str += "<form id='commentRegisterForm" + operation + "' action='#' method='post'>";
            str += "<div class='form-group'>";
            str += "<input type='text' name='writer' class='form-control comment-writer'/>";
            str += "<textarea name='content' class='form-control comment-content' rows='4'></textarea></div>";
            str += "<div class='form-group'><div class='text-right'>";
            str += "<button class='btn btn-primary comment-cancelBtn mr-1'>취소</button>";
            str += "<button class='btn btn-primary comment-registerBtn'>등록</button>";
            str += "</div></div></form></div>";
            str += "</li>";
            $('#' + replyId).after(str);

            var commentRegisterFormId = "commentRegisterForm" + operation;
            $(".comment-cancelBtn").on("click", function (e) {
                e.preventDefault();
                showList(pageNum);
                // $('#' + commentRegisterFormId).hide();
            });

        });

    });

</script>

<script type="text/javascript">
    $(document).ready(function () {
        console.log("operForm");
        var operForm = $("#operForm");
        $('.oper-btn').on("click", function (e) {
            e.preventDefault();
            var operation = $(this).data("oper");
            console.log(operation);
            if (operation === 'list') {
                operForm.attr("action", "/board/list");
                operForm.find("input[name='id']").remove();
            } else if (operation === 'modify') {
                operForm.attr("action", "/board/modify");
            } else if (operation === 'remove') {
                operForm.attr("action", "/board/remove").attr("method", "post");
            }
            operForm.submit();
        });
    });

</script>


<%@include file="../includes/footer.jsp" %>