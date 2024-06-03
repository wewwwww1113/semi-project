<%@page import="com.kh.market.model.vo.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>HEALTHLIFE</title>

<style>	
	
	.form-area{
		margin : auto;
		width : 700px;
	}

	.slide-container{
		width: 900px;
		height : 500px;
		transition : transform 0.5s;

	}
	.inner{

		width: 450px;
		height : 480px;
		padding: 0px;
		float : left;
	}
	.inner img{
			width: 100%;
			height : 100%;
	}
	.slide{
		width : 450px;
		height : 480px;
		overflow : hidden;
		padding: 0px;

	}
	.component_title{
		font-weight : lighter;
		font-size : 13px; 
	}
	.component td{
		width : 80px;
		textalign : left;
		padding : 0 0 0 5px;
	}

	.order{
		float : right;
	}
	
	.order button{
		width : 100px;
		height : 45px;
		font-size : 17px;
		font-weight : bold;
		border-radius: : 10px;
		
	}
	.quantity{
		width : 61px;
		height : 41px;
		font-size : 20px;
		text-align: center;
	}
	#quantity {
	font-weight : 500;
	font-size : 20px;
	color : white;
	width : 60px;
	display: inline-block;"
	}
	
	.star-rating {
      display: flex;
    }

    .star {
      appearance: none;
      padding: 1px;
    }

    .star::after {
      content: '☆';
      color: hsl(60, 80%, 45%);
      font-size: 20px;
    }

    .star:hover::after,
    .star:has(~ .star:hover)::after,
    .star:checked::after,
    .star:has(~ .star:checked)::after {
      content: '★';
    }
	
	.star:checked ~ .star::after,
    .star:hover ~ .star::after {
      content: '☆';
    }

		body,ul,li {
	  margin: 0;
	  padding: 0;
	  list-style: none;
		}
		body {
		  position: relative;
		}
		.a {
		  color: white;
		  text-decoration: none;
		}
		.menu-box-1 {
		  position: fixed;
		  width: 15%;
		 
		  top: 30%;
		  left: -10%;
		  background-color: rgb(41 ,41 ,41);
		  transition: left 1s;
		}
		.menu-box-1:hover {
		  left: 0;
		  transition: left 1s;
		}
		.menu-box-1 > ul {
		  position: relative;
		  width: 100%;
		  top: 10%;
		}
		.menu-box-1 > ul > li {
		  padding-left: 10%;
		}
		.menu-box-1 > ul > li:hover {
		  background-color: red;
		  
		}
		.menu-box-1 ul > li > a {
		  display: block;
		  padding: 10px;
		}
		.menu-box-1 ul > li:hover > a {
		  background-color: red;
		 
		  color: white;
		}
			.menu-box-1 ul ul {
		  display: none;
		 background-color: rgb(55,55,55);
		  width: 100%;
		  top: 0%;
		  left: 100%;
		  
		}
		.menu-box-1 ul > li:hover > ul {
		
		  display: block;
		}
	
</style>

<script type="text/javascript">
	function addToCart(){
	if(confirm('상품을 장바구니에 추가하시겠습니까?')){
		document.addForm.submit();
	}else{
		document.addForm.reset();
	}
	}
