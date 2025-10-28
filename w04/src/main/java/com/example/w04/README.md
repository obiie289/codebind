# w04 – Profile & Message Cards
Jetpack Compose로 프로필 카드(ProfileCard)와 메시지 카드(MessageCard)를 구현하고, 상단에 두 개의 버튼을 배치한 레이아웃 예제다.
## 핵심 구성
	•	Activity: `MainActivity`에서 `HomeScreen()` 렌더링.
	•	데이터 모델: `Profile(name, intro)`, `Message(name, msg)`.
	•	주요 컴포저블:
	•	`ProfileCard(Profile)`: 원형 프로필 이미지 + 이름/소개 텍스트.
	•	`MessageCard(Message)`: 카드 형태로 이름과 메시지 표시.
	•	`HomeScreen()`: 중앙에 카드, 화면 좌측 상단에 버튼 2개 배치.
## UI 포인트
	•	이미지 원형 처리: `Modifier.clip(CircleShape)`와 `size(70.dp)`.
	•	타이포그래피: `MaterialTheme.typography.titleMedium`, `bodyMedium` 등 사용.
	•	색상: 이름은 초록, 소개는 빨간색으로 시각적 구분.
	•	레이아웃:
	•	가운데 정렬: `Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center)`.
	•	버튼 열: `Column(horizontalAlignment = Alignment.CenterHorizontally)`로 상단 고정 느낌 구현.

  ## 코드 요소 
```
  @Composable
fun ProfileCard( Profile) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.ne),
            contentDescription = "연락처 프로필 사진",
            modifier = Modifier.size(70.dp).clip(CircleShape)
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(text = data.name, color = Color.Green, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text(text = data.intro, color = Color.Red, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun MessageCard(me: Message) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(me.name, color = Color.Green, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text(me.msg, color = Color.Green, style = MaterialTheme.typography.titleLarge)
        }
    }
}
```
## 미리보기
	•	다크 모드 프리뷰 제공: `PreviewProfileCard`, `PreviewMessageCard`에서 `uiMode = UI_MODE_NIGHT_YES`.









