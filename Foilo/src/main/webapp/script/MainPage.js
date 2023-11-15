
/*TODO HSCHOE */
const URL_LOCAL_HOST = "http://localhost:8080"
const URL_FOLIO_MAIN_TO_LOGIN = URL_LOCAL_HOST + "/Foilo/MoveToLoginAction.do" //로그인으로 이동.
const URL_FOLIO_LOGOUT = URL_LOCAL_HOST + "/Foilo/LogoutAction.do" //로그아웃 액션 

const contentDiv = document.getElementById("boardContainer")

/**글쓰기로 컨텐츠 전환 */
const onLoadInsertBoard = () => {
	/* 글쓰기 리스트로 페이지 전환시에 글쓰기 버튼을 감추거나 다른걸로 변경 한다.
	글쓰기 폼을 innerHtml로 갈아치우고 처리한다.
	데이터는 json으로 BoardVo으로 동일하게 작업한다.
	 */
	contentDiv.innerHTML = `<p>글쓰기</p>`
}

/**게시글 리스트로 컨텐츠 전환 */
const onLoadBoardList = () => {
	/*
		위에 onLoadBard와 반대로 작업하면됨 
	 */
	contentDiv.innerHTML = `<p>게시글</p>`
}

const onLoadPrintBoard = () => {
	/*
		위에 onLoadBard와 반대로 작업하면됨 
	 */
	contentDiv.innerHTML = `<p>글보기</p>`
}

const onLoadSetting = (info) => {
	const memberInfo = JSON.parse(info)
	const male =(memberInfo.gender === "남")? 'checked' : '';
	const fmale =(memberInfo.gender === "여")? 'checked' : '';
	/*세팅 화면*/
	 contentDiv.innerHTML = "<div id='settingRootContainer'>"+
	 							"<p class='nomalTitleStyle' id='settingTitle'>설정화면</p><br>"+
	 							"<p class='subTitleStyle'>이름</p>"+
	 							`<input class='settingTextInput' id='modifyName' type='text' value=${ memberInfo['cstNm'] }><br>`+
 								"<p class='subTitleStyle'>성별</p>"+
 								"<label><input id='selectGender' name='gender' type='radio' value='남' "+male+">남</label>"+
	                    		"<label><input id='selectGender' name='gender' type='radio' value='여' "+fmale+">여</label><br>"+
								"<p class='subTitleStyle'>이메일</p>"+
								`<input class='settingTextInput' id='modifyEmail' type='email' value='${memberInfo['email']}'><br>`+
								`<p class='subTitleStyle'>주소</p>`+
								`<input class='settingTextInput' id='modifyAddress' type='text' value='${memberInfo['address']}'><br>`+
								`<p class='subTitleStyle'>자기소개</p>`+
	 							`<textarea class='settingTextInput'>${ memberInfo['aboutMe'] })}</textarea><br>`+
	 							"<button class='settingNomalButton' id='delAccountButton' onclick='deleteAccount()'>회원 탈퇴</button>"+
	 							"<button class='settingNomalButton' id='modifyPassWord' onclick='passWordModfiy()'>비밀번호 변경하기</button>" +
	 							"<button class='settingNomalButton' id='modifyOkay' onclick='modfiyOkay()'>수정 완료</button>"
	 						"</div>"
}

const passWordModfiy = () => {
	confirm("비밀번호를 변경하시겠어요?")
}

const deleteAccount = () => {
	confirm("회원을 탈퇴하시겠습니까?")
}

const modfiyOkay = () => {
	confirm("수정을 완료 하시겠습니까?")
}

const goLoginPage = () => {
	location.href = URL_FOLIO_MAIN_TO_LOGIN
}

/**화면 중앙 정렬 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.innerWidth/2
    const centerY = window.innerHeight/2
    const rootContainer = document.getElementById("rootContainer")
    const mainCenterX = rootContainer.clientWidth/2
    const mainCenterY = rootContainer.clientHeight/2
    rootContainer.style.left = (centerX - mainCenterX) + 'px'
    rootContainer.style.top = (centerY - mainCenterY)+ 'px'
    console.log(`updateCenterPosition ${centerY}`)
}

//windows가 갱신시
window.addEventListener('resize',() => {
    console.log("resize")
	updateCenterPosition()
})

//DOM이 로드 완료될시에 
document.addEventListener('DOMContentLoaded', ()=> {
    console.log("DOMContentLoaded")
    updateCenterPosition()
})

const onLogout = ()=> {
	fetch(URL_FOLIO_LOGOUT)
	.then(response => response.json())
	.then(data => { 
		const isLogout = data['isLogout']
		if(isLogout) { //로그아웃 성공
			alert(`로그아웃이 정상적으로 진행되었습니다.`) 
			goLoginPage()
		}
	 })
	.catch(error => console.error("에러 :", error))
}
