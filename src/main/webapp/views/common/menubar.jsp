<%@page import="com.kh.market.model.vo.Item"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	//로그인 정보 추출하기 
	Member loginUser = (Member)session.getAttribute("loginUser");
	//로그인이 되어있지 않다면 loginUser라는 key값으로 데이터를 조회할 수 없으니 
	//null이 반환된다. 로그인이 되었다면 해당 로그인 정보가 반환된다. 
	//알림 메세지 추출하기 
	String alertMsg = (String)session.getAttribute("alertMsg");
	
	//contextPath 변수처리해서 사용하기 
	String contextPath = request.getContextPath();
	
	//카트 번호 생신용
	@SuppressWarnings("unchecked")
	ArrayList<Item> list= (ArrayList<Item>)session.getAttribute("cartlist");
	
	
%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<style>
.header {
	position: relative;
	width: 80%;
	margin: auto;
	z-index: 20;
	background-color: #ffffff;
}

.header-inner {
	position: relative;
	max-width: 1300px;
	min-height: 130px;
	margin: 0 auto;
}

.util {
	position: absolute;
	top: 0px;
	right: 5%;
}

.util>ul {
	float: right;
	list-style: none;
	overflow: hidden;
}

.util>ul>li {
	display: inline;
	font-size: 13px;
}

.util a {
	text-decoration: none;
	color: gray;
}

.logo {
	display: block;
	position: absolute;
	top: 20px;
	left: 0;
	right: 0;
	margin: auto;
}

.logo img {
	width: 300px;
	float: left;
}

.logo a {
	width: 300px;
	float: left;
}

.nav-area {
	margin: auto;
	width: 1300PX;
	background-color: #d60000;
	position: relative;
	z-index: 5;
}

.menu {
	display: flex;
	height: 50px;
	margin: auto;
	width: 1100px;
}

.menu div {
	width: 100%;
	margin: auto
}

.menu a {
	text-decoration: none;
	color: white;
	font-size: 20px;
	font-weight: bold;
	display: block;
	width: 100%;
	height: 100%;
	line-height: 50px;
}

.menu a:hover {
	background-color: rgb(0, 0, 0);
    cursor: pointer;
	transition: background-color 1s;
	text-decoration: none;
	color: white;
}

.board ul {
	margin: auto;
}

#navi>li>ul {
	list-style-type: none;
	background-color: rgb(0, 0, 0);
	padding: 0px;
	display: none;
    
}

#navi>li>a:hover+ul {
	/*안보이는 ul 보여주기*/
	display: block;
    
}

#navi>li>ul:hover {
	display: block;
	background-color: gray;
    
}

.board-category a {
	font-size: 15px
    
}

.outer {
	background-color: #26272b;
	color: white;
	width: 100%;
	margin: auto;
	margin-top: 50px;
}

.image2 {
	width: 40px;
	heigth: 40px;
}

.util-icons li {
	margin: 4px;
}



#pic{
    
    height : 400px;
    
}


#cartCount {
 position : absolute;
 text-align: center;
 background: #FF542A;
        font-size: 12px;
    font-weight: bold;
    border-radius: 50%;
    top:50px;
    right: 85px;
    width: 18px;
    height: 18px;
    color: #fff;
}

</style>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<script>
	
		var msg = "<%= alertMsg%>"
		
		if(msg!="null"){
			alert(msg);
			<%session.removeAttribute("alertMsg");%>
		}
	</script>

	<div class="header">

		<div class="header-inner">
			<div class="logo">
				<a href="${contextPath }">
				<img src="${contextPath}/resources/logo/temp.png"></a>
			</div>


		</div>
		<div class="util">
		<br><br> 
		<a href="${contextPath}/myPage.me"> <img class="image2"></a>&nbsp;&nbsp;&nbsp; 
		<a href="${contextPath}/views/market/cart.jsp"> 
		<img class="image" width=40 height=40>
		</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<%if(loginUser == null) {%>
			<a href='${contextPath}/views/member/login.jsp'>로그인</a> | 
			<a href="${contextPath}/enrollFormBefore.me">회원가입</a>
			<%}else{ %>
			<a href="logout.me">로그아웃</a>
			<%} %>
			
			</div>
		</div>
	



	<div class="nav-area" align="center">
		<div class="menu">
			<div class="board">
				<ul id="navi">
					<li><a href="">게시판</a>
						<ul class="board-category">
							<li><a
								href="${contextPath}/board.bo?currentPage=1&category=0&sort=latest">자유게시판</a></li>
							<li><a href="${contextPath}/infoboard.bo?currentPage=1&category=0&sort=latest">정보게시판</a></li>
						</ul></li>
				</ul>

			</div>
			<div>
				<a href="${contextPath}/list.ex">운동법</a>
			</div>
			<div>
				<a href="${contextPath }/list.mk?sort=topSal">매장</a>
			</div>
			
	<%if(loginUser==null){ %>
			<div>
				<a href="${contextPath}/views/member/login.jsp" id="fake">개인기록</a>
			</div>
	<script>
	
			document.getElementById('fake').addEventListener('click', function(event) {
    		alert("로그인한 상태에서 사용 가능합니다.");
    		window.location.href = "${contextPath}/views/member/login.jsp";
			});
	</script>
	
			
			<%} else { %>
			<div>
				<a href="Memorials.me">개인기록</a>
			</div>
			<%} %>

		</div>

	</div>

    <script>
    
    const staticImg= "<%=contextPath%>/resources/icons/icons-cart-static.png"
    const gifImg= "<%=contextPath%>/resources/icons/icons-cart.gif" 
    
    const image = document.querySelector('.image');

    image.src = staticImg;

    image.addEventListener("mouseenter", function() {
      image.src = gifImg;
    });
    image.addEventListener("mouseleave", function() {
      image.src = staticImg;
    });
    
    const staticImg2= "<%=contextPath%>/resources/icons/img.icons-normal.png"
        const gifImg2= "<%=contextPath%>/resources/icons/img.icons-hover.gif" 
        
        const image2 = document.querySelector('.image2');

        image2.src = staticImg2;

        image2.addEventListener("mouseenter", function() {
          image2.src = gifImg2;
        });
        image2.addEventListener("mouseleave", function() {
          image2.src = staticImg2;
        });
    
    </script>


</body>
</html>