</script>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="outer">
	
	<nav class="menu-box-1">
  <div class="button">
   <ul>
  <li align="right"><<&nbsp;&nbsp;<li>
  </ul>
  
  <ul>
  	<li><a href="${contextPath }/list.mk?sort=topSal" class="a">전체품목</a>
	<ul>
    		<li><a href="${contextPath }/list.mk?sort=topSal" class="a">판매순</a></li>
    		<li><a href="${contextPath }/list.mk?sort=topSco" class="a">평점순</a></li>
    		<li><a href="${contextPath }/list.mk?sort=latest" class="a">신상품</a></li>
    	</ul>    	
  	</li>
  </ul>
  	<c:forEach items="${cList }" var="c">
  <ul>
    <li>
      <a href="${contextPath }/list.mk?sort=topSal&category=${c.categoryNo}" class="a">${c.categoryName }</a>
    	<ul>
    		<li><a href="${contextPath }/list.mk?sort=topSal&category=${c.categoryNo}" class="a">판매순</a></li>
    		<li><a href="${contextPath }/list.mk?sort=topSco&category=${c.categoryNo}" class="a">평점순</a></li>
    		<li><a href="${contextPath }/list.mk?sort=latest&category=${c.categoryNo}" class="a">신상품</a></li>
    	</ul>
    </li>
    </ul>
    </c:forEach>
    </div>
    </nav>
    
	<br><br><br>
		<c:if test="${not empty loginUser && loginUser.authCode eq 'ADMIN' }">
			<div align="center">
			<c:choose>
				<c:when test="${i.status eq 'N' }">
				<button class='btn btn-info' onclick="reacitivateItem();">상품 활성화</button>
				<a href="${contextPath }/update.mk?itno=${i.itemCode}" class="btn btn-primary" role="botton">상품 업데이트</a>
				<script>
					function reacitivateItem(){
						var flag=confirm("상품 게시을 다시 게시하시겠습니까?");
					if(flag){
						location.href="${contextPath}/reinsert.mk?itno=${i.itemCode }"
						}
					}
				</script>
				</c:when>
				<c:otherwise>
				<button class='btn btn-danger' onclick="deleteItem();">상품 비활성화</button>
				<a href="${contextPath }/update.mk?itno=${i.itemCode}" class="btn btn-primary" role="botton">상품 업데이트</a>
				<script>
					function deleteItem(){
						var flag=confirm("상품 게시를 종료하시겠습니까?");
					if(flag){
						location.href="${contextPath}/delete.mk?itno=${i.itemCode }"
						}
					}
				</script>
				</c:otherwise>
			</c:choose>
			
			
				
			
		</div>
		<br>
		</c:if>
		
		<input type="hidden" value="${listLength + 1 }" id="count" name="count">
		
		<div class="form-area">
		<form name="addForm" action="${contextPath }/views/market/addCart.jsp?id=${i.itemCode }" method="post">
		<table align="center" border="1">
			<tr>
				<td rowspan='5'>
				
			<div class="slide">

				

				<div class="slide-container" id="imgContainer">

				<c:forEach  items="${itList }" var="it" >
					<div class="inner">
							<img src="${contextPath}${it.filePath }${it.changeName}">
						</div>
				</c:forEach>
					
					
				</div>
			</div>
				
			<div id="pagination">
			<c:forEach items="${itList }" varStatus="status">
			<button type="button" class="버튼${status.count }" onclick="movePage(${status.count})" style="width :25px; border-radius:10px ;background-color: gray; color:white;">${status.count }</button>
			</c:forEach>
			
			</div>
			
			 
				</td>
				<th>상품명 : ${i.itemName }</th>
			</tr>
			<tr>
				<th> 카테고리 : ${i.categoryName }
					</th>
			</tr>
			<tr>
				<th>판매량 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${i.salesVol }" /></th>
			</tr>
			<tr>
				
				<td style="font-size : 13px; color : gray;"><del>원가 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${i.price}"  /> 원 </del><br>
					할인률 : ${i.discount }% <br>
					<p style="font-weight : 500; font-size : 20px; color : white">
					<fmt:formatNumber type="number" maxFractionDigits="3" value="${i.totalPrice}" />원</p><br>
					<div id="quantity">수량 : </div>
					<input type="number" value="1" class="quantity" name="quantity" onclick="minLimit(this);" onkeyup="minLimit(this);">
					</td>
					
			</tr>

			<tr>
				<td>보관방법 : ${i.storageMethod } </td>
			</tr>
		</table>
		<br>
	
	 
	 <div class="order">
