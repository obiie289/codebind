# Profile card 

 프로필 카드 화면을 구현했습니다. 가운데 정렬된 이름, 이미지, 위치, 전화번호를 한 화면에 배치합니다.

 ## 결과 화면 
<img width="421" height="804" alt="스크린샷 2025-10-29 02 27 57" src="https://github.com/user-attachments/assets/b3df9c02-e168-4b25-a5b2-3ece1406eb56" />


 ### 코드 개요
	•	Activity: `MainActivity`에서 `HomeScreen()` 컴포저블을 표시.
	•	UI 구성: `Column`으로 수직 정렬, 중앙 정렬 설정.
	•	컴포넌트:
	•	`Text("Kang")` – 큰 제목 스타일(headlineLarge).
	•	`Image(R.drawable.hyi)` – 300dp 크기, 16dp 패딩.
	•	`Text("위치: GONE")`, `Text("전화번호: 010-1234-5678")` – 중간 제목 스타일(headlineMedium).

```kt
  @Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Kang", style = MaterialTheme.typography.headlineLarge)
		
        Image(
            painter = painterResource(id = R.drawable.hyi),
            contentDescription = "Jetpack Compose 로고",
            modifier = Modifier.size(300.dp).padding(16.dp)
        )

        Text(text = "위치: GONE", style = MaterialTheme.typography.headlineMedium)
        Text(text = "전화번호: 010-1234-5678", style = MaterialTheme.typography.headlineMedium)
    }
}
```
