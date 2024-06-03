<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HEALTHLIFE</title>


	<style type="text/css">
	.slide-container{
		width: 3900px;
		hight: 850px;
		transition : transform 1s;
		
	}
	.inner{
		width: 1300px;
		hight: 850px;
		float : left;
	}
	.inner img{
			width: 100%;
			height : 100%;
	}
	.slide{ 
		width: 1300px;
		overflow : hidden
	}
	</style>
</head>

<body>
	<%@ include file="/views/common/menubar.jsp" %>

	 
   
  			  <div class="slide" style="    margin-right: auto;
   						 margin-left: auto;">
				<div class="slide-container" id="imgContainer">
				
					<div class="inner">
						
						<img class="img" src="${contextPath }/resources/uploadFiles/1.jpg">
					</div>
					<div class="inner">
						
						<img class="img" src="${contextPath }/resources/uploadFiles/5.jpg">
					</div>
					<div class="inner">
						
						<img class="img" src="${contextPath }/resources/uploadFiles/3.jpg">
					</div>
				</div>
			</div>
			<div id="pagination" align="center">
		<button type="button" class="버튼1" onclick="movePage(1)" style="width :25px; border-radius:10px ;background-color: gray; color:white;">1</button>
		<button type="button" class="버튼2" onclick="movePage(2)" style="width :25px; border-radius:10px ;background-color: gray; color:white;">2</button>
		<button type="button" class="버튼3" onclick="movePage(3)" style="width :25px; border-radius:10px ;background-color: gray; color:white;">3</button>
            </div>
            
            <script>
            function movePage(num){
    			var pageLocation=1300;
    			var pageNo=num-1;
    			
    			document.querySelector(".버튼"+num).addEventListener('click',function(){
    				document.querySelector('.slide-container').style.transform='translate('+(-pageNo*pageLocation)+'px)';
    			})
    		}
            
            </script>
    





<%@ include file="/views/common/footer.jsp" %>
	
	
	
	
</body>
</html>