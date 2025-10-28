# w05 – Counter · Stopwatch · 11:11 이벤트

## 구성
	•	Counter: 값 증가/리셋 버튼.
	•	Stopwatch: 10ms 간격 갱신, Start/Stop/Reset.
	•	Color Toggle: 원형 버튼 탭 시 색상 토글.
## 11:11 이벤트
	•	목표 시각: 11:11:11 → 671,110ms.
	•	도달 시 AlertDialog 1회 표시, Reset 후 재도전 가능.
## 핵심 코드 포인트
	•	LaunchedEffect로 10ms 주기 타이머.
	•	eventTriggered 플래그로 중복 트리거 방지.
	•	formatTime으로 MM:SS:ss 표기.


  ## 결과 화면


   ### 초기 화면 

   


   ### 이밴트 


  

   

  
