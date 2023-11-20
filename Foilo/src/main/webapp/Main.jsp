<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./style/CommonStyle.css">
    <link rel="stylesheet" href="./style/MainStyle.css">
<title>-처음부터 시작하는 간단한 홈페이지에 오신걸 환영합니다.-</title>
</head>
<body>
    <div class="rootContainerStyle" id="rootContainer"> <!--루트-->
        <div class="loginRootContainer" id="loginRootContainer"> <!--로그인 루트-->
            <div id="postItHeaderDiv"></div>
            <div id="loginInfoContainer">
                <label class="nomalTitleStyle" id="idLabel">아이디 : '${sessionScope.memberInfo.cstNm }' 님 반갑습니다 :)</label>
            </div>
            <div id="menuContainer">
                <button class="buttonStyle" id="myPage" onclick='onLoadSetting(`${sessionScope.memberInfo}`)'>설정</button><br>
                <button class="buttonStyle" id="buttonLogout" type="button" onclick="onLogout()">로그아웃</button><br>
            </div>
        </div>
        <div class="boardStyle" id="boardContainer"> <!-- 컨텐츠 영역으로 사용될 녀석 일종의 프레그먼트. -->
        </div> <!--로그인 루트-->
        <button class="bottomButtonStyle" onclick="onLoadInsertBoard()">글쓰기</button>
    </div> <!--루트-->
</body>
<script src="./script/MainPage.js"></script> 
</html>