//변수 선언문 시작
const pwdInput = document.getElementById("passwordInput") //패스워드 인풋
const pwdChecked = document.getElementById("passwordChecked"); //패스워드 체크
const emailInput = document.getElementById("emailInput") //이메일 input 창.
const idInput = document.getElementById("inputId") //아이디 입력창.
//벨류데이션 에러 메세지 정의

const ERROR_PASSWORD_VALUDATION = "입력한 비밀번호가 올바르지 않습니다."
const ERROR_EMAIL_VALUDATION = "이메일 형식이 올바르지 않습니다."
const ERROR_NAME_VALUDATION = "이름은 빈값이 들어갈수 없습니다."
const ERROR_ADDRESS_VALUDATION = "주소는 빈값이 될수 없습니다."
const ERROR_ID_EMPTY_VALUDATION = "아이디가 빈값입니다."
const ERROR_ID_DUPLICATE_VALUDATION = "이미 등록된 아이디가 있습니다."
const ERROR_ID_VALUDATION = "입력한 아이디가 올바르지 않습니다."
//비동기 URL 정의 
const URL_LOCAL_HOST = "http://localhost:8080"
const URL_FOLIO_ID_CHECKED = URL_LOCAL_HOST + "/Foilo/IdCheckedAction.do" //회원가입 아이디 체크

let isIdChecked = false; //아이디 체크 불린값 @TODO HSCHOE이거 나중에 리펙토링 꼭해야함 

//변수 선언문 끝
//Callback 선언문
//windows가 갱신시
window.addEventListener('resize',() => {
    console.log("resize")
    setTimeout(() => {
        updateCenterPosition()
    },50)
})

//DOM이 로드 완료될시에 
document.addEventListener('DOMContentLoaded',()=> {
    console.log("DOMContentLoaded")
    updateCenterPosition()
})

/***아이디 중복체크 */
const valudationCheckedAll = () => {
	let nameInput = document.getElementById("inputName")
	let addressInput = document.getElementById("addressInput")
	if(!isIdChecked){
		window.alert(ERROR_ID_VALUDATION)		
		idInput.focus()
		return false
	}else if((pwdInput.value === "" && pwdChecked.value === "") || !pwdValudation(pwdInput.value, pwdChecked.value)) {
		window.alert(ERROR_PASSWORD_VALUDATION)
		if(pwdInput.value === "") {
			pwdInput.focus();
		}else {
			pwdChecked.focus();
		}
		return false
	}else if(!emailValudation(emailInput.value) || emailInput.value === "") {
		window.alert(ERROR_EMAIL_VALUDATION)
		emailInput.focus();
		return false
	}else if(nameInput.value === "") {
		window.alert(ERROR_NAME_VALUDATION)
		nameInput.focus();
	}else if(addressInput.value ===""){
		window.alert(ERROR_ADDRESS_VALUDATION)
		addressInput.focus();
		return false 
	}else {
		return true
	}
}

/*패스워드,패스워드 확인 입력시 불림*/
const valudationCheckedListener = () => {
    console.log((pwdInput.value === "" && pwdChecked.value === ""))
    if((pwdInput.value === "" && pwdChecked.value === "") || !pwdValudation(pwdInput.value, pwdChecked.value)) {
        showPwdValudationText()
    } else {
        hidePwdValudationText()
    }
}

/**이메일 체크 */
const valudationCheckedEmailListener = () => {
    if(!emailValudation(emailInput.value) || emailInput.value === "") {
        showEmailValudationText()
    }else {
        hideEmailValudationText()
    }
}

/**비동기 아이디 중복 체크 */
const valudationCheckedIdListener = () => {
    let idValue = document.getElementById("inputId").value 
    let url = URL_FOLIO_ID_CHECKED +`?id=${idValue}` 
    fetch(url)
    	.then(response => response.text())
		.then(data =>{
				console.log(data)
			if("true" === data) {
				showIdInputValudationText()
			}else {
				hideIdInputValudationText()
			}
		})
		.catch(error => {console.error("에러",error)})
}

