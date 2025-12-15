#  Fake Store App - UI/UX 개선 및 카테고리 필터 구현

Fake Store REST API를 활용하여 상품 데이터를 불러오고, 사용자 친화적인 UI로 상품을 탐색할 수 있는 안드로이드 쇼핑몰 앱입니다.
기본적인 API 연동 이후, **그리드 레이아웃**과 **카테고리 필터링** 기능을 추가하여 앱의 완성도를 높였습니다.

---

##  주요 변경 사항 (API 연동 이후 추가 구현)

### 1. 2단 그리드 레이아웃 (Grid Layout)
- 기존의 수직 리스트(`LazyColumn`) 방식을 **2열 그리드(`LazyVerticalGrid`)**로 변경했습니다.
- 한 화면에 상품을 2개씩 배치하여 쇼핑몰 앱에 적합한 시각적 경험을 제공합니다.

### 2. 카테고리 필터링 (Category Filter)
- 상단에 **가로 스크롤 가능한 칩(`FilterChip`)**을 구현했습니다.
- `All`, `전자제품`, `쥬얼리` 등 카테고리 버튼 클릭 시, 해당 상품만 리스트에 필터링되어 표시됩니다.
- **로컬 필터링**: API를 매번 호출하지 않고, 최초 로딩된 데이터를 메모리에서 필터링하여 반응 속도를 최적화했습니다.
- **한글화 매핑**: API의 영어 데이터(`electronics`)를 UI에서는 한글(`전자제품`)로 매핑하여 표시했습니다.

### 3. 상품 카드 UI 리뉴얼
- 기존 가로형 배치에서 **세로형 카드 디자인**으로 변경했습니다.
- `Coil` 라이브러리를 활용해 이미지를 상단에 크게 배치하고, 텍스트 레이아웃이 깨지지 않도록 `maxLines`와 `TextOverflow`를 처리했습니다.
- 칩 버튼이 화면 밖으로 잘리지 않도록 `LazyRow`의 Padding을 최적화했습니다.

---

##  주요 코드 및 로직
<https://github.com/obiie289/codebind/blob/main/w11/src/main/java/com/example/w11/ui/ProductListScreen.kt>
### 1. `ProductListScreen.kt` (UI 구성)
- **`LazyVerticalGrid`**: `GridCells.Fixed(2)`를 사용하여 반응형 격자 구조 구현.
- **`LazyRow` & `FilterChip`**: 상단 카테고리 바 구현 및 선택 상태(`selected`) 시각화.
- **Category Mapping Logic**: `Pair("electronics", "전자제품")` 등을 활용해 서버 요청용 영어 이름과 화면 표시용 한글 이름을 매핑.
- 
```kt
// 영어(API 요청용)와 한글(화면 표시용)을 매핑
val categoryPairs = listOf(
    "All" to "전체",
    "electronics" to "전자제품",
    "jewelery" to "쥬얼리",
    "men's clothing" to "남성의류",
    "women's clothing" to "여성의류"
)

// 가로 스크롤 칩 리스트
LazyRow(...) {
    items(categoryPairs) { (englishName, koreanName) ->
        FilterChip(
            selected = selectedCategory == englishName, // 선택 상태 확인은 '영어'로
            onClick = {
                selectedCategory = englishName
                viewModel.updateCategory(englishName) // ViewModel 요청도 '영어'로
            },
            label = { Text(koreanName) } // 화면 표시는 '한글'로
        )
    }
}
```

### 2. `ProductViewModel.kt` (비즈니스 로직)
<https://github.com/obiie289/codebind/blob/main/w11/src/main/java/com/example/w11/ui/ProductListScreen.kt>
- **State Management**: `ProductUiState`(Loading, Success, Error)를 통해 데이터 로딩 상태 관리.
- **Data Backup & Filtering**:
  - `allProducts`: 전체 원본 데이터를 보존하는 변수 추가.
  - `updateCategory(category)`: 사용자가 선택한 카테고리에 맞춰 `allProducts.filter`를 수행하고 UI State를 갱신.

```kt
LazyVerticalGrid(
    columns = GridCells.Fixed(2), // 가로 2개 고정 배치
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp), // 전체 여백
    horizontalArrangement = Arrangement.spacedBy(16.dp), // 아이템 간 가로 간격
    verticalArrangement = Arrangement.spacedBy(16.dp)    // 아이템 간 세로 간격
) {
    items(state.products) { product ->
        ProductItem(product = product)
    }
}
```

### 3. `ProductItem.kt` (컴포넌트)
<https://github.com/obiie289/codebind/tree/main/w11/src/main/java/com/example/w11/viewmodel>
- **`Card` & `Column`**: 카드 내부 요소를 세로로 배치(이미지 위, 텍스트 아래)하여 모바일 환경에 맞는 디자인 적용.
- **`AsyncImage`**: URL 이미지 비동기 로딩 및 비율 조정(`ContentScale.Fit`).

```kt
private var allProducts: List<Product> = emptyList()

fun updateCategory(category: String) {
    if (category == "All") {
        // '전체' 선택 시 원본 데이터 복구
        _uiState.value = ProductUiState.Success(allProducts)
    } else {
        // 선택된 카테고리만 필터링하여 새로운 리스트 생성
        val filteredList = allProducts.filter {
            it.category == category.lowercase()
        }
        _uiState.value = ProductUiState.Success(filteredList)
    }
}
```

---

##  Tech Stack
- **Language**: Kotlin
- **UI Toolkit**: Jetpack Compose (Material3)
- **Network**: Retrofit2, GSON Converter
- **Image Loading**: Coil
- **Architecture**: MVVM (ViewModel, StateFlow, Coroutines)
