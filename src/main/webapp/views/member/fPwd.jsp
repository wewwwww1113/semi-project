<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<%String contextPath = request.getContextPath();
String alertMsg = (String)session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
        background-color: black ; 
    }
 form {
        max-width: 400px;
        margin: auto; 
        background-color: red; 
        border-radius: 8px; 
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
        padding: 40px; 
    }
    input[type="text"]{
        border: none; 
        border-bottom: 1px solid #ced4da; 
        padding: 10px; 
        width: 100%; 
        margin-bottom: 20px; 
        
        h2 {
        text-align: center;
        margin-bottom: 30px; 
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
                <a href="${contextPath }"><img src="${contextPath}/resources/logo/temp.png"></a>
            </div>
            </div>
        </div><br><br><br><br><br><br>
     
<div class="find-area">
	<form id="findpass-form" action="<%=contextPath%>/findPwd.me" method="post">
    <table>
   		<tr>
			<th>아이디</th>
			<td><input type="text" id="ffId" name="ffId" placeholder="아이디 입력하세요" required></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" id="ffEmail" name="ffEmail" placeholder="이메일 입력하세요" required></td>				
		</tr>
		<th colspan="2">
   			<div style="display: flex; justify-content: space-between;">
        	<button type="submit" style="width: 48%; background-color: yellow; color : black;">비밀번호 찾기</button>
        	<button type="button" onclick="location.href='${contextPath }/views/member/fId.jsp'" style="width: 48%; background-color: yellow; color : black;">아이디 찾기로 가기</button>
        	</div>
		</th>
    </table>
</form>
</div>
</body>
</html>