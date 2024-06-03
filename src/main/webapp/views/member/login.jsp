<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% //로그인 정보 추출하기 
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	String alertMsg = (String)session.getAttribute("alertMsg");

	Cookie[] cookies = request.getCookies();
	
	String saveId = "";
	
	if(cookies !=null){
		for(Cookie c : cookies){
			if(c.getName().equals("userId")){
				saveId = c.getValue();
				break;
			}
		}
	}
	
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Views</title>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	
	<!-- Popper JS -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.header{
            position: relative;
            width: 80%;
            margin : auto;
            z-index: 20;
            background-color: black;
             text-align: center;
        }
        .header-inner{
            
        position: relative;
        max-width: 1300px;
        min-height: 130px;
        margin: 0 auto;

        }
            .logo{
            display: block;
            position: absolute;
            top: 20px;
            left: 0;
            right: 0;
            margin: auto;
        }
        .logo img{
            width: 60%;
             max-width: 80%;
             height: 60%;
           /* float: right; */
          
        }
        .logo a{
            width: 60%;
             max-width: 80%;
             height: 60%;
          
        }
    
    body {
        margin: 0;
        padding: 0;
        background-color: black;
    }

    
    form {
        max-width: 500px;
        margin: auto; 
        background-color: red; 
        border-radius: 8px; 
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
        padding: 40px; 
    }

    h2 {
        text-align: center;
        margin-bottom: 30px; 
    }

    input[type="text"],
    input[type="password"] {
        border: none; 
        border-bottom: 1px solid #ced4da; 
        padding: 10px; 
        width: 100%; 
        margin-bottom: 20px; 
    }

    button {
        background-color: #007bff;
        border: none; 
        border-radius: 20px; 
        padding: 15px; 
        width: 100%; 
        color: white;
        font-weight: bold; 
        margin-bottom: 20px; 
    }

    .btn-secondary {
        background-color: #6c757d; 
        border: none; 
        border-radius: 20px; 
        padding: 15px; 
        width: 100%; 
        color: white; 
        font-weight: bold; 
    }

</style>
</head>
<body>
	<script>
		var msg = "<%=alertMsg%>";
		
		if(msg!="null"){
			alert(msg); 
			<%session.removeAttribute("alertMsg");%>
		}
	</script>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<div class="header">
        <div class="header-inner">
            <div class="logo">
                <a href="${contextPath }"><img src="${contextPath}/resources/logo/white.png"></a>
            </div>
            </div>
        </div><br><br><br><br>
	<div class="login-area">
	
		<form id="login-form" action="<%=contextPath%>/login.me" method="post">
		<img name="login-image" src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20140925_74%2Filomarta_1411621796365wo9SX_JPEG%2Fbodybuilding_motivation-wallpaper-1920x1200.jpg&type=a340" alt="로그인 이미지" style="display: block; margin: 0 auto; width: 100%;">
		<br><br><br>
			<table style="margin : 0 auto;">
				<tr>
					<th>아이디</th>
					<td><input type="text" id="loginId" name="userId" required></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="loginPwd" name="userPwd" required></td>				
				</tr>
				<tr>
					<td colspan="2">
						<label for="saveId">아이디 저장</label><input type="checkbox" id="saveId" name="saveId">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<button type="submit">로그인</button>
						<button type="button" class="btn-secondary" onclick="location.href='${contextPath }/enrollFormBefore.me'">회원가입</button>
						<br><br>
					</th>
				</tr>
				<tr>
					<th colspan="2">
	   					<div style="display: flex; justify-content: space-between;">
	        				<button type="button" onclick="location.href='${contextPath }/views/member/fId.jsp'" name="FId" style="width: 49%; background-color: yellow; color : black;">아이디 찾기</button>
	        				<button type="button" onclick="location.href='${contextPath }/views/member/fPwd.jsp'" name="FPwd" style="width: 49%; background-color: yellow; color : black;">비밀번호 찾기</button>
	    				</div>
					</th>
				</tr>

			
			</table>
		</form>
		
		
		 <script>
        	$(function(){
        		
        		var saveId = "${cookie.userId.value}";
        		console.log(saveId);
				if(saveId!=""){
					$("#saveId").attr("checked",true);
					$("#loginId").val(saveId);
				}        		
        		
        	});
        	
        
        </script>

	</div>
	 
	
</body>
</html>