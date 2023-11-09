<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
String userId = (String)session.getAttribute("userId");
System.out.println("UserId : " + userId);
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./style/CommonStyle.css">
    <link rel="stylesheet" href="./style/MainStyle.css">
<title>Insert title here</title>
</head>
<body>
    <div class="rootContainerStyle" id="rootContainer">
        <div class="loginRootContainer" id="loginRootContainer">
            <div id="postItHeaderDiv"></div>
            <div id="loginInfoContainer">
                <label class="nomalTitleStyle" id="idLabel">아이디 : <%=userId%>님</label>
            </div>
            <div id="menuContainer">
                <button class="buttonStyle" id="myPage">설정</button><br>
                <button class="buttonStyle" id="buttonLogout" type="button">로그아웃</button> <br>
            </div>
        </div>
    </div>
</body>
<script src="./script/MainPage.js"></script> 
</html>