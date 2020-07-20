<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../includes/header.jsp" %>

<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${category} > 글 쓰기</h4>
                    <div id="insertForm">
                        <div class="form-group">
                            <label>작성자</label>
                            <input type="text" name="writer" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea name="content"  class="form-control" rows="10" style="resize:none"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <button class="btn btn-primary submit-btn">완료</button>
                                <a href="/board/list?category=${category}"
                                   class="btn btn-primary">목록</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/board.js"></script>
<script>
    $(document).ready(function () {
        var category = '${category}';
        var insertForm = $("#insertForm");
        $('.submit-btn').on("click", function(e){
            var board ={
                category: category,
                writer: insertForm.find("input[name='writer']").val(),
                title: insertForm.find("input[name='title']").val(),
                content: insertForm.find("textarea[name='content']").val()
            }
            boardService.write(board, function(result){
                alert(result);
                document.location.href = "/board/list?category=" + category;
            });
        });
    });


</script>
<%@include file="../includes/footer.jsp" %>


