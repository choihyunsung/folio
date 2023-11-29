
/*TODO HSCHOE */
const URL_LOCAL_HOST = "http://localhost:8080"
const URL_FOLIO_MAIN_TO_LOGIN = URL_LOCAL_HOST + "/Foilo/MoveToLoginAction.do" //로그인으로 이동.
const URL_FOLIO_LOGOUT = URL_LOCAL_HOST + "/Foilo/LogoutAction.do" //로그아웃 액션 
const URL_FOLIO_DELETE_ACCOUNT = URL_LOCAL_HOST + "/Foilo/DeleteAccountAction.do" //회원 탈퇴
const URL_FOLIO_SETTING_MODIFY = URL_LOCAL_HOST + "/Foilo/SettingModifyAction.do" //회원 정보 수정.
const URL_FOLIO_INSERT_BOARD =  URL_LOCAL_HOST + "/Foilo/InsertBoardAction.do"//게시글 삽입
const URL_FOLIO_LOOK_UP_BOARD = URL_LOCAL_HOST + "/Foilo/LookUpBoardAction.do"//게시글 조회
const URL_FOLIO_GET_BOARD = URL_LOCAL_HOST + "/Foilo/GetBoardAction.do" //게시글 보기 
const URL_FOLIO_DELETE_BOARD = URL_LOCAL_HOST + "/Foilo/DeleteBoardAction.do" //게시글 삭제  
const URL_FOLIO_MODIFY_BOARD = URL_LOCAL_HOST + "/Foilo/ModifyBoardAction.do" //게시글 수정  
const URL_FOLIT_SEARCH_BOARD = URL_LOCAL_HOST + "/Foilo/SearchAction.do"
const URL_PAGE_POPUP_PASSWORD_MODIFY = "./PasswordModifyPopUp.html" //비밀번호팝업 페이지

const ERROR_VALIDATION_NAME_EMPTY_MSG = "이름에 빈값이 들어갈 수 없습니다."
const ERROR_VALIDATION_EMAIL_EMPTY_MSG = "email에 빈값이 들어갈 수 없습니다."
const ERROR_VALIDATION_EMAIL_FORMAT_NOT_MATCH_MSG = "이메일에 형식이 올바르지 않습니다."
const ERROR_VALIDATION_ADDRESS_EMPTY_MSG = "주소에 빈값이 들어갈 수 없습니다."

const memberInfoJson = JSON.parse(document.getElementById("memberInfoData").value) //아이디 정보
const contentDiv = document.getElementById("boardContainer")
const writingBtn = document.getElementById("writingButton") //글쓰기 버튼 
const writingSuccessBtn = document.getElementById("writingSuccessButton") //글작성완료 버튼 
const settingModifyBtn = document.getElementById("settingModifyButton") //세팅 수정 완료 버튼 
const returnBoardButton = document.getElementById("returnBoardButton") //게시글 보기에서 게시판으로 돌아갈때 사용
const boardModifyButton = document.getElementById("boardModifyButton") // 게시글 수정 버튼

//설정에서는 최초에 정상적인 값이 들어가 있으므로 초기값은 true 임 
let isSettingNameChecked = true //이름 벨류 데이션 체킹
let isSettingEmailChecked = true //이메일 벨류데이션 체킹 
let isSettingAddressChecked = true //주소 벨류데이션 체킹 
let posPageCount = 0 //패이지 카운트 default = 1
let lastSearchTxt = "" //마지막 검색입력
let lastSearchType = ""// 마지막 검색 타입.


/**글쓰기로 컨텐츠 전환 */
const onLoadInsertBoard = () => {
 	contentDiv.innerHTML = `<div class='main-content-container-style' id='writingRootContainer'>` +
	 							`<p class='nomalTitleStyle' id='settingTitle'>글쓰기 화면</p>` +
	 							`<p class='subTitleStyle'>제목</p>` +
	 							`<input class='nomalInputStyle' id='insert-board-title' type='text' style='width:99%' placeholder='제목 입력 하기'><br>` +
	 							`<p class='subTitleStyle'>내용</p>` +
	 							`<textarea class='nomalTextArea' id='insert-board-content' style='width:100%; height: 200px' placeholder="내용을 입력하세요"></textarea><br>` +
	 							`<div>` +
	 							`<button class='nomalButtonStyle' style='float:right' onclick='moveToPrevPage()'>이전화면</button>` +
	 							`</div>` +
	 						`</div>`
	elementShow(writingSuccessBtn)
	elementHide(writingBtn)
	elementHide(settingModifyBtn)
	elementHide(returnBoardButton)
	elementHide(boardModifyButton)
}

