<%@page import="com.kh.board.model.vo.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HEALTHLIFE</title>




<style>
	#form_area{
		margin : auto;
		width : 800px;
	}
	.slide-container{
		width: 900px;
		height : 500px;
		transition : transform 0.5s;
	}
	.inner{
		width: 480px;
		height : 480px;
		float : left;
	}
	.inner img{
			width: 100%;
			height : 100%;
	}
	.component input{
		margin : 5px;
		height : 20px;
		width: 60px;
	}
	.component_title{
		font-weight : lighter;
		font-size : 13px; 
	}
	.main{
		width : 100%;
	}
	.slide{
		width : 480px; 
		height : 480px; 
		overflow : hidden
	}
	.enter{
		width : 100px;
		height : 50px;
		background-Color : skyblue;
		border-radius : 10px;
	}
</style>

</head>
<body>
	
	<%@ include file = "/views/common/menubar.jsp" %>
	<div class="outer">
	<br>
		<h2 align="center">상품정보 갱신 페이지</h2>
		<br>
		<div id="form_area">
		<form action="${contextPath }/update.mk" method="post" enctype="multipart/form-data" id="insert-form"> 
		<input type="hidden" value="${i.itemCode }" name="itno">
		<input type="hidden" value="${listLength + 1 }" id="count" name="count">
		<table class="main" align="center" border="1">
			<tr>
				<td rowspan='6' width="480px">
				
	
			<div class="slide">
				<div class="slide-container" id="imgContainer">
				<c:forEach items="${itList }" var="it" varStatus="status">
					<div class="inner ${status.count }">
						<img class="${status.count }" id="productImg${status.count }" src="${contextPath}${it.filePath }${it.changeName}"
						onclick="inputImg(${status.count});">
					</div>
					</c:forEach>
				</div>
			</div>
			
				<div id="pagination">
				<c:forEach items="${itList }" varStatus="status">
				<button type="button" class="버튼${status.count } ${status.count}" onclick="movePage(${status.count})" style="width :25px; border-radius:10px ;background-color: gray; color:white;">${status.count }</button>
				</c:forEach>
				</div>
			
			
			
			 
				</td>
				<th>상품명 : <input type=text name="productName" value="${i.itemName }" required></input></th>
			</tr>
			<tr>
				<th> 카테고리 : <select name="category">
					<c:forEach items="${cList }" var="cList">
					
					<option value="${cList.categoryNo}"
					<c:if test="${i.category eq cList.categoryNo }">
					  	selected="selected"
					  </c:if>>${cList.categoryName}</option>
					
					</c:forEach>
					 
					</select>
					</th>
			</tr>
			<tr>
				
				<th>가격 : 
				<input type="number" id="price" name="price" value="${i.price }"
				required onkeyup="noMinus(this);autoCal();"></th>
				
			</tr>
			<tr>
				<td>할인률 : 
				<input type="number" id="discount" name="discount" value="${i.discount }"
				min="0" max="99" required onkeyup="noMinus(this);autoCal();limit(num)">%</td>
			</tr>
			<tr>
				<td>총가격 : <input type="text" name="totalPrice" id="totalPrice" value="${i.totalPrice }" readonly>
			</tr>
			
			<tr align="center">
				<td>보관방법 : <select name="storageMethod">
				<option value="냉장보관">냉장보관</option>
				<option value="상온보관">상온보관</option>
				<option value="냉동보관">냉동보관</option>
				<option value="비식품">비식품</option>
							</select></td>
			</tr>
			
			
				
		</table>
		<br>
		<div id="img-area">
		<c:forEach items="${itList }" varStatus="status">
		<label for="itemImg${status.count }" class="${status.count }">상품이미지</label>
		<input type=file class="${status.count }" id="itemImg${status.count }" name="itemImg${status.count }" 
		onchange="loadImg(this,${status.count });"> <c:if test="${status.count gt 1 }"><button type='button' onclick="deleteSlide(${status.count})">${status.count }번삭제</button></c:if> <br>
		</c:forEach>
		</div>
		
		<button type="button" onclick="addImg();">사진 추가 입력</button> <br>
		
		
	
	<br><br>
	
	<div class="component">
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
				<tr>
					<td><input type="number" name="calorie" step="0.1" value="${c.calorie}"
					required onkeyup="noMinus(this);">kcal</td>
					<td><input type="number" name="protin" step="0.1" value="${c.protin}"
					required onkeyup="noMinus(this);">g</td>
					<td><input type="number" name="salt" step="0.1" value="${c.salt}"
					required onkeyup="noMinus(this);">mg</td>
					<td><input type="number" name="carbo" step="0.1" value="${c.carbo}"
					required onkeyup="noMinus(this);">g</td>
					<td><input type="number" name="fat" step="0.1" value="${c.fat}"
					required onkeyup="noMinus(this);">g</td>
					<td><input type="number" name="transFat" step="0.1" value="${c.transFat}"
					required onkeyup="noMinus(this);">g</td>
					<td><input type="number" name="saturatedFat" step="0.1" value="${c.saturatedFat}"
					required onkeyup="noMinus(this);">g</td>
					<td><input type="number" name="chol" step="0.1" value="${c.chol}"
					required onkeyup="noMinus(this);">mg</td>
					<td><input type="number" name="sugar" step="0.1" value="${c.sugar}"
					required onkeyup="noMinus(this);">g</td>
				</tr>
			</tbody>
		</table>
	<br><br>
	</div>
	<div align="center">
	<button class="enter" type="submit">등록</button>
	</div>
	
	<br><br>
	
	</form>
	</div>
	<script>
		
	
	function noMinus(num){
		
		if(num.value<0){
			alert('음수값은 입력이 불가능합니다.');
			num.value = 0;
		}
		
		
		
		}
		function limit(num){
			if(num.value>=100){
				alert('100%이상은 입력이 불가능합니다.');
				num.value = 0;
			}
			
			
		}
		function autoCal(){
			
			var price=document.getElementById("price").value;
			var discount=document.getElementById("discount").value;
			
			document.getElementById("totalPrice").value=Math.round(parseInt(price)*(1-discount/100)/10)*10;
			
			
		}
		
		function movePage(num){
			var pageLocation=480;
			var pageNo=num-1;
			var count= document.getElementById("count").value;
			document.querySelector(".버튼"+num).addEventListener('click',function(){
				document.querySelector('.slide-container').style.transform= 'translate('+(-pageNo*pageLocation)+'px)';
				
				$('.slide-container').css("width",count*pageLocation+"px");
			})
		}
			
		

		function inputImg(num){
			$("#itemImg"+num).click();
		}
		
		
		function loadImg(inputFile,num){
			if(inputFile.files.length==1){
				var reader = new FileReader();
				
				reader.readAsDataURL(inputFile.files[0]);
				
				reader.onload =function(e){
						$("#productImg"+num).attr("src",e.target.result);
					}
				}else{
					$("#productImg"+num).attr("src",null);
			}
		}
		function addImg(){
			var count=document.getElementById("count");
			$("#pagination")
			.append("<button type='button' class='버튼"+count.value+" "+count.value+"' style='width :25px; border-radius:10px ;background-color: gray; color:white;' onclick='movePage("+count.value+")'>"+count.value+"</button>");
			
			$("#img-area")
			.append("<label for='itemImg"+count.value+" class='"+count.value+"'>상품이미지</label> <input type=file class="+count.value+" id='itemImg"+count.value+"' name='itemImg"+count.value+"' onchange='loadImg(this,"+count.value+");'><button type='button' class='"+count.value+"' onclick='deleteSlide("+count.value+"})'>"+count.value+"번삭제</button><br>");
			
			$("#imgContainer")
			.append("<div class='inner "+count.value+"'><img id='productImg"+count.value+"' onclick='inputImg("+count.value+");'></div>");
			count.value++;
		}
		
		function deleteSlide(num){
			
			$.ajax({
				url : "deleteSlide.mk",
				type : "post",
				data : {
					num : num,
					itno : ${i.itemCode}
				},
				success : function(result){
						var data =null;
					if(result>0){
						data=$("."+num).detach();
					}
					},
				error : function(){
					console.log("통신 실패");
				}
				
			});
		}
	</script>
	
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>