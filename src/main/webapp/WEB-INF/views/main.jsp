<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="includes/header.jsp" %>


<img src="https://static-sample.tkddn2356.com/hello/frog.png" style="text-align: center"/>

<script>
    var result = '<c:out value="${result}"/>'
    if(result === "로그인 성공"){
        alert(result);
    }
    if(result === "로그아웃 성공"){
        alert(result);
    }
</script>
<%@include file="includes/footer.jsp" %>