/** 이전페이지 이동시 처리될것. */
const moveToPrevPage = () =>{
	if(lastSearchTxt === ""){ //검색이 안되었거나 빈값 입력시 
		onLoadBoardList()
	}else {
		onSearch(false) //아니면 검색으로 ㄱ
	}
}

/**게시글 리스트로 컨텐츠 전환 */
const onLoadBoardList = () => {
	const queryString = `?pageCount=${posPageCount}`
	
	fetch(URL_FOLIO_LOOK_UP_BOARD + queryString)
	.then(response => response.json())
	.then(boardDTO => {
	
		parseBoardList(boardDTO)
		elementShow(writingBtn)
		elementHide(writingSuccessBtn)
		elementHide(settingModifyBtn)
		elementHide(returnBoardButton)
		elementHide(boardModifyButton)
	})
	.catch(error => console.error("에러", error))
}

/*게시글 리스트 파싱 작업 */
function parseBoardList(boardDTO) {
		console.log(boardDTO)
		const totalPage = boardDTO.totalPage //전체 페이지 
		const boardList = boardDTO.boardList //글 리스트 
	
		if(boardList.length > 0) {
			let htmlString =
			`<div class='main-content-container-style' id='listRootContainer'>`+ 
			`<p>게시글</p>`+
			`<pre id='show-search-info'>검색타입:'${ lastSearchType }',검색 내용:'${ lastSearchTxt }'로 검색한결과 </pre>`+
			`<table class='board-list-style' style="width:100%;">` +
				`<tr>`+
					`<th class='board-colunm-style' style="width:10%;">글번호</th>`+
					`<th class='board-colunm-style' style="width:50%;">제목</th>`+
					`<th class='board-colunm-style' style="width:20%;">작성자</th>`+
					`<th class='board-colunm-style' style="width:20%;">작성일</th>`+
				`</tr>`
			for(let i = 0; i < boardList.length; i++) {
				const date = new Date(boardList[i].boardVo.date)
				const dateString =`${ date.getFullYear() }-${ date.getMonth() }-${date.getDay()}`
				htmlString += 
				`<tr class='board-row-style'>`+
					`<td class='board-list-td'>${ boardList[i].boardVo.no }</td>`+ //글번호 	
					`<td class='board-list-td' style="cursor: pointer;" onclick="onLoadPrintBoard(${ boardList[i].boardVo.no })">${ boardList[i].boardVo.title }</td>`+	 //글 제목
					`<td class='board-list-td'>${ boardList[i].author }</td>`+	
					`<td class='board-list-td'>${ dateString }</td>`+	
				`</tr>`	
			}
			htmlString+=
			`</div>` + 
			`</table>`
			htmlString+=`<div id='board-page-container'>` + "<"
				for(let i = 0; i < totalPage; i++) {
					let PageCountView;
			
					if(i === posPageCount){
						PageCountView = `<a class='page-number-style' style="font-weight: bold" href="javascript:pageClick(${ i })">${ i + 1 }</a>`
					}else {
						PageCountView = `<a class='page-number-style' href="javascript:pageClick(${ i })">${ i + 1 }</a>`
					}
					htmlString += PageCountView
				}
			htmlString+=`></div>`
			htmlString+=`<div id='board-input-container'>` +
			`<select id='board-search-select'>` +
				`<option value="author" selected>작성자</option>`+
				`<option value="title">제목</option>`+
			`</select>` +
				`<input id='board-search-input'type=text placeHolder="검색어를 입력하세요">` +
				`<button id='baord-search-button' onclick="onSearch(${true})">검색</button>`+
			`</div>`
			contentDiv.innerHTML = htmlString
			const showSearchInfo = document.getElementById("show-search-info")
			if(lastSearchTxt === "") {
				elementHide(showSearchInfo)
			}
		} else {
			onNotBoardList() 
		}
}

/* 검색 버튼 누를 때 true 가들어오고 아니면 false가 들어옴 */
const onSearch = ( isFlag ) => {
	console.log(isFlag)
	posPageCount = (isFlag)? 0 : posPageCount
	console.log(`posPageCount : ${posPageCount}`)
	
	if(isFlag) { 
		const searchValue = document.getElementById("board-search-input").value
		const searchTypeList = document.getElementById('board-search-select')
		const searchOptionType = searchTypeList.options[searchTypeList.selectedIndex].value
		lastSearchTxt = searchValue  //검색어 저장 
		lastSearchType = searchOptionType //검색어 저장
	} 
	const queryString = `?searchText=${lastSearchTxt}&searchType=${lastSearchType}&pageCount=${posPageCount}`
	fetch(URL_FOLIT_SEARCH_BOARD + queryString)
	.then(response => response.json())
	.then(boardDTO => {
		parseBoardList(boardDTO)
	})
	.catch(error=> console.error("에러", error) )
}

