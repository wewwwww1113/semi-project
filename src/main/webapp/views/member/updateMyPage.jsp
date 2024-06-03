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

	%><style>
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
<script>
		
		var msg = "<%=alertMsg%>";
		
		if(msg!="null"){
			alert(msg); 
			<%session.removeAttribute("alertMsg");%>
		}
	</script>
	<form id="updateForm" action="<%=contextPath%>/update.me" method="post">
    <div class="container">
        <h1>프로필 편집</h1>
        <table>
            <tr>
                <th>항목</th>
                <th>현재</th>
                <th>바꿀 내용</th>
            </tr>
            <tr>
                <td>아이디</td>
                <td><%= loginUser.getUserId() %></td>
                <td><input type="text" name="userId" value="<%= loginUser.getUserId() %>" required readonly></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><%= loginUser.getUserName() %></td>
                <td><input type="text" name="userName" value="<%= loginUser.getUserName() %>" required></td>
            </tr>
            <tr>
                <td>성별</td>
                <td><%= loginUser.getGender() %></td>
                <td>
			        <label><input type="radio" name="gender" value="M" required/> 남성</label>
                	<label><input type="radio" name="gender" value="F" required/> 여성</label>
			    </td>
            </tr>
            <tr>
                <td>별명</td>
                <td><%= loginUser.getNickName() %></td>
                <td><input type="text" name="nickName" value="<%= loginUser.getNickName() %>" required></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><%= loginUser.getEmail() %></td>
                <td><input type="text" name="email" value="<%= loginUser.getEmail() %>" required></td>
            </tr>
            <tr>
                <td>핸드폰 번호</td>
                <td><%= loginUser.getPhone() %></td>
                <td><input type="text" name="phone" value="<%= loginUser.getPhone() %>" required></td>
            </tr>
            <tr>
            	<td>가입 날짜</td>
            	<td><%=loginUser.getEnrollDate() %></td>
            	<td><%=loginUser.getEnrollDate() %></td>
            </tr>
            <tr>
            <td>현재 비밀번호 입력</td>
            <td><input type="text" name="test" value="" required></td>
            <td><input type="hidden" name="pass" value="<%=loginUser.getUserPwd() %>"></td>
            </tr>
        </table>
         <div class="btn-container">
        	<button type="submit" class="btn">프로필 저장</button>
        </div>
    </div>
    </form>
    <br><br><br><br><br><br><br><br><br><br><br>
    <%@ include file="/views/common/footer.jsp" %>
</body>
</html>