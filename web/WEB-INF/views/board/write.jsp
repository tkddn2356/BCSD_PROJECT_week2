<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../includes/header.jsp" %>

<div class="container" style="margin-top:100px">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${board_info_name} > 글 쓰기</h4>
                    <form name="insertForm" action="/board/write" method="post">
                        <input type="hidden" name="board_info_id" value="${board_info_id}"/>
                        <div class="form-group">
                            <label>이름</label>
                            <input type="text" name="writer" class="form-control"/>
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
                                <button type="submit" class="btn btn-primary">완료</button>
                                <a href="/board/list?board_info_id=${board_info_id}"
                                   class="btn btn-primary">목록</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<script>--%>
<%--    window.onbeforeunload = function(e) {--%>
<%--        return '작성 중인 글이 있습니다.';--%>
<%--    };--%>

<%--</script>--%>
<%@include file="../includes/footer.jsp" %>


