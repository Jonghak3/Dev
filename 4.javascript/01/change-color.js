       //id가 heading인 요소를 가져옴
        var heading = document.getElementById("heading");
        // onclick = function (): 클릭했을 때 실행할 함수 정의
        //if 조건문 : 현재 색이 'red'이면 파란색으로 바꾸기
        heading.onclick = function () {
            if(heading.style.color ==="red"){
                heading.style.color = "blue"
            }
            // 그 외에는 빨간색으로 바꾸기
            else {
                heading.style.color = "red"
            }
        }