<%@page import="java.net.URLEncoder"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<%
	//로그인 정보 추출하기 
	Member loginUser = (Member)session.getAttribute("loginUser");
	//로그인이 되어있지 않다면 loginUser라는 key값으로 데이터를 조회할 수 없으니 
	//null이 반환된다. 로그인이 되었다면 해당 로그인 정보가 반환된다. 
	//알림 메세지 추출하기 
	String alertMsg = (String)session.getAttribute("alertMsg");
	
	//contextPath 변수처리해서 사용하기 
	String contextPath = request.getContextPath();
	
	
	
%>

</head>


    <style>
            
         body {
        font-family: 'Malgun Gothic', sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f5f6f7;
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
        
        .logo img {
            max-width: 200px;
        }
        
        main {
            margin-bottom: 30px;
        }
        
        .progress-bar {
            height: 5px;
            background-color: #e5e5e5;
            margin-bottom: 30px;
        }
        
        .progress {
            height: 100%;
            width: 66.66%;
            background-color: #ff0000;
        }
        

        
        .optional{    color: #888;
        }
	        
        

		.buttons {
			margin-top: 20px;
			text-align: center;
		}

		.button {
			padding: 17px 20px;
			background-color: #000000; /* 짙은 회색 버튼 배경 */
			color: #fff;
			border: none;
			border-radius: 5px;
			cursor: pointer;
			transition: background-color 0.2s; /* hover 효과 */
		}



		.button:hover {
			background-color: #ff0000; /* hover 시 밝은 회색으로 변화 */
		}

		
		input::placeholder {
        color: rgb(204, 191, 191);
        font-style: italic;
        font-size:  13px;
        }

       
        
        
        .input-box input{
            width : 100%;
            height : 100%;
            background-color: transparent;
            border : none;
            outline : none;
            border : 2px solid rgba(0, 0, 0, 0.2);
            border-radius : 40px;
            font-size: 16px;
            color : rgb(0, 0, 0);
            padding : 5px 45px 10px
        }

       

        .checkbox input {
        display: none;
        }

        .checkbox_icon {
        display: inline-block;
        width: 20px;
        height: 20px;
        background-color: transparent;
        border: 2px solid rgb(194, 193, 193);
        position: relative;
        cursor: pointer;
        }

        .checkbox_icon::before, .checkbox_icon::after {
        content: ''; 
        display: inline-block; 
        width: 1px;
        height: 0; 
        background-color: red;
        position: absolute; 
        transform-origin: left top; 
        }

        .checkbox_icon::before {
        top: 9px; 
        left: 2px; 
        transform: rotate(-45deg);
        }

        .checkbox_icon::after {
        top: 16px; 
        left: 9px; 
        transform: rotate(-135deg); 
        }

        .checkbox input:checked + .checkbox_icon {
        border-color: red;
        }

        .checkbox input:checked + .checkbox_icon::before {
        height: 10px; /
        transition: all 0.15s ease;
        }

        .checkbox input:checked + .checkbox_icon::after {
        height: 20px; 
        transition: all 0.15s ease 0.15s; 
        }
            
        .container input:hover{
            box-shadow: 0 0 0 2px red;
        }

        .container input {
            width : 105%;
            height : 50px;
            background : white;
            border : none;
            outline : none;
            border-radius:  1px;
            box-shadow: 0 0 10px rgba(0, 0, 0, .1);
            cursor : pointer;
            font-size : 15px;
            color : #333;
            font-weight: 600;
            transition : .2s ease-in;

        }

    


    </style>



</head>

<body>
	


	<form id="enroll-form" action="<%=contextPath%>/insert.me" method="post">
	
		<div class="container">
            
            <header>
                <h1>
                    <a href="#" class="logo">
                        <img src="https://cdn-icons-png.flaticon.com/512/4205/4205505.png" alt="헬스라이프">
                    </a>
                </h1>


            </header>
           
                <div class="progress-bar">
                    <div class="progress"></div>
                </div>

                <table align="center">

                    <tr class="input-box">
                        <td>아이디</td>
                        <td><input type="text" id="userId" name="userId" required placeholder="6자 이상 20자 이하의 대소문자"></td>
                        <td><button type="button" class="button" id="btn1" onclick="checkId();">중복확인</button></td>
                    </tr>
    
                    <tr class="input-box">
                        <td>비밀번호</td>
                        <td><input type="password" id="userPwd" name="userPwd" required placeholder="8자 이상 20자 이하 영문,숫자"></td>
                        <td></td>
                    </tr>
                    
                    <tr>
                    	<td></td>
                    	<td><span id="output1"></span></td>
                    	<td></td>
                    </tr>
    
    
                    <tr class="input-box">
                        <td>이름</td>
                        <td><input type="text" name="userName" id="userName" required placeholder="2글자 이상 5글자 이하 한글 입력"> </td>
                        
                    </tr>
    				<tr>
    					<td>　</td>
    				</tr>
                    <tr>
                        <td>성별</td>
                        
                        <td>
                            
                            <label class="checkbox" >
                                <input type="checkbox" name="gender" value="M"  onclick="clickCheck(this)" checked>
                                    <span class="checkbox_icon" ></span>
                                    <span class="checkbox_text"></span>남자　　
                            </label>
                                
                            <label class="checkbox">
                                <input type="checkbox" name="gender"  onclick="clickCheck(this)" value="F">
                                    <span class="checkbox_icon"></span>
                                    <span class="checkbox_text"></span>여자
                            </label>
                      
                        </td>
                        
                    </tr>
                    
                    <tr>
    					<td>　</td>
    				</tr>
    
                    <tr class="input-box">
                        <td>닉네임</td>
                        <td><input type="text" name="nickName" id="nickName" required placeholder="2글자 이상 8글자 이하 한글 입력"></td>
                        <td><button type="button" class="button" onclick="checkNickName();">중복확인</button></td>
                    </tr>
    
                    <tr class="input-box">
                        <td>이메일</td>
                        <td><input type="email" name="email" id="email" required placeholder="ex) test1@naver.com"></td>
                    </tr>
    
                    <tr class="input-box">
                        <td>전화번호</td>
                        <td>
                            <input type="text" name="phone" id="phone" required placeholder="(-) 하이픈을 반드시 기입해 주세요.">
                        </td>
                    </tr>
    
                </table>
    
                <br> <br>
    
                <div align="center">
                    <button type="submit" class="button" id="nextButton" onclick="return won1();">회원가입</button>
                    
                    <button type="reset" class="button">초기화</button>
                </div>
                
                



                </div>
                
                
	</form>	


        <script>
        function clickCheck(target) {
        document.querySelectorAll(`input[type=checkbox]`)
            .forEach(el => el.checked = false);
    
        target.checked = true;
        }
        </script>

        
		
		<script>
		
			
			function won1() {
			
			  //비밀번호 (ok)
			  const inputPwd = $("#userPwd").val();
 
			  const pwdRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d~!@#$%^&*()+|=]{8,20}$/;
			  if(!pwdRegex.test(inputPwd)) {
			    alert("비밀번호 오류 : 영문,숫자로 8~20글자 채워주세요.");
			    $("#userPwd").focus();
			    return false; 
			  }
			
			//이름
			  const inputName = $("#userName").val();
 
			  const nameRegex = /^[가-힣]{2,5}$/;
			  if (!nameRegex.test(inputName)) {
			    alert("이름 오류 : 이름은 한글만 2글자 이상 5글자 이하 가능합니다.");
			    $("#userName").focus();
			    return false; 
			  }
			
				  
			
				
				
				
				
				  
				//전화번호
				  const inputPhone = $("#phone").val();
	 
				  const phoneRegex = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
				  if (!phoneRegex.test(inputPhone)) {
				    alert("잘못된 형식의 휴대전화번호입니다. (하이픈(-) 포함 필수!)");
				    $("#phone").focus();
				    return false; 
				  }
				  
				//이메일
				  const inputEmail = $("#email").val();
	 
				  const emailRegex = /^[A-Za-z-0-9\-\.]+@[A-Ja-z-0-9\-\.]+\.[A-Ja-z-0-9]+$/;
				  if (!emailRegex.test(inputEmail)) {
				    alert("올바른 이메일 형식으로 작성해 주세요.");
				    $("#email").focus();
				    return false; 
				  }

				  
				}
		
		</script>
		
		
		
		
		
		
		
		
		<!-- 회원가입 성공시 / 실패시 -->
		<script>
		
			var msg = "<%=alertMsg%>";
			
			
			if(msg!="null"){
				alert(msg);
				<%session.removeAttribute("alertMsg");%>
			}
		
		</script>
		

		<script>
		
			
			function checkId(){
				

					
				var inputId= $("#userId").val();

				// * 아이디 영문 숫자만, 6~20자  
				var idRegex = /^[a-zA-Z][a-zA-Z0-9]{5,19}$/;
	            
				if(document.getElementById("userId").value==""){
					alert("아이디를 입력하세요.");
					return;
				}

	            if (!idRegex.test(inputId)) {
				    alert("아이디 오류 : 6자 이상 20자 이하의 대소문자로 입력해주세요.");
				    $("#userId").val("").focus();
				    return;
				  }
				
	            
				
				$.ajax({
					url : "idCheck.me",
					type : "post",
					data : {
						inputId : $("#userId").val()
					},
					success : function(result){
						//result가 NNNNN 또는 NNNNY로 반환됨 
						
						

						
						if(result=="NNNNN"){ //사용불가
							alert("이미 존재하는 아이디입니다.");
							
						
						}else{ //사용가능
							if(confirm("정말 사용하시겠습니까?")){ //사용
								$("#userId").attr("readonly",true);
								$("#userPwd").focus();
								
							
							}else{ //사용안함 
								//다시 입력유도
								$("#userId").focus();
							}
						}
					},
					error : function(){
						console.log("회원가입 실패");
					}
				});
				
				
			}
			

	
			
			// * 닉네임 중복 체크 
			function checkNickName(){
				
				var inputNickName = $("#nickName").val()
				const nickNameRegex = /^[가-힣]{2,8}$/;
				
				
				if(document.getElementById("nickName").value==""){
					alert("닉네임을 입력하세요.");
					return;
				}
				
				  if (!nickNameRegex.test(inputNickName)) {
				    alert("닉네임 오류 : 한글로만 2글자부터 8글자까지 가능합니다. ");
				    $("#nickName").focus();
				    return false; 
				  }
				
				
				$.ajax({
					url : "nickNameCheck.me",
					type : "post",
					data : {
						inputNickName : $("#nickName").val()
					},
					success : function(result){
						
						
						if(result=="WWWWW"){ //사용불가
							alert("이미 존재하는 닉네임입니다.");
						}else{ //사용가능
							if(confirm("정말 사용하시겠습니까?")){ //사용
								$("#nickName").attr("readonly",true);
								$("#email").focus();
								
							
							}else{ //사용안함 
								//다시 입력유도
								$("#nickName").focus();
							}
						}
					},
					error : function(){
						console.log("회원가입 실패");
					}
				});
				
			}
			
			
			
			
			
		
		</script>


<%@ include file="/views/common/footer.jsp" %>
</body>
</html>