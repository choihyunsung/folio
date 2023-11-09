
/**화면 중앙 정렬 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.outerWidth/2
    const centerY = window.outerHeight/2
    const rootContainer = document.getElementById("rootContainer")
    const loginCenterX = rootContainer.clientWidth/2
    const loginCenterY = rootContainer.clientHeight/2
    rootContainer.style.left = (centerX - loginCenterX) + 'px'
    rootContainer.style.top = (centerY - loginCenterY)+ 'px'
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
