<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="/views/common/menubar.jsp"%>
<%
	
	String userId = loginUser.getUserId();
	String userName = loginUser.getUserName();
	
	
	String gender = loginUser.getGender() == null? "" :loginUser.getGender();

	%>    	
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
            text-align: left;
        }
        .btn-container {
            text-align: center;
            margin-top: 20px;
        }
        .btn {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>

    <div class="container">
        <h1>My Page</h1>
        <table>
            <tr>
                <th>항목</th>
                <th>내용</th>
            </tr>
            <tr>
                <td>아이디</td>
                <td><%= loginUser.getUserId() %></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><%= loginUser.getUserName() %></td>
            </tr>
            <tr>
                <td>성별</td>
                <td><%= loginUser.getGender() %></td>
            </tr>
            <tr>
                <td>별명</td>
                <td><%= loginUser.getNickName() %></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><%= loginUser.getEmail() %></td>
            </tr>
            <tr>
                <td>핸드폰 번호</td>
                <td><%= loginUser.getPhone() %></td>
            </tr>
            <tr>
            	<td>가입 날짜</td>
            	<td><%=loginUser.getEnrollDate() %></td>
            </tr>
        </table>
        <div class="btn-container">
            <button class="btn" onclick="location.href='${contextPath}/views/member/updateMyPage.jsp'">프로필 편집</button>
            <button type="button"  class="btn btn-primary" data-toggle="modal" data-target="#updatePwdForm">비밀번호변경</button>
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm" style="background-color : red;">회원탈퇴</button>
        </div>
    </div>
    <!-- 비밀번호 변경 모달영역 -->
	<div class="modal" id="updatePwdForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">비밀번호 변경</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>

	      <div class="modal-body" align="center">
	       		<form action="<%=contextPath%>/updatePwd.me" method="post">

					<input type="hidden" name="userId" value="<%=userId%>">
					
					<table>
						<tr>
							<td>현재 비밀번호</td>
							<td><input type="password" name="userPwd" required> </td>
						</tr>
						<tr>
							<td>변경할 비밀번호</td>
							<td><input type="password" name="updatePwd" required> </td>
						</tr>
						<tr>
							<td>변경할 비밀번호 확인</td>
							<td><input type="password" id="chkPwd" required> </td>
						</tr>
					</table>		
					<br>
					<button type="submit" class="btn btn-secondary" onclick="return checkPwd();">비밀번호 변경</button>
	       		</form>
	      </div>
	      <script>
	      		function checkPwd(){
	      			var updatePwd = $("input[name=updatePwd]").val();
	      			var checkPwd = $("#chkPwd").val();
	      			
	      			if(updatePwd != checkPwd){
	      				alert("변경할 비밀번호와 확인이 일치하지 않습니다.");
	      				return false;
	      			}
	      		}
	      </script>
	      <!--  Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	      </div>
	      </div>
	      </div>
	      </div>
	      
	      <!-- 회원탈퇴 모달용 영역 -->
	<div class="modal" id="deleteForm">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title" align="center">회원 탈퇴</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <div class="modal-body" align="center">
	       		<form action="<%=contextPath%>/delete.me" method="post">
					<!-- 식별자용 아이디 전달하기 -->
					<input type="hidden" name="userId" value="<%=userId%>">
					<table>
						<tr>
							<td>현재 비밀번호 : </td>
							<td><input type="password" id="deletePwd" required> </td>
						</tr>
					</table>		
					<br>
					<button type="submit" class="btn btn-danger" onclick="return deleteMember();">회원탈퇴</button>
	       		</form>
	      </div>
	      <script>
	      		function deleteMember(){
	      			var inputPwd = $("#deletePwd").val();
	      			var userPwd = "<%=loginUser.getUserPwd()%>"; // 안녕하세요
	      			
	      			if(inputPwd==userPwd){
					  
					  return confirm("정말로 탈퇴하실건가요? 탈퇴 후 복구는 불가능합니다.");
	      			}else{
	      				alert("비밀번호가 일치하지 않습니다.");
	      				return false;
	      			}
	      			
	      			
	      		}
	      </script>
		<!--  Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	      </div>
	
	    </div>
	  </div>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>