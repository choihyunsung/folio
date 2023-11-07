/**회원 가입 페이지로 이동 */
const goJoinPage = () => {
    location.href = `Join.html`
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
//windows가 갱신시
window.addEventListener('resize',() => {
    console.log("resize")
    setTimeout(() => {
    updateCenterPosition()
},100)
})

//DOM이 로드 완료될시에 
document.addEventListener('DOMContentLoaded',()=> {
    console.log("DOMContentLoaded")
    updateCenterPosition()
})
