<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/header.jsp" %>
<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${category} >게시글 수정</h4>
                    <div id="modifyForm">
                        <div class="form-group">
                            <label>이름</label>
                            <input type="text" name="writer" class="form-control" readonly="readonly">
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea name="content" class="form-control" rows="10" style="resize:none"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <button data-oper='modify' class="btn btn-primary oper-btn">수정</button>
                                <button data-oper='cancel' class="btn btn-primary oper-btn">취소</button>
                            </div>
                        </div>
                    </div>
                    <form id="operForm" action="/board/read" method="get">
                        <input type="hidden" name="id" value="${id}">
                        <input type="hidden" name="category" value="${category}">
                        <input type="hidden" name="pageNum" value="${criteria.pageNum}"/>
                        <input type="hidden" name="amount" value="${criteria.amount}"/>
                        <input type="hidden" name="type" value="${criteria.type}"/>
                        <input type="hidden" name="keyword" value="${criteria.keyword}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/board.js"></script>
<script>
    $(document).ready(function () {
        var modifyForm = $("#modifyForm");
        var id = ${id};
        var user_id = '${loginUser.id}';
        showBoard();

        function showBoard() {
            boardService.read(id, function (board) {
                modifyForm.find("input[name='writer']").val(board.writer);
                modifyForm.find("input[name='title']").val(board.title);
                modifyForm.find("textarea[name='content']").val(board.content);
            });
        }

        console.log("operForm");
        var operForm = $("#operForm");
        $('.oper-btn').on("click", function (e) {
            e.preventDefault();
            var operation = $(this).data("oper");
            console.log(operation);
            if (operation === 'cancel') {
                console.log("cancel 선택됨");
            } else if (operation === 'modify') {
                var board = {
                    title: modifyForm.find("input[name='title']").val(),
                    writer: modifyForm.find("input[name='writer']").val(),
                    content: modifyForm.find("textarea[name='content']").val(),
                    user_id: user_id,
                };
                boardService.modify(id ,board, function (result) {
                    alert(result);
                }, function(){
                    alert("사용자 id와 일치하지 않습니다.");
                });
            }
            operForm.submit();
        });
    });
</script>


<%@include file="../includes/footer.jsp" %>