const pageClick = (pageCount) => {
	posPageCount = pageCount
	if(lastSearchTxt === ""){ //검색이 안되었거나 빈값 입력시 
		onLoadBoardList()
	}else {
		onSearch(false) //아니면 검색으로 ㄱ
		
	}
}

/* 게시글이 아무것도 없을때 호출 */
const onNotBoardList = () => {
	if(posPageCount > 0) { //카운트가 클경우 삭제로 인해서 불러온 테이블이 전부 삭제된것이므로 페이지를 하나 줄이고 다시 로드한다.
		posPageCount--
		if(lastSearchTxt === ""){ //검색이 안되었거나 빈값 입력시 
			onLoadBoardList()
		}else {
			onSearch(false) //아니면 검색으로 ㄱ
		}
	}else {
		const isSearch = (lastSearchTxt != "")
		const errorString = (!isSearch)? "게시글이 단하나라도 존재하지 않습니다.:(" : `${ lastSearchTxt }로 검색한 결과가 없습니다. :<`
		
		contentDiv.innerHTML = `<div class='main-content-container-style' id='writingRootContainer'>` +
	 							`<p>${ errorString }</p>`+
	 							`</div>`
		lastSearchTxt = "" //검색어 초기화 
		elementShow(returnBoardButton)
		elementHide(writingSuccessBtn)
		elementHide(settingModifyBtn)
		elementHide(writingBtn)
		elementHide(boardModifyButton)
	}
}

/* 수정 화면 보이기 */
const onLoadModifyBoard = (boardInfo) => {
	const content = boardInfo.boardVo.content.replaceAll("<br>", "\r\n");
 	contentDiv.innerHTML = `<div class='main-content-container-style' id='writingRootContainer'>` +
	 							`<p class='nomalTitleStyle' id='settingTitle'>글수정 화면</p>` +
	 							`<p class='subTitleStyle'>제목</p>` +
	 							`<input class='nomalInputStyle' id='modify-board-title' type='text' style='width:99%' placeholder='제목 입력 하기' value='${ boardInfo.boardVo.title }'><br>` +
	 							`<p class='subTitleStyle'>내용</p>` +
	 							`<textarea class='nomalTextArea' id='modify-board-content' style='width:100%; height: 200px' placeholder="내용을 입력하세요">${ content }</textarea><br>` +
	 							`<div>` +
	 							`<button class='nomalButtonStyle' style='float:right' onclick='moveToPrevPage()'>이전화면</button>` +
	 							`</div>` +
	 						`</div>`
	elementShow(boardModifyButton)
	elementHide(writingBtn)
	elementHide(settingModifyBtn)
	elementHide(returnBoardButton)
	elementHide(writingSuccessBtn)
	
	const modifyClickListener = () => {
		const modifyTitleVal = document.getElementById("modify-board-title").value
		const modifyContentVal = document.getElementById("modify-board-content").value
		boardInfo.boardVo.title = modifyTitleVal
		boardInfo.boardVo.content = modifyContentVal
 		modifyBoard(boardInfo.boardVo) //서버랑 통신해서 데이터 수정하기 
 		boardModifyButton.removeEventListener("click", modifyClickListener)
	}
	boardModifyButton.addEventListener("click", modifyClickListener)
}

