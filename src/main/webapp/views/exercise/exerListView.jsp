<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    
    <!--background, 글꼴은 임시 지정-->
   <title>운동게시판 리스트보기</title>
    <style>
    
        .exercise{
            background:rgba(197, 154, 154, 0.349);
            border: 1px solid black;
            vertical-align: middle;
            margin: auto;
            width:70%;
        }
        .exex{
            width:98%;
            padding: 5px;
            overflow : hidden;
            text-overflow : ellipsis;
        }
        .bodypt{
        	 margin: auto;
        	 align:center;
        }
        .eximg{
        	float:left;
            vertical-align: middle;
            clear:both;
        }
        .text{
            white-space : nowrap;
        }
        .textar{
            width:50%;
            overflow : hidden;
            text-overflow : ellipsis;
        }
        
    </style>
</head>
<body>
    <%@ include file="/views/common/menubar.jsp" %>

    <div class="exex">
		<h2 align="center">운동 게시판</h2>
		<br>
		<div style="text-align : center">
		<c:if test="${empty loginUser eq 'admin'}">
			<button onclick="location.href='${contextPath}/insert.ex'">새로운 글 작성</button><!-- 운영자만 작성 가능하게끔 -->
		</c:if>
		<br>
		
        <!--이미지는 이미 사이트에 등록되어있어야합니다.-->
        <img class="bodypt" src="${contextPath}/resources/body/mybody.jpg" usemap="#image-map">
		<c:forEach var="ec" items="${ec}">
		<map name="image-map">
            <area target="" alt="가슴" title="가슴운동" href="${contextPath}/list.ex?category=1" coords="118,120,138,98,196,98,212,118,199,133,141,135" shape="poly">
            <area alt="어깨" title="어깨운동" href="${contextPath}/list.ex?category=2" coords="105,121,119,92,152,81,118,117" coords="" shape="poly">
            <area alt="어깨" title="어깨운동" href="${contextPath}/list.ex?category=2" coords="188,82,219,94,231,120,217,119" shape="poly">
            <area alt="팔" title="팔운동" href="${contextPath}/list.ex?category=3" coords="102,121,61,190,74,202,125,137,121,122" shape="poly">
            <area alt="팔" title="팔운동" href="${contextPath}/list.ex?category=3" coords="213,123,212,142,258,202,273,198,233,122" shape="poly">
            <area alt="복부" title="복부운동" href="${contextPath}/list.ex?category=4" coords="132,135,202,213" shape="rect">
            <area alt="허벅지" title="허벅지운동" href="${contextPath}/list.ex?category=5" coords="122,232,121,295,156,304,166,252,185,305,211,294,214,233" shape="poly">
            <area alt="종아리" title="종아리운동" href="${contextPath}/list.ex?category=6" coords="122,317,120,391,138,396,156,319,181,319,197,390,215,391,215,319" shape="poly">
            <area alt="등운동" title="등운동" href="${contextPath}/list.ex?category=7" coords="500,190,381,86" shape="rect">
            <area alt="힙운동" title="힙운동" href="${contextPath}/list.ex?category=8" coords="392,203,489,244" shape="rect">
        </map>
         </div>
        </c:forEach>
		<div class="listarea">
			<c:forEach var="e" items="${elist}">
				<div class="exercise" alt="${e.exerTitle}" align="center" onclick="location.href='${contextPath}/detail.ex?eno=${e.exerNo}'">
					<img id="eximg" src="${contextPath}${e.titleImg}" width="200px" height="150px" onerror="this.src='<%=request.getContextPath() %>/resources/noimage/onerror.png'"><!-- 운동사진 -->
					<b>${e.exerTitle}</b><br><br><!-- 운동이름 -->                
					<div class="textar">
                    <span>${e.exerInf}</span><br><br><!-- 운동정보 -->
                    
                    <!--textar은 정보 입력란이고, 특정 글자 이상이 넘어가면 ...으로 출력하게 style 설정해둠 (60%)-->
                    <span class="text" id="text">${e.exerContent}</span><br><!-- 운동내용 -->
                    </div>
                </div>
                <br>
			</c:forEach>
		</div>
      
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>