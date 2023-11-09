
//비동기 URL 정의 
const URL_LOCAL_HOST = "http://localhost:8080"
const URL_FOLIO_LOGIN = URL_LOCAL_HOST + "/Foilo/LoginAction.do" //로그인 

/**회원 가입 페이지로 이동 */
const goJoinPage = () => {
    location.href = `Join.html`
}

const goMainPage = () => {
	location.href = `Main.jsp`
}


/**로그인 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.outerWidth/2
    const centerY = window.outerHeight/2
    const loginContainer = document.getElementById("loginContainer")
    const loginCenterX = loginContainer.clientWidth/2
    const loginCenterY = loginContainer.clientHeight/2
    loginContainer.style.left = (centerX - loginCenterX) + 'px'
    loginContainer.style.top = (centerY - loginCenterY)+ 'px'
    console.log("updateCenterPosition")
}
//리스너 선언 부 
const clickLoginListener = () => {
	const idValue = document.getElementById("userId").value
	const passwordValue = document.getElementById("userPassword").value
	
	fetch(URL_FOLIO_LOGIN, {
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
			goMainPage();
		}else { //로그인 실패 
			alert(`로그인 실패`)
		}
	})
	.catch(error => console.log("애러 로그", error))
	
	return false
}

//windows가 갱신시
window.addEventListener('resize',() => {
    console.log("resize")
	setTimeout(() => {
    	updateCenterPosition()
	},100)
})

//DOM이 로드 완료될시에 
document.addEventListener('DOMContentLoaded', ()=> {
    console.log("DOMContentLoaded")
    updateCenterPosition()
})
