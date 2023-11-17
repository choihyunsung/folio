
/**화면 중앙 정렬 버튼 중앙 정렬*/         
function updateCenterPosition() {
    const centerX = window.innerWidth/2
    const centerY = window.innerHeight/2
    const rootContainer = document.getElementById("rootContainerStyle")
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
