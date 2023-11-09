
const contentDiv = document.getElementById("boardContainer")

/**글쓰기로 컨텐츠 전환 */
const onLoadBoard = () => {
	/* 글쓰기 리스트로 페이지 전환시에 글쓰기 버튼을 감추거나 다른걸로 변경 한다.
	글쓰기 폼을 innerHtml로 갈아치우고 처리한다.
	데이터는 json으로 BoardVo으로 동일하게 작업한다.
	 */
	contentDiv.innerHTML = `<p>게시글</p>`
}

/**게시글 리스트로 컨텐츠 전환 */
const onLoadBoardList = () => {
	/*
		위에 onLoadBard와 반대로 작업하면됨 
	 */
	contentDiv.innerHTML = `<p>게시글</p>`
}


/**화면 중앙 정렬 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.outerWidth/2
    const centerY = window.outerHeight/2
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
	setTimeout(() => {
    	updateCenterPosition()
	},100)
})

//DOM이 로드 완료될시에 
document.addEventListener('DOMContentLoaded', ()=> {
    console.log("DOMContentLoaded")
    updateCenterPosition()
})
