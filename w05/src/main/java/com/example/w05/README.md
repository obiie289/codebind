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

   <img width="425" height="938" alt="스크린샷 2025-10-29 03 15 45" src="https://github.com/user-attachments/assets/7d37b328-993d-4e25-8a89-bc8a0985cfc9" />



   ### 이밴트 

<img width="428" height="917" alt="스크린샷 2025-10-29 03 15 17" src="https://github.com/user-attachments/assets/9163fde9-396d-46ec-bb17-518160ecf242" />

  

   

  