/** 게시글 보기 페이지로 이동 */
const onLoadPrintBoard = (boardNo) => {	
		const queryString = `?no=${boardNo}`
		fetch(URL_FOLIO_GET_BOARD + queryString)
		.then(response => response.json())
		.then(data => {
			const author = data.author
			const boardVo = data.boardVo 
			const date = new Date(boardVo.date)
			const isMyBoard = (boardVo.cno === memberInfoJson.cno) //나에 글일경우 
			const dateString =`${ date.getFullYear() }-${ date.getMonth() }-${date.getDay()}`
			contentDiv.innerHTML = 
				`<div class='main-content-container-style' id='readRootContainer'>` +
					`<p class='board-read-title-style'>글보기</p>` +
					`<div>`+
						`<ul id='board-read-member-info-container'>`+
							`<li class='li-left-sort-style'>` + `작성자 : ${ author }` + `</li>` +
							`<li class='li-left-sort-style'> `+`작성 일자 : ${ dateString }`+`</li>`+
						`</ul>`+
					`</div>`+
					`<table class='board-read-style'>` + 
						`<tr>` +
							`<td class='board-sub-style'>제목</td>` + 
						`</tr>` +
						`<tr>` +
							`<td><pre class='read-board-title-style'>${ boardVo.title }</pre></td>` + 
						`</tr>` +
						`<tr>` +
							`<td class='board-sub-style'>내용</td>` +
						`</tr>` +
						`<tr>` +
							`<td colspan='2' style="height:190px;">` +
								`<pre class='board-read-content-style'>${ boardVo.content }</pre>` +
							`</td>` +
						`</tr>` +
						`<tr>` +
							`<td colspan='2'>`+
								`<button class='nomalButtonStyle' id='board-modify-button' onclick='boardModify(${ JSON.stringify(data) })'>수정</button>` +
								`<button class='nomalButtonStyle' id='board-delete-button' onclick="boardDelete(${ boardVo.no })">삭제</button>` +
							`</td>`+
						`</tr>` +
					`</table>` + 
				`</div>`
				if(!isMyBoard) {
					const modBtn = document.getElementById("board-modify-button")
					const delBtn = document.getElementById("board-delete-button")
					elementHide(modBtn)
					elementHide(delBtn)
					
				}
				elementShow(returnBoardButton)
				elementHide(writingSuccessBtn)
				elementHide(settingModifyBtn)
				elementHide(writingBtn)
				elementHide(boardModifyButton)
			})
			.catch(error => console.error("에러", error))
}
/*세팅 화면*/
const onLoadSetting = (info) => {
	const memberInfoJSON = JSON.parse(info)
	const male =(memberInfoJSON.gender === "남")? 'checked' : '';
	const fmale =(memberInfoJSON.gender === "여")? 'checked' : '';	
	contentDiv.innerHTML = `<div id='settingRootContainer' class='main-content-container-style'>`+
	 							`<p class='nomalTitleStyle' id='settingTitle'>설정화면</p><br>`+
	 							`<p class='subTitleStyle'>이름</p>`+
	 							`<input class='settingTextInput' id='modifyName' type='text' value=${ memberInfoJSON['cstNm'] } onkeyup="settingNameCheckListener()">`+
	 							`<p class='subTitleStyle' id='validationNameText' style="color: red; margin-left: 5px;" hidden>이름입력 확인 텍스트</p>` +
 								`<p class='subTitleStyle'>성별</p>`+
 								"<label><input id='selectGender' name='gender' type='radio' value='남' " + male + ">남</label>"+
	                    		"<label><input id='selectGender' name='gender' type='radio' value='여' " + fmale + ">여</label><br>"+
								`<p class='subTitleStyle'>이메일</p>`+
								`<input class='settingTextInput' id='modifyEmail' type='email' value='${ memberInfoJSON['email'] }' onkeyup="settingEmailCheckListener()"><br>`+
								`<p class='subTitleStyle' id='validationEmailText' style="color: red; margin-left: 5px;" hidden>이메일 입력 확인 텍스트</p>` +
								`<p class='subTitleStyle'>주소</p>`+
								`<input class='settingTextInput' id='modifyAddress' type='text' value='${ memberInfoJSON['address'] }' onkeyup="settingAddrCheckListener()"><br>`+
								`<p class='subTitleStyle' id='validationAddressText' style="color: red; margin-left: 5px;" hidden>주소확인 텍스트</p>` +
								`<p class='subTitleStyle'>자기소개</p>`+
	 							`<textarea class='settingTextInput' id='modifyAboutMe'>${ memberInfoJSON['aboutMe'] }</textarea><br>` +
	 							`<button class='settingNomalButton' id='delAccountButton' onclick='deleteAccount(${ info })'>회원 탈퇴</button>`+
	 							`<button class='settingNomalButton' id='modifyPassWord' onclick='passWordModfiy("${ memberInfoJSON['id'] }")'>비밀번호 변경하기</button>` +
 								`<button class='nomalButtonStyle' style='float:right' onclick='moveToPrevPage()'>이전화면</button>` +
	 						"</div>"
	elementShow(settingModifyBtn)
	elementHide(writingBtn)
	elementHide(writingSuccessBtn)
	elementHide(returnBoardButton)
	elementHide(boardModifyButton)
}

