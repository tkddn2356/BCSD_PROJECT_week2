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
                    <form id="modifyForm" action="/board/modify" method="post">
                        <input type="hidden" name="category" value="${board.category}"/>
                        <input type="hidden" name="id" value="${board.id}"/>
                        <input type="hidden" name="pageNum" value="${cri.pageNum}"/>
                        <input type="hidden" name="amount" value="${cri.amount}"/>
                        <input type="hidden" name="type" value="${cri.type}"/>
                        <input type="hidden" name="keyword" value="${cri.keyword}"/>
                        <div class="form-group">
                            <label>이름</label>
                            <input type="text" name="writer" value="${board.writer}" class="form-control"
                                   readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label>제목</label>
                            <input type="text" name="title" value="${board.title}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>내용</label>
                            <textarea name="content" class="form-control" rows="10" style="resize:none"><c:out
                                    value="${board.content}"/></textarea>
                        </div>
                        <div class="form-group">
                            <div class="text-right">
                                <button data-oper='modify' type='submit' class="btn btn-primary">수정</button>
                                <button data-oper='list' type='button' onclick="history.back()" class="btn btn-primary">취소</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>




<%@include file="../includes/footer.jsp" %>