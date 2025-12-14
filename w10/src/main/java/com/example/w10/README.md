# [Android/Compose] HorizontalPager와 TabRow 연동 예제
* 프로젝트 설명
이 프로젝트는 Jetpack Compose를 사용하여 스와이프 가능한 HorizontalPager와 상단 TabRow를 서로 연동하는 방법을 보여주는 예제입니다.

일반적인 앱(카카오톡, 유튜브 등)에서 볼 수 있는 UI 패턴으로, 사용자가 탭을 클릭하면 해당 페이지로 이동하고, 화면을 스와이프하면 상단 탭이 자동으로 변경되는 양방향 동기화 기능을 구현했습니다.

## 주요 변경 사항 (TabRow 추가)
기존의 단순 페이저(목록만 스와이프되는 형태) 코드에서 다음과 같은 기능을 추가하여 업그레이드했습니다.

레이아웃 구조 변경: Column을 사용하여 화면 상단에 탭(TabRow)을 배치하고, 하단에 페이저(HorizontalPager)를 배치했습니다.

상태 동기화: pagerState 하나를 사용하여 탭의 선택 상태와 페이저의 현재 페이지를 연결했습니다.

클릭 이벤트 처리: 탭을 클릭했을 때 즉시 이동하는 것이 아니라 coroutineScope를 사용하여 부드럽게 페이지가 넘어가는 애니메이션을 적용했습니다.

### 주요 코드 

#### 1. 상태 선언 및 준비
```kt
val pagerState = rememberPagerState(pageCount = { 3 })

// 비동기 작업(페이지 스크롤 애니메이션)을 위한 스코프
val coroutineScope = rememberCoroutineScope()

// 탭에 표시할 제목 리스트
val tabTitles = listOf("전체", "인기", "추천")
```
#### 2.TabRow (상단 탭) 구현
```kt
TabRow(
    // 중요: 현재 페이저의 페이지 인덱스를 탭의 선택 상태와 동기화
    selectedTabIndex = pagerState.currentPage,
    // ... 색상 설정
) {
    tabTitles.forEachIndexed { index, title ->
        Tab(
            selected = pagerState.currentPage == index,
            onClick = {
                // 중요: 탭 클릭 시 해당 페이지로 부드럽게 스크롤 이동
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            },
            text = { Text(text = title) }
        )
    }
}
```
#### 3. HorizontalPager (하단 목록) 구현
```kt
HorizontalPager(
    state = pagerState,
    modifier = Modifier.weight(1f) // 남은 화면 공간을 전부 차지
) { page ->
    // 페이지별 데이터 로드 및 LazyColumn 표시
    val itemsForPage = pageItems[page]
    
    LazyColumn(...) {
        // ... 리스트 아이템 구현
    }
}
```
