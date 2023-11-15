
//비동기 URL 정의 
/*TODO HSCHOE */
const URL_LOCAL_HOST = "http://localhost:8080"
const URL_FOLIO_LOGIN_CHECKED = URL_LOCAL_HOST + "/Foilo/LoginCheckAction.do" //로그인 체크 
const URL_FOLIO_LOGIN = URL_LOCAL_HOST + "/Foilo/LoginAction.do" //로그인 액션  
const URL_FOLIO_MAIN_TO_MAIN = URL_LOCAL_HOST + "/Foilo/MoveToMainAction.do" //메인으로 이동.
const URL_FOLIO_MOVE_TO_JOIN = URL_LOCAL_HOST + "/Foilo/MoveToJoinAction.do" //조인페이지 이동.

/**회원 가입 페이지로 이동 */
const goJoinPage = () => {
    location.href = URL_FOLIO_MOVE_TO_JOIN
}

const goMainPage = () => {
	location.href = URL_FOLIO_MAIN_TO_MAIN
}


/**로그인 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.innerWidth/2
    const centerY = window.innerHeight/2
    const loginContainer = document.getElementById("loginContainer")
    const loginCenterX = loginContainer.clientWidth/2
    const loginCenterY = loginContainer.clientHeight/2
    loginContainer.style.left = (centerX - loginCenterX) + 'px'
    loginContainer.style.top = (centerY - loginCenterY)+ 'px'
     console.log(`updateCenterPosition ${centerY}`)
}
//리스너 선언 부 
const clickLoginListener = ()=> {
	const idValue = document.getElementById("userId").value
	const passwordValue = document.getElementById("userPassword").value
	
	fetch(URL_FOLIO_LOGIN_CHECKED, {
		method: `POST`,
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({
			"id" : idValue, 
			"password" : passwordValue
		})
	})
	.then(response => response.json())
	.then(data => {
		const isLogin = data['isLogin']
		if(isLogin) { //로그인 성공
			alert(`로그인 성공`)
			fetch(URL_FOLIO_LOGIN+`?id=${idValue}`)
			.then(data => { goMainPage() })
			.catch(error =>console("에러 :", error))
		}else {
			alert(`로그인 실패`)
		}
	})
	.catch(error => console.log("애러 로그", error))
}

//windows가 갱신시
window.addEventListener('resize',()=> {
    console.log("resize")
	updateCenterPosition()
})

//DOM이 로드 완료될시에 
document.addEventListener('DOMContentLoaded', ()=> {
    console.log("DOMContentLoaded")
    updateCenterPosition()
})
