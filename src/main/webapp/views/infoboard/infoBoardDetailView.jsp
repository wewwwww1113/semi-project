<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.infodetail-area, .reply-area {
	width: 80%;
	margin: auto;
}

.infodetail-area table {
	width: 90%;
}

.reply-area table {
	width: 80%;
}

#insertReply td {
	text-align: center;
}

#insertReply textarea, #insertReply button {
	width: 100%
}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp"%>

	<br>
	<div align="center" class="infodetail-area">
		<table border="1">
			<thead>
				<tr>
					<th width="10%">작성자</th>
					<th width="15%"></th>
					<th width="15%">최종수정일</th>
					<th width="30%" style="text-align: center">${ib.reviseDate}</th>
					<th width="10%">조회수</th>
					<th width="5%">${ib.count}</th>
					<th width="10%">추천수</th>
					<th width="5%">${ib.recommend}</th>
				</tr>
				<tr>
					<th colspan="8"><p>${ib.boardContent}</p></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="8" style="text-align: center"><button onclick="increaseRC();">추천</button></td>
				</tr>
				<!-- 로그인 상태 확인 and 로그인 유저 아이디 닉네임이 글 작성자와 동일 또는 권한코드가 운영자인 경우 -->
				<c:if
					test="${!empty loginUser and (loginUser.userId eq ib.boardWriter or loginUser.nickName eq ib.boardWriter or loginUser.authCode eq 'ADMIN')}">
					<tr>
						<td colspan="6"></td>
						<td colspan="2" style="text-align: center" onclick="location.href='infoupdate.bo?bno=${ib.boardNo}'"><button>글 수정하기</button></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<script>
	function increaseRC(){
		if(${empty loginUser.userNo}){
			alert("해당 기능은 로그인 이후에 가능합니다.");
		}else{
			$.ajax({
				url : "inforecommend.no",
				data : {
					bno : ${ib.boardNo},
					uno : "${loginUser.userNo}"
				},
				success : function (Message){
					alert(Message);
					
				},
				error : function(){
					alert("추천에 실패했습니다.");
				}
			});
		}
	}
	</script>
	<div class="reply-area" align="center">
	<table border="1" class="reply-list">
	<thead>
	<tr>
	<th width="75px">작성자</th>
	<th>댓글내용</th>
	<th width="120px">작성일</th>
	</tr>
	</thead>
	<tbody>
	
	</tbody>
	</table>
	<br>
	<table border="1" id="infoinsertReply">
	<tr>
	<td width="120px">댓글 작성란</td>
	<td><textarea id="replyContent" rows="5" style="resize:none;"></textarea></td>
	<td width="100px"><button onclick="infoinsertReply();" style="height:50px">작성</button></td>
	</tr>
	</table>
	</div>
	
	<script>
	function insertReply(){
		if($("#replyCount").val()==""){
			//작성하지않았으면 실행하지않음
			alert("댓글을 작성하지 않았습니다");
			return false;
			
		}
		var=userNo;
		if(${!empty loginUser}) {
			userNo="${loginUser.userNo}"
		}
		
		//로그인 했으면 작성자를 로그인 유저의 아이디(닉네임)으로, 안했으면 "익명"으로 표시
		
		$.ajax{
			url : "infoinsertReply.bo".
			type : "post",
			date : {
				bno : ${ib.boardNo},
				uno : userNo,
				content : $("#replyContent").val()
				
			},
			success : function(result){
				alert(result);
			},
			error : function(){
				alert("댓글 작성에 오류가 발생했습니다.");
			},
			complate : function(){
				//댓글 작성 완료후 작성한 댓글을 포함하기 위해 댓글 목록을 다시 조회 후 표시
				replayList();
				}
			
		});
	}
			
  </script>
		<%@ include file="/views/common/footer.jsp" %>
</body>
</html>