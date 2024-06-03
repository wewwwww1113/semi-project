<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

<%
	String userId = (String)request.getParameter("userId");
	String userName = (String)request.getParameter("userName");
	String nickName = (String)request.getParameter("nickName");
	String email = (String)request.getParameter("email");
	String phone = (String)request.getParameter("phone");
%>

<%@ include file="/views/common/menubar.jsp"%>

</head>

    <style>
        .div1{
            background-color: rgb(255, 34, 34);
            color : white;
            width: 500px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            border-radius: 10px;
            border: 2px solid rgb(255, 255, 255);
            cursor: pointer;
            
        }

        .p1{
            text-align: center;
            border: 1px solid rgb(255, 255, 255);
            background-color: rgba(252, 0, 0, 0.033);
            line-height: 30px;
            width: 500px;
            height: 500px;
            margin-top: 10px;
            border-radius: 10px;
            box-sizing: border-box;
            display: none; 
        }
        
        .sp1{
            color : red;
        }

        .button {
			padding: 10px 20px;
			background-color: #333; /* 짙은 회색 버튼 배경 */
			color: #fff;
			border: none;
			border-radius: 5px;
			cursor: pointer;
			transition: background-color 0.3s; /* hover 효과 */
            
		}
		
	.button:hover {
			background-color: #ff0000; /* hover 시 밝은 회색으로 변화 */
		}
    
    .name {
        color : red;
    }

    .progress-bar {
	    height: 5px;
	    background-color: #e5e5e5;
	    margin-bottom: 30px;
	}
	
	.progress {
	    height: 100%;
	    width: 100%;
	    background-color: #ff0000;
	}

    .container {
	    max-width: 600px;
	    margin: 0 auto;
	    padding: 20px;
	    background-color: #fff;
	    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	}

    header {
	    text-align: center;
	    margin-bottom: 30px;
	}

    body {
    font-family: 'Malgun Gothic', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f5f6f7;
	}

    #id{
        
    }

    </style>





</head>
<body>
    

    <div class="container" align="center">
    	<br><br>
        
        <header>
        
            <img id='fade'src="https://i.pinimg.com/originals/2b/e6/da/2be6dae48ff2cd934903267c8a163355.png" alt="" width="150" height="150">
        
        </header>

        <div class="progress-bar">
            <div class="progress"></div>
        </div>

    	<h1>  <span class="name">헬스라이프</span> 가입 완료<br> </h1> 
        <h6>
           <span class="name"><%=userName %></span>님, 가입을 축하합니다.<br>
            헬스라이프의 새로운 아이디는 <span id="id"><%=userId%></span>입니다. 
            <br>
        
        </h6>

                
        
        
        <div class="div1">내정보 상세보기 </div>
        <p class="p1">
                <br>
                <span class="sp1"><i class='bx bxs-user'></i></span> <br> <%=userId %>
                <br><br>
                <span class="sp1"><i class='bx bxs-rename'></i> <br></span> <%=userName %>
                <br><br>
                <span class="sp1"><i class='bx bx-wink-smile'></i> <br> </span> <%=nickName %> 
                <br><br>
                <span class="sp1"><i class='bx bxs-envelope'></i> <br>  </span><%=email %>
                <br><br>
                <span class="sp1"><i class='bx bxs-phone'></i> <br> </span> <%=phone %>
            
        </p>
    </div>



    <script>
        $(function(){
            $(".div1").click(function(){
                //$(this) : 현재 클릭이벤트가 발생한 div요소
                //우리가 원하는 슬라이드 대상 : p태그 
                //p태그의 위치는 div 앞쪽 (동위) next() 를 이용해서 접근한다.
                console.log($(this).next().css("display"));

                //현재 클릭한 div뒤에있는 p태그의 display 속성값이 none 이라면
                if($(this).next().css("display") == "none"){
                    //안보이는상태에서 슬라이드 내려주기 
                    $(this).next().slideDown();//시간부여가능
                    //지금 보여지는 p태그를 제외한 나머지 p태그는 다시 올려주기
                    $(this).next().siblings(".p").slideUp();

                }else{//none 아닌경우
                    $(this).next().slideUp();//올려주기
                }
                


            });
        });

    </script>
    
    <br><br>

    <div align="center">
    <button class="button" id="nextButton" 
        onclick="location.href='${contextPath }/views/member/login.jsp'">로그인 화면으로</button>
    <button class="button" id="nextButton" 
        onclick="location.href='${contextPath }/'">홈으로 돌아가기</button>
    </div>

</body>
</html>