/* 실제로 게시글 서버에 저장  */
const insertBoard = (cno) => {
	const title = document.getElementById("insert-board-title")
	const titleVal = title.value.trim()
	const content = document.getElementById("insert-board-content")
	const contentVal = content.value.trim()
	const queryString = `?cno=${cno}&title=${titleVal}&content=${contentVal.replace(/\n/g, "<br>")}`;
	
	if(titleVal === "" || titleVal === null) {
		alert("제목은 빈값이 들어갈수 없습니다.")
		title.focus()
	}else if(contentVal === "" || contentVal === null) {
		alert("내용은 빈값이 들어갈수 없습니다.")
		content.focus()
	}else {
		fetch(URL_FOLIO_INSERT_BOARD + queryString)
		.then(response => response.json())
		.then(data =>{
			if(data.isInsertBoard) {
				alert("성공적으로 게시되었습니다.")
				onLoadBoardList()// 게시글 작성후 로 이동 	
				lastSearchTxt=""
				lastSearchType=""
			} else {
				alert("서버에서 게시글 작성이 실패되었습니다.")
			}
		})
		.catch(error=>console.error("에러",error))
	}
}
//실제로 서버에서 해당 게시글 제거
const boardDelete = (boardNo) => {
	if(confirm('게시글을 삭제 하시겠습니까?')) {
		fetch(URL_FOLIO_DELETE_BOARD+`?no=${boardNo}`)
		.then(response => response.json())
		.then(data => {
			if(data.isDeleteBoard) {
				if(lastSearchTxt === ""){ //검색이 안되었거나 빈값 입력시 
					onLoadBoardList()
				}else {
					onSearch(false) //아니면 검색으로 ㄱ
				}
			}else {
				
			}
			
		})
		.catch(error => console.error("에러",error))		
	} else {
		//아무것도 안함 
	}
}


const boardModify = (boardInfo) => {
	console.log(boardInfo)
	if(confirm('게시글을 수정하시겠습니까?')) {
		onLoadModifyBoard(boardInfo)
	} else {
		//아무것도 안함 
	}
}

/**실질적으로 데이터를 수정하는 메소드  */
const modifyBoard = (boardInfo) => {
	console.log(boardInfo)
	fetch(URL_FOLIO_MODIFY_BOARD,{
		method : "POST",
		headers:{
			"Content-type":"applicaiton-json"
		},
		body: JSON.stringify(boardInfo)
	})
	.then(response => response.json())
	.then(data=> {
		
		if(data.isModifyBoard) {
			alert("성공적으로 게시글을 수정하였습니다.")
			if(lastSearchTxt === ""){ //검색이 안되었거나 빈값 입력시 
				onLoadBoardList()
			}else {
				onSearch(false) //아니면 검색으로 ㄱ
			}
		} else {
			alert("서버에서 수정을 실패하였습니다.")
		}
	})
	.catch(error => console.error("에러",error))
}

//설정에서 세팅중 네임에 대한 벨류 데이션 체크리스너
const settingNameCheckListener = () => {
	const nameInputVal = document.getElementById("modifyName").value.trim()
	const nameInputValicationText = document.getElementById("validationNameText")
	
	if(nameInputVal === "" || nameInputVal === null) {
		showInputTypeElement(nameInputValicationText,ERROR_VALIDATION_NAME_EMPTY_MSG)
		isSettingNameChecked = false
	}else {
		hideInputTypeElement(nameInputValicationText);
		isSettingNameChecked = true
	}
}



//설정에서 세팅중 네임에 대한 벨류 데이션 체크리스너
const settingEmailCheckListener = () => {
	const emailInputVal = document.getElementById("modifyEmail").value.trim()
	const emailnputValicationText = document.getElementById("validationEmailText")
	if(emailInputVal === "" || emailInputVal === null) {
		showInputTypeElement(emailnputValicationText,ERROR_VALIDATION_EMAIL_EMPTY_MSG)
		isSettingEmailChecked = false
	}else if(!emailValudation(emailInputVal)) {
		showInputTypeElement(emailnputValicationText,ERROR_VALIDATION_EMAIL_FORMAT_NOT_MATCH_MSG)
		isSettingEmailChecked = false
	}else {
		hideInputTypeElement(emailnputValicationText)
		isSettingEmailChecked = true
	}
}

