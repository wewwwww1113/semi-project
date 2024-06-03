<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Exercise_DetailView</title>
    <style>
        th{
            background-color:rgba(175, 45, 45, 0.884);
            color: rgba(224, 238, 238, 0.829);
        }
        .title{
        width:66%;
        }
        tr{
        text-align: center;
        }
        #ci{
        	background-color:rgba(168, 134, 134, 0.884);
        }
    </style>
</head>
<body>
    <%@ include file="/views/common/menubar.jsp" %>
    <div class="outer" id="md">
		<br>
        <!--button onclick="history.back()">Go Back</button>-->
        <label onclick="location.href='${contextPath}/list.ex';" style="border: 1px solid grey">←목록으로</label>
        
		<!-- h2 align="center">${e.exerTitle}운동이름</h2>-->
		<br>
		<form action="${contextPath}/detail.ex?eno=${ex.exerNo}" method="post" enctype="multipart/form-data" id="detail-form">
			
			<table align="center" border="5" width="67%">
				
				<tr>
					<th colspan="3">
                        <h2 align="center">${ex.exerTitle}</h2>
                    </th>
				</tr>
							<c:forEach items="${ap}" var="a" varStatus="w">
								<c:choose>
									<c:when test="${w.index eq 0}">
                            			<tr>
                            				<th colspan="3">
                             					<img id="titleImg" src="${contextPath}${a.filePath}${a.changeName}">
                            				</th>
                            			</tr> 
                            		</c:when>
                            		<c:otherwise>
                            			
                            				<td id="ci">
                                    			<img id="contentImg${w.index}" src="${contextPath}${a.filePath}${a.changeName}" height="200px">
                                			</td>
                                		
                            		</c:otherwise>
                            	</c:choose>
                            </c:forEach>
				
		
                      
		
                            <tr>
                                <td colspan="3">
                                    <img src="<%=request.getContextPath() %>/resources/logo/white.png" width="100%"> 
                                    <!--없을때는 white사진 출력-->
                                </td>
                            </tr>
                <tr>
					<td colspan="3">
                        ${ex.exerInf}
                    </td>
				</tr>
				<tr>
					<th width="30%">운동종류</th>
					<td colspan="3">
						<p>${ex.exerType}</p>
					</td>
				</tr>
                <tr>
					<th>운동부위</th>
					<td colspan="3">
						<p>${ex.categoryName}</p>
					</td>
				</tr>
                <tr>
                    <td colspan="3">
                        ${ex.exerContent}
                    </td>
                </tr>
                
			</table>
			<br><br>
			<div align="center">
				<button type="submit" style="display:none">글수정</button>
                <!--style="display:none"-->
			</div>
		</form>
	</div>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>