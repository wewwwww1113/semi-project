<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>운동 기록 수정</title>
</head>
<body>
    <%@ include file="/views/common/menubar.jsp" %>
    <div align="center">
        <h2 align="center">운동 기록 수정</h2>
        <form action="${contextPath }/MemorialsUpdateController.me" method="post" enctype="multipart/form-data">
            <input type="hidden" name="memorialsNo" value="${me.memorialsNo}">
            <input type="hidden" name="muserId" value="${loginUser.userId }" required>
            <table border="1">
                <tr align="center">
                    <td colspan="2">날짜 <br> <input type="date" name="memorialsDate" value="${me.memorialsDate}">
                        <br>
                    </td>
                </tr>
                <tr>
                    <td>몇 시간? : <select name="memorialsTime">
							<option value="30분" ${me.memorialsTime eq '30분' ? 'selected' : ''}>30분</option>
							<option value="40분" ${me.memorialsTime eq '40분' ? 'selected' : ''}>40분</option>
							<option value="50분" ${me.memorialsTime eq '50분' ? 'selected' : ''}>50분</option>
							<option value="1시간" ${me.memorialsTime eq '1시간' ? 'selected' : ''}>1시간</option>
							<option value="1시간 30분" ${me.memorialsTime eq '1시간 30분' ? 'selected' : ''}>1시간 30분</option>
							<option value="2시간" ${me.memorialsTime eq '2시간' ? 'selected' : ''}>2시간</option>
							<option value="2시간 30분" ${me.memorialsTime eq ' 2시간30분' ? 'selected' : ''}>2시간 30분</option>
							<option value="3시간" ${me.memorialsTime eq '3시간' ? 'selected' : ''}>3시간</option>
							<option value="3시간 30분" ${me.memorialsTime eq '3시간 30분' ? 'selected' : ''}>3시간 30분</option>
							<option value="4시간" ${me.memorialsTime eq '4시간' ? 'selected' : ''}>4시간</option>
					</select>
                    </td>
                    
                    <td>오늘 어느 부위 했어? <br>
					    <label><input type="checkbox" name="memorialsParts" value="chest" ${mas != null && mas.contains('chest') ? 'checked' : ''}/> 가슴 </label>
					    <label><input type="checkbox" name="memorialsParts" value="arm" ${mas != null && mas.contains('arm') ? 'checked' : ''}/> 팔</label>
					    <label><input type="checkbox" name="memorialsParts" value="back" ${mas != null && mas.contains('back') ? 'checked' : ''}/> 등</label> <br>
					    <label><input type="checkbox" name="memorialsParts" value="shoulder" ${mas != null && mas.contains('shoulder') ? 'checked' : ''}/> 어깨</label>
					    <label><input type="checkbox" name="memorialsParts" value="lowerbody" ${mas != null && mas.contains('lowerbody') ? 'checked' : ''}/> 하체</label>
					    <label><input type="checkbox" name="memorialsParts" value="cardio" ${mas != null && mas.contains('cardio') ? 'checked' : ''}/> 유산소</label>
					</td>
                </tr>
                <tr>
                    <td colspan="2">오운완 이미지 파일 업로드 <br> <input type="file" name="memorialsImg">
                        <br><img src="${contextPath }${at.filePath}${at.changeName}" alt="운동 기록 이미지">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">특이사항 및 메모 <br> <textarea name="memorialsContent">${me.memorialsContent}</textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">오늘 나에게 주는 점수! <br> <input type="range" name="MemorialsSelfScore" value="${me.memorialsSelfScore}">
                    </td>
                </tr>
            </table>
            <br>
            <button type="submit">운동일지 수정하기!</button>
        </form>
    </div>
    <%@ include file="/views/common/footer.jsp" %>
</body>
</html>