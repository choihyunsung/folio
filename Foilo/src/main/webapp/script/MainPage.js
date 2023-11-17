
/*TODO HSCHOE */
const URL_LOCAL_HOST = "http://localhost:8080"
const URL_FOLIO_MAIN_TO_LOGIN = URL_LOCAL_HOST + "/Foilo/MoveToLoginAction.do" //로그인으로 이동.
const URL_FOLIO_LOGOUT = URL_LOCAL_HOST + "/Foilo/LogoutAction.do" //로그아웃 액션 
const URL_FOLIT_DELETE_ACCOUNT = URL_LOCAL_HOST + "/Foilo/DeleteAccountAction.do" //회원 탈퇴
const URL_PAGE_POPUP_PASSWORD_MODIFY = "./PasswordModifyPopUp.html" //비밀번호팝업 페이지

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
	const memberInfoJSON = JSON.parse(info)
	const male =(memberInfoJSON.gender === "남")? 'checked' : '';
	const fmale =(memberInfoJSON.gender === "여")? 'checked' : '';
	/*세팅 화면*/
	 contentDiv.innerHTML = "<div id='settingRootContainer'>"+
	 							"<p class='nomalTitleStyle' id='settingTitle'>설정화면</p><br>"+
	 							"<p class='subTitleStyle'>이름</p>"+
	 							`<input class='settingTextInput' id='modifyName' type='text' value=${ memberInfoJSON['cstNm'] }><br>`+
 								"<p class='subTitleStyle'>성별</p>"+
 								"<label><input id='selectGender' name='gender' type='radio' value='남' "+male+">남</label>"+
	                    		"<label><input id='selectGender' name='gender' type='radio' value='여' "+fmale+">여</label><br>"+
								"<p class='subTitleStyle'>이메일</p>"+
								`<input class='settingTextInput' id='modifyEmail' type='email' value='${memberInfoJSON['email']}'><br>`+
								`<p class='subTitleStyle'>주소</p>`+
								`<input class='settingTextInput' id='modifyAddress' type='text' value='${memberInfoJSON['address']}'><br>`+
								`<p class='subTitleStyle'>자기소개</p>`+
	 							`<textarea class='settingTextInput'>${ memberInfoJSON['aboutMe'] })}</textarea><br>`+
	 							`<button class='settingNomalButton' id='delAccountButton' onclick='deleteAccount(${info})'>회원 탈퇴</button>`+
	 							"<button class='settingNomalButton' id='modifyPassWord' onclick='passWordModfiy()'>비밀번호 변경하기</button>" +
	 							"<button class='settingNomalButton' id='modifyOkay' onclick='modfiyOkay()'>수정 완료</button>"+
	 						"</div>"
}

const passWordModfiy = () => {
	if(confirm("비밀번호를 변경하시겠어요?")) {
		showPasswordModifyPopUp()
	} else {
		//아무것도 안함.
	}
}

const deleteAccount = (info) => {
	if(confirm("회원을 탈퇴하시겠습니까?")) {
		console.log(info)
		//회원탈퇴에 JSON 파라메터 보냄
		fetch(URL_FOLIT_DELETE_ACCOUNT, {
			method: 'POST',
			headers: {
				"Content-Type" : "application/json"
			},
			body: JSON.stringify(info)
		})
		.then(response => response.json())
		.then(data => {
				const isDelAccount = data['isDeleteAccount']
				console.log(data + `isDelAccount : ${isDelAccount}`)
				if(isDelAccount) {
					alert("회원탈퇴가 완료되었습니다. 로그인 화면으로 이동합니다.")
					goLoginPage();
				} else {
					alert("알수 없는 이유로 회원 탈퇴 실패 되었습니다.")
				}
			}
		)
		.catch(error => console.error("에러: ",error))
	} else {
		//아무것도 안함.
	}
}

const modfiyOkay = () => {
	if(confirm("수정을 완료 하시겠습니까?")) {
		
	} else {
		
	}
}

const goLoginPage = () => {
	location.href = URL_FOLIO_MAIN_TO_LOGIN
}

const showPasswordModifyPopUp = () => {
		// JS
		var popupWidth = 250;
		var popupHeight = 250;
		var options = `top = ${ 10 }, left = ${ 10 }, width = ${ popupWidth }, height = ${ popupHeight }`;
		window.open(URL_PAGE_POPUP_PASSWORD_MODIFY, "비밀번호 변경", options);
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