//설정에서 세팅중 네임에 대한 벨류 데이션 체크리스너
const settingAddrCheckListener = () => {
	const addressInputVal = document.getElementById("modifyAddress").value.trim()
	const addressnputValicationText = document.getElementById("validationAddressText")
	if(addressInputVal === "" || addressInputVal === null) {
		showInputTypeElement(addressnputValicationText,ERROR_VALIDATION_ADDRESS_EMPTY_MSG)
		isSettingAddressChecked = false
	}else {
		hideInputTypeElement(addressnputValicationText)
		isSettingAddressChecked = true
	}
}

const settingModfiyOkay = (cno) => {
	const nameInput = document.getElementById("modifyName")
	const emailInput = document.getElementById("modifyEmail")
	const addressInput = document.getElementById("modifyAddress")
	const aboutMeInput = document.getElementById("modifyAboutMe")
	const gender = document.querySelector(`input[name="gender"]:checked`).value
	
	console.log(`name : ${nameInput.value} gender : ${gender}, email : ${emailInput.value}, addressInput : ${addressInput.value},aboutMe : ${aboutMeInput.value}`)
	if(!isSettingNameChecked) {
		alert("이름을 잘 입력해주세요.")
		nameInput.focus()
	}else if(!isSettingEmailChecked) {
		alert("이메일을 잘 입력해주세요.")
		emailInput.focus()
	}else if(!isSettingAddressChecked) {
		alert("주소를 잘 입력해주세요.")
		addressInput.focus()
	}else {
		if(confirm("수정을 완료 하시겠습니까?")) {
			fetch(URL_FOLIO_SETTING_MODIFY,{
				method : 'POST',
				headers :{
					"ContentType" : "application/json"
				},
				body : JSON.stringify({
					"cno" : cno,
					"cstNm" : nameInput.value,
					"gender" : gender,
					"email" : emailInput.value,
					"address" : addressInput.value,
					"aboutMe" : aboutMeInput.value
				})
			})
			.then(response => response.json())
			.then(data => {
				if(data.isModifyOkay) {
					alert("정보수정이 성공적으로 변경되었습니다.")
					pageRefresh() //수정된 정보로 페이지 리로드
					//TODO HSCHOE 페이지 로드후 스택으로 전에 화면으로pop 시키도록 구현하기. 
				}else {
					alert("서버오류로 정보 수정이 실패하였습니다.")
				}
			})
			.catch(error => console.error("에러 : ", error))
		} else {
			//아무것도 안함
		}
	}
}

const passWordModfiy = (id) => {
	if(confirm("비밀번호를 변경하시겠어요?")) {
		showPasswordModifyPopUp(id)
	} else {
		//아무것도 안함.
	}
}

const deleteAccount = (info) => {
	if(confirm("회원을 탈퇴하시겠습니까?")) {
		console.log(info)
		//회원탈퇴에 JSON 파라메터 보냄
		fetch(URL_FOLIO_DELETE_ACCOUNT, {
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

//로그인창 이동
const goLoginPage = () => {
	location.href = URL_FOLIO_MAIN_TO_LOGIN
}

const showPasswordModifyPopUp = (id) => {	
		// JS
		console.log("id : "+ id)
		var popupWidth = 250;
		var popupHeight = 300;
		var options = `top = ${ 10 }, left = ${ 10 }, width = ${ popupWidth }, height = ${ popupHeight }`
		//자바스크립트 ㅋㅋ 스페이스 %20으로 치환되서 get에다 스페이스 넣으면 큰일남
		window.open(URL_PAGE_POPUP_PASSWORD_MODIFY+`?userId=${id}`, "비밀번호 변경", options)
	
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
document.addEventListener('DOMContentLoaded', () => {
    console.log("DOMContentLoaded")
    updateCenterPosition()
    onLoadBoardList()
})

const onLogout = () => {
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


/**정규식을 활용한 이메일 검증 */
function emailValudation(emailString) {
    var regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return regExp.test(emailString)
}

/*****************************************************************************************요놈들은 나중에 공통으로 뺄것들 ******************************************************************/
/**
 * 특정 요소 보이게
 */
 
 
//텍스트 요소 보이면서 메세지 출력
const showInputTypeElement = (element, msg) => {
    element.innerText = msg
    element.style.color = 'red'
    elementShow(element)
} 

//텍스트 요소 숨기기
const hideInputTypeElement = (element) => {
    elementHide(element)
} 
//페이지 리플레쉬
const pageRefresh = () => {
	history.go(0);
}
 
function elementShow(elemtnt) {
    elemtnt.hidden = false
}

function elementShowAll(...elementList) {
    elementList.array.forEach(element => {
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
    elementList.array.forEach(element => {
        element.hidden = true
    });
}