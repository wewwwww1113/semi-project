<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.detail-area,.reply-area{
width:80%;
margin:auto;
}
.detail-area table{
width:90%;
}
.reply-area table{
width:80%;
}
#insertReply td{
text-align:center;
}
#insertReply textarea,#insertReply button{
width:100%;
}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<br>
	<div align="center" class="detail-area">
	<table border="1">
	<thead>
	<tr>
		<th width="10%">작성자</th>
		<th width="15%">${b.boardWriter}</th>
		<th width="15%">최종수정일</th>
		<th width="30%" style="text-align:center">${b.reviseDate }</th>
		<th width="10%">조회수</th>
		<th width="5%">${b.count }</th>
		<th width="10%">추천수</th>
		<th width="5%" id="RC">${b.recommend }</th>
	</tr>
	<tr>
		<th colspan="8" style="text-align:left">${b.boardTitle }</th>
	</tr>
	</thead>
	<tbody>
	<tr>
	<td colspan="8"><p>${b.boardContent}</p></td>
	</tr>
	<tr>
	<td colspan="8" style="text-align:center"><button onclick="increaseRC();">추천</button></td>
	</tr>
	<!-- 로그인 상태 확인 and 로그인유저 아이디/닉네임이 글작성자와 동일 또는 권한코드가 'ADMIN'인경우 -->
	<c:if test="${!empty loginUser and (loginUser.userId eq b.boardWriter or loginUser.nickName eq b.boardWriter or loginUser.authCode eq 'ADMIN') }">
	<tr>
	<td colspan="6"></td>
	<td colspan="2" style="text-align:center" onclick="location.href='update.bo?bno=${b.boardNo}'"><button>글 수정하기</button></td>
	</tr>
	</c:if>
	</tbody>
	</table>
	</div>
	<script>
	function increaseRC(){
		if(${empty loginUser.userNo}) {
			alert("해당 기능은 로그인 이후에 사용가능합니다.");
		} else {
		$.ajax({
			url : "recommend.bo",
			data : {
				bno : ${b.boardNo},
				uno : "${loginUser.userNo}"
			},
			success : function(Message){
				switch(Message){
				case "RCNNN":
					alert("이미 이 글을 추천했습니다.");
					break;
				case "RCYYN":
					alert("추천에 실패했습니다");
					break;
				case "RCYYY":
					alert("이 글을 추천했습니다.");
					$("#RC").text(${b.recommend}+1);
					break;
				}
			},
			error : function(){
				alert("추천에 실패했습니다.");	
			}
		});
		}
	}
	</script>
	<br>
	<div class="reply-area" align="center">
	<table border="1" class="reply-list">
	<thead>
		<tr>
		<th width="10%">작성자</th>
		<th width="75%">댓글내용</th>
		<th width="15%">작성일</th>
		</tr>
	</thead>
	<tbody>

	</tbody>
	</table>
	<br>
	<table border="1" id="insertReply">
	<tr>
		<td width="120px">댓글 작성란</td>
		<td><textarea id="replyContent" rows="5" style="resize:none;"></textarea></td>
		<td width="100px"><button onclick="insertReply();" style="height:50px">작성</button></td>
	</tr>
	</table>
	</div>
	<script>
	function insertReply(){
		if($("#replyContent").val()==""){
			//작성하지 않았으면 실행하지 않음
			alert("댓글을 작성하지 않았습니다.");
			return false;
		}
		var userNo=0;
		if(${!empty loginUser}) {
			userNo="${loginUser.userNo}"
		}
		//로그인했으면 작성자를 로그인 유저의 아이디(닉네임)으로, 안했으면 "익명"으로 표시
		
		$.ajax({
			url : "insertReply.bo",
			type : "post",
			data : {
				bno : ${b.boardNo},
				uno : userNo,
				content : $("#replyContent").val()
			},
			success : function(result){
				alert(result);
				$("#replyContent").val("");
			},
			error : function(){
				alert("댓글 작성에 오류가 발생했습니다.");
			},
			complete : function(){
				//댓글작성 완료후 작성한 댓글을 포함하기 위해 댓글목록을 다시 조회후 표시
				replyList();
			}
		});
	}
	function replyList(){
		$.ajax({
			url : "reply.bo",
			data : {
				bno : ${b.boardNo}
			},
			success : function(list){
				//기존에있던 댓글을 지우고 다시 조회
				$(".reply-list tbody").children().remove();
				var tr="";
				for(var i in list) {
					
					tr +="<tr>"
						+"<td>"+list[i].replyWriter+"</td>"
						+"<td id="+list[i].replyNo+">"+list[i].replyContent+"</td>"
						+"<td>"+list[i].createDate+"</td>"
					    +"</tr>";
				}
				$(".reply-list tbody").append(tr);
			},
			error : function(){
				alert("댓글을 불러오는데 실패했습니다.");
			}
		});
	}
	function deleteReply(rId,rName){
		if(confirm(rName+"의 댓글을 삭제하시겠습니까?")){
			$.ajax({
				url : "deleteReply.bo",
				data : {
					rNo : rId
				},
				success : function(message){
					alert(message);
				},
				error : function(){
					alert("삭제에 실패했습니다.");
				},
				complete : function(){
					replyList();
				}
			});
		}
	}
	
	$(function(){
		//처음 게시글에 들어왔을때 댓글목록 조회후 댓글창에 표시
		replyList();
		
		$(document).on("click",".reply-list tbody tr",function(){
			var rName=$(this).children().eq(0).text();
			var rId=$(this).children().eq(1).attr('id');
			var id="${loginUser.userId}";
			var name="${loginUser.nickName}";
			var bId="${b.boardWriter}";
			
			if(${!empty loginUser}){
			if((id==bId)||(name==bId)){
				//이 글의 작성자인 경우
				deleteReply(rId,rName);
			}else if((id==rName)||(name==rName)){
				//해당 댓글의 작성자인 경우
				deleteReply(rId,rName);
			}else if(${loginUser.authCode eq 'ADMIN'}){
				//로그인 아이디가 운영자 권한을 가진경우
				deleteReply(rId,rName);
			}
			}
			
			
		});
	});
	</script>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>