//함수 선언문
/*로그인 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.outerWidth/2 
    const centerY = window.outerHeight/2
    const loginContainer = document.getElementById("joinRoot")
    const loginCenterX = loginContainer.clientWidth/2
    const loginCenterY = loginContainer.clientHeight/2
    loginContainer.style.left = (centerX - loginCenterX) + 'px'
    loginContainer.style.top = (centerY - loginCenterY)+ 'px'
}

/***
 * @param pwdInput 사용자가 입력한 패스워드
 * @param pwdCheck 사용자가 입력한 패스워드 체크
 * @returns 형식이 맞으면 true 아니면 false  
 */
function pwdValudation(pwdInputString, pwdCheckString) {
   return (pwdInputString !== pwdCheckString)? false : true
}
/**아이디 경고문 띄우기 */
function showIdInputValudationText() {
	var idValudationTxt = document.getElementById("validationIdText") //아이디 채킹
	var idOkayImage = document.getElementById("idOkay") //아이디 성공 이미지
	idValudationTxt.style.color = 'red'
	idValudationTxt.innerText = ERROR_ID_DUPLICATE_VALUDATION
	elementHide(idOkayImage)
    elementShow(idValudationTxt)
    isIdChecked = false
}

/***아이디 경고문 숨기기 */
function hideIdInputValudationText() {
	var idValudationTxt = document.getElementById("validationIdText") //아이디 채킹
	var idOkayImage = document.getElementById("idOkay") //아이디 성공 이미지
	if(idInput.value === "") {
		idValudationTxt.style.color = 'red'
		idValudationTxt.innerText = ERROR_ID_EMPTY_VALUDATION
		elementShow(idValudationTxt)
    	elementHide(idOkayImage)
    	isIdChecked = false
	}else {
		elementShow(idOkayImage)
    	elementHide(idValudationTxt)
    	isIdChecked = true
    }
}

/**패스워드 체크 경고문을 보여준다. */
function showPwdValudationText() {
    var pwdOkayImage = document.getElementById("passwordOkay")
    var pwdValudationTxt = document.getElementById("validationPasswordText") //패스워드 벨류 데이션 체크 텍스트
    pwdValudationTxt.innerText = ERROR_PASSWORD_VALUDATION
    pwdValudationTxt.style.color = 'red'
    elementHide(pwdOkayImage)
    elementShow(pwdValudationTxt)
}
/**패스워드 체크 경고문을 숨긴다.. */
function hidePwdValudationText() {
    var pwdOkayImage = document.getElementById("passwordOkay")
    var pwdValudationTxt = document.getElementById("validationPasswordText") //패스워드 벨류 데이션 체크 텍스트
    elementShow(pwdOkayImage)
    elementHide(pwdValudationTxt)
}

/**패스워드 체크 경고문을 보여준다. */
function showEmailValudationText() {
	let emailOkayImage = document.getElementById("emailOkay") //비밀번호 확인창.
	let emailErrorText = document.getElementById("validationEmailText")
    emailErrorText.innerText = ERROR_EMAIL_VALUDATION
    emailErrorText.style.color = 'red'
    elementHide(emailOkayImage)
    elementShow(emailErrorText)
}
/**패스워드 체크 경고문을 숨긴다.. */
function hideEmailValudationText() {
	let emailErrorText = document.getElementById("validationEmailText")
	let emailOkayImage = document.getElementById("emailOkay") //비밀번호 확인창.
    elementShow(emailOkayImage)
    elementHide(emailErrorText)
}

/**정규식을 활용한 이메일 검증 */
function emailValudation(emailString) {
    var regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regExp.test(emailString)
}

/*****************************************************************************************요놈들은 나중에 공통으로 뺄것들 ******************************************************************/
/**
 * 특정 요소 보이게
 */
function elementShow(elemtnt) {
    elemtnt.hidden = false
}

function elementShowAll(...elementList) {
    element.array.forEach(element => {
        element.hidden = false
    });
}

/**
 * 특정요소 안보이도록
 */
function elementHide(element) {
    element.hidden = true
}

function elementHideAll(...elementList) {
    element.array.forEach(element => {
        element.hidden = true
    });
}

/* TODO LIST
 * 뒤로가기시에 레이아웃 깨지는 현상 수정. (임시 보류)
 * 미디어 쿼리 학습!
 */ 
