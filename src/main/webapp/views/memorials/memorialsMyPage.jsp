
 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/views/common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.header {
	position: relative;
	width: 80%;
	margin: auto;
	z-index: 20;
	background-color: #fff;
}

.header-inner {
	position: relative;
	max-width: 1300px;
	min-height: 130px;
	margin: 0 auto;
}
	.list-area{
		width: 760px;
		margin : auto;
	}
	.thumbnail{
		border : 1px solid white;
		width : 220px;
		display : inline-block;
		margin : 14px;
	}

	.post-container {
        display: flex;
        margin-bottom: 20px;
    }
    .image-container {
        flex: 0 0 200px;
        margin-right: 20px;
    }
    .image-container img {
        max-width: 100%; 
        height: auto; 
        display: block;
    }
    .details-container {
        flex-grow: 1;
    }
    .details-container h2 {
        margin-top: 0;
    }

</style>


</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="outer">
		<br>

		<div class="list-area">
		
		<c:forEach var="me" items="${list}">
    	<div class="post-container">
        <div class="image-container">
            <c:forEach var="at" items="${atlist}">
                <c:if test="${me.memorialsNo+1 eq at.fileNo}">
                
                    <img src="${contextPath }${at.filePath}${at.changeName}" alt="게시물 이미지">
                </c:if>
            </c:forEach>
        </div>
        <div class="details-container">
            <h2>운동 날짜: ${me.memorialsDate}</h2>
            <p>운동 시간: ${me.memorialsTime}</p>
            운동 부위: <c:forEach var="part" items="${me.memorialsParts}">${part} </c:forEach>
            <p>기타: ${me.memorialsContent}</p>
            <p>나의 점수: ${me.memorialsSelfScore}</p>
            <div class="button-container">
                        <button style="background-color: blue;" onclick="location.href='${contextPath}/MemorialsUpdateController.me?memorialsNo=${me.memorialsNo}'">수정하기</button>
                        <button style="background-color: red;" onclick="deleteMemorial(${me.memorialsNo})">삭제하기</button>
                    </div>
        
	
                </div>
            </div>
            <br><br>
        </c:forEach>
    </div>
</div>
<script>
    function deleteMemorial(memorialsNo) {
        if (confirm('정말로 삭제하시겠습니까?')) {
        	 var mNo = memorialsNo;
             var contextPath = "<%=request.getContextPath()%>"; // JSP에서 컨텍스트 경로 가져오기
             location.href = contextPath + "/memorialsDelete.me?mNo=" + mNo; 
        }
    }
</script>
<%@ include file="/views/common/footer.jsp" %>
</body>
</html>
 
 