<br>
	<c:choose>
	<c:when test="${not empty loginUser && i.status eq 'Y' }">
	<a href="#" class="btn btn-info" onclick="addToCart()">상품추가&raquo;</a>
	<a href="${contextPath }/views/market/cart.jsp" class="btn btn-warning">장바구니&raquo;</a>
	<a href="${contextPath }/list.mk" class="btn btn-secondary">상품목록&raquo;</a>
	</c:when>
	<c:otherwise>
		
			<a href="${contextPath}/views/member/login.jsp" class="btn btn-secondary">상품추가&raquo;</a>
	<a href="${contextPath}/views/member/login.jsp" class="btn btn-secondary">장바구니&raquo;</a>
	<a href="${contextPath }/list.mk" class="btn btn-secondary">상품목록&raquo;</a>
		
	</c:otherwise>
	</c:choose>
						
	
	
	
	
	 </div>
	  <div class="component">
	 <br><br>
	<h3>영양성분</h3>
		<table>
			<tbody>
				<tr class="component_title">
					<td>열량</td>
					<td>단백질</td>
					<td>나트륨</td>
					<td>탄수화물</td>
					<td>지방</td>
					<td>트랜스지방</td>
					<td>포화지방</td>
					<td>콜레스테롤</td>
					<td>당류</td>
				</tr>
				<tr class="component_content">
					<td>${c.calorie }kcal</td>
					<td>${c.protin }g</td>
					<td>${c.salt }mg</td>
					<td>${c.carbo }g</td>
					<td>${c.fat }g</td>
					<td>${c.transFat }g</td>
					<td>${c.saturatedFat }g</td>
					<td>${c.chol }mg</td>
					<td>${c.sugar }g</td>
				</tr>
			</tbody>
		</table>
		</div>
		</form>
	<br><br>
		<div id="review-area">
			<table border="1" align="center">
				<thead>
					<c:choose>
						<c:when test="${not empty loginUser }">
							<tr>
								<th>리뷰작성</th>
								<td>
									 <div class="star-rating">
									    <input type="radio" class="star" name="score" value="1">
									    <input type="radio" class="star" name="score" value="2">
									    <input type="radio" class="star" name="score" value="3">
									    <input type="radio" class="star" name="score" value="4">
									    <input type="radio" class="star" name="score" checked="checked" value="5">
									  </div>
								</td>
								 <td>
								 	<textarea id="reviewContent" rows="3" cols="50" style="resize:none;" required></textarea>
								 </td>
								 <td><button onclick="insertReview();">작성</button> </td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th>리뷰작성</th>
								<td>
									<div class="star-rating">
									    <input type="radio" class="star" name="score" value="1">
									    <input type="radio" class="star" name="score" value="2">
									    <input type="radio" class="star" name="score" value="3">
									    <input type="radio" class="star" name="score" value="4">
									    <input type="radio" class="star" name="score" checked="checked" value="5">
									  </div>
								</td>
								 <td>
								 	<textarea readonly rows="3" cols="50" style="resize:none;">로그인 후 이용 가능한 서비스입니다.</textarea>
								 </td>
								 <td><button disabled>작성</button> </td>
							</tr>
						
						</c:otherwise>
						
						
						
					</c:choose>
					<tr>
						<th>작성자</th>
						<th>평점</th>
						<th>내용</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody class="review-list">
					
				</tbody>
			</table>
		</div>
		
	
	<br><br>
	</div>
	
	</div>
	<c:if test="${not empty loginUser}">
	<script type="text/javascript">
	function insertReview(){
			
		
		
			var checkedVal = $('[name="score"]:checked').val();
			 
			
		
			$.ajax({
				url : "insertR.mk",
				type : "post",
				data : {
					itno : ${i.itemCode},
					uno : ${loginUser.userNo},
					content : $("#reviewContent").val(),
					checkedVal : checkedVal
				},
				success : function(result){
					alert(result);
					reviewList();
				},
				error : function(){
					alert("리뷰 작성에 오류가 발생했습니다.");
				},
				complete : function(){
					
					reviewList();
				}
			});
		
	
			function reviewList(){
				$.ajax({
					url : "listR.mk",
					data : {
						itno : ${i.itemCode}
					},
					success : function(list){
						//기존에있던 댓글을 지우고 다시 조회
						$(".review-list").children().remove();
						var tr="";
						for(var i in list) {
							
							tr +="<tr>"
								+"<td>"+list[i].nickName+"</td>"
								+"<td>"+list[i].score+"</td>"
								+"<td>"+list[i].content+"</td>"
								+"<td>"+list[i].update+"</td>"
							    +"</tr>";
						}
						$(".review-list").append(tr);
					},
					error : function(){
						alert("리뷰를 불러오는데 실패했습니다.");
					}
				});
			}
		
			
			$(function(){
				//처음 게시글에 들어왔을때 댓글목록 조회후 댓글창에 표시
				reviewList();
				});
		
	}
	
	</script>
	</c:if>
	

	<script>
		
		function minLimit(num){
			
			if(num.value<=0){
				alert('0 이하는 입력할수 없습니다..');
				num.value = 1;
			}
		}
	
		function movePage(num){
			var pageLocation=450;
			var pageNo=num-1;
			var count=document.getElementById("count").value;
			document.querySelector(".버튼"+num).addEventListener('click',function(){
				document.querySelector('.slide-container').style.transform= 'translate('+(-pageNo*pageLocation)+'px)';
				
				$('.slide-container').css("width",count*pageLocation+"px");
			})
		}
	
	
	</script>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>