
/*TODO HSCHOE */
const URL_LOCAL_HOST = "http://localhost:8080"
const URL_VALIDATION_CURRENT_PASSWORD_CHECKED = URL_LOCAL_HOST + "/Foilo/PasswordCheckedAction.do" //현재 패스워드가 맞는지 
const URL_PASSWORD_MODIFY = URL_LOCAL_HOST + "/Foilo/PasswordModifyAction.do" //패스워드 수정.
//벨류데이션 에러 메세지 정의 ]
const ERROR_PASSWORD_VALUDATION = "입력한 비밀번호가 올바르지 않습니다."
const ERROR_CURRENT_PASSWORD_MSG = "현재 비밀번호를 올바르게 입력해주세요"
const ERROR_NEW_PASSWORD_MSG = "새로운 비밀번호에 오류가 있습니다 확인후 다시 시도해주세요."
const ERROR_CNT_PASS_NEW_PASS_SAME_MSG ="새로운 비밀번호는 이젠 비밀번호랑 같을 수 없습니다."
const ERROR_PASSWORD_MODIFY_FAILD_MSG ="서버에서 비밀번호 수정이 실패되었습니다."
const SUCCESS_PASSOWRD_MODIFY_MSG = "패스워드를 정상적으로 수정하였습니다."
let isCurrentPasswordChecked = false; //현재 비밀번호 체킹 플레그
let isNewPasswordChecked = false; //현재 비밀번호 체킹 플레그

/**화면 중앙 정렬 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.innerWidth/2
    const centerY = window.innerHeight/2
    const rootContainer = document.getElementById("rootContainerStyle")
    const mainCenterX = rootContainer.clientWidth/2
    const mainCenterY = rootContainer.clientHeight/2
    rootContainer.style.left = (centerX - mainCenterX) + 'px'
    rootContainer.style.top = (centerY - mainCenterY)+ 'px'
    console.log(`updateCenterPosition ${ centerY }`)
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

const validationAll = ()=> {
	
	const newPasswordVal = document.getElementById("newPassword").value;
	const currentPasswordVal = document.getElementById("currentPasswordInput").value
	const id = new URLSearchParams(window.location.search).get("userId") //내부에서 계정 조회해서 패스워드를 검사하기 위한 용도.
	if(!isCurrentPasswordChecked) {
		alert(ERROR_CURRENT_PASSWORD_MSG)
	} else if(!isNewPasswordChecked) {
		alert(ERROR_NEW_PASSWORD_MSG)
	}else if(newPasswordVal === currentPasswordVal){
		alert(ERROR_CNT_PASS_NEW_PASS_SAME_MSG)
	} else {
		if(confirm("정말로 비밀번호 변경하시겠습니까?")) {
			fetch(URL_PASSWORD_MODIFY,{
				method : 'POST',
				headers: {
					"Content-Type" : "application/json"
				},
				body: JSON.stringify({
					"id" : id,
					"password" : newPasswordVal
				})
			})
			.then(response => response.json())
			.then(data => {
				console.log(data)
				if(data.isModifyOkay) {
					alert(SUCCESS_PASSOWRD_MODIFY_MSG)
					window.close()
				} else {
					alert(ERROR_PASSWORD_MODIFY_FAILD_MSG)
				}
			})
			.catch(error => console.log("에러로그",error))
		}else {
			//아무것도 안함 
		}
	}
}

/* 현 비밀번호 체크 */
const validationCurrentPassword = () => {
	const id = new URLSearchParams(window.location.search).get("userId") //내부에서 계정 조회해서 패스워드를 검사하기 위한 용도.
	const currentPassword = document.getElementById("currentPasswordInput").value
	fetch(URL_VALIDATION_CURRENT_PASSWORD_CHECKED,{
		method: `POST`,
		headers: {
			"Content-Type" : "application/json"
		},
		body: JSON.stringify({
			"id" : id,
			"password" : currentPassword
		})
	})
	.then(response => response.json())
	.then(data => {
		if(data.isPasswordChecked) {
			hideCntPwdValudationText()
			isCurrentPasswordChecked = true
		}else {
			showCntPwdValudationText()
			isCurrentPasswordChecked = false
		}
	})
	.catch(error => console.error("에러:", error))
}

/* 새로운 비밀번호 체크 */
const validationNewPassword = () => {
	const newPassword = document.getElementById("newPassword");
	const newPasswordChecked = document.getElementById("newPasswordChecked");
 if((newPassword.value === "" && newPasswordChecked.value === "") || !pwdValudation(newPassword.value, newPasswordChecked.value)) {
        showNewPwdValudationText()
        isNewPasswordChecked = false
    } else {
		isNewPasswordChecked = true
        hideNewPwdValudationText()
    }    
}

/***
 * @param pwdInput 사용자가 입력한 패스워드
 * @param pwdCheck 사용자가 입력한 패스워드 체크
 * @returns 형식이 맞으면 true 아니면 false  
 */
function pwdValudation(pwdInputString, pwdCheckString) {
   return (pwdInputString !== pwdCheckString)? false : true
}

//TODO HSCHOE 해당 코드 유틸로 뺴서 재활용 될수 있도록 수정 하자. 꼭 제발 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
/**패스워드 체크 경고문을 보여준다. */
function showCntPwdValudationText() {
    var pwdValudationTxt = document.getElementById("validationCurrentPasswordText") //패스워드 벨류 데이션 체크 텍스트
    pwdValudationTxt.innerText = ERROR_PASSWORD_VALUDATION
    pwdValudationTxt.style.color = 'red'
    elementShow(pwdValudationTxt)
}
/**패스워드 체크 경고문을 숨긴다.. */
function hideCntPwdValudationText() {
    var pwdValudationTxt = document.getElementById("validationCurrentPasswordText") //패스워드 벨류 데이션 체크 텍스트
    elementHide(pwdValudationTxt)
}

function showNewPwdValudationText() {
    var pwdValudationTxt = document.getElementById("validationNewPasswordText") //패스워드 벨류 데이션 체크 텍스트
    pwdValudationTxt.innerText = ERROR_PASSWORD_VALUDATION
    pwdValudationTxt.style.color = 'red'
    elementShow(pwdValudationTxt)
}
/**패스워드 체크 경고문을 숨긴다.. */
function hideNewPwdValudationText() {
    var pwdValudationTxt = document.getElementById("validationNewPasswordText") //패스워드 벨류 데이션 체크 텍스트
    elementHide(pwdValudationTxt)
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
