package com.example.w05


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w05.ui.theme.CandyTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CandyTheme {
                // TODO: 여기에 카운터와 스톱워치 UI를 만들도록 안내
                val count = remember {mutableStateOf(0)}
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CounterApp(count)
                    Spacer(modifier = Modifier.height(32.dp))
                    StopWatchApp()
                }
            }
        }
    }
}



@Composable
fun ColorToggleButtonApp() {
    // 배경색 상태를 저장하는 변수. 초기값은 Color.Red.
    // 'by' 키워드를 사용하여 MutableState<Color>의 value 속성에 직접 접근하도록 함.
    var currentColor by remember { mutableStateOf(Color.Red) }

    // 원형 버튼을 화면 중앙에 배치하기 위한 외부 Box
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 클릭 가능한 원형 버튼 역할을 하는 내부 Box
        Box(
            modifier = Modifier
                .clip(CircleShape) // 모양을 원형으로 자름
                .background(currentColor) // 현재 색상으로 배경 설정
                .clickable { // 클릭 이벤트 처리
                    // 현재 색상이 빨간색이면 파란색으로, 아니면 빨간색으로 변경
                    currentColor = if (currentColor == Color.Transparent) Color.Blue else Color.Transparent
                }
                .padding(30.dp), // 원 안쪽에 여백을 줘서 텍스트와 경계 사이에 공간을 둠
            contentAlignment = Alignment.Center // Box 안의 내용을 중앙에 정렬
        ) {
            Text(
                text = "Click Me",
                color = Color.White, // 텍스트 색상은 흰색으로 고정
                fontSize = 30.sp
            )
        }
    }
}



@Composable
fun CounterApp(count: MutableState<Int>) {
    // TODO: 상태 변수 정의
    // TODO: 버튼 클릭 시 상태 변경 로직 작성
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Count: ${count.value}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ) // TODO: 상태값 표시
        Row {
            Button(onClick = { count.value++ }) {
                Text("Increase")
            }
            Button(onClick = { count.value = 0 }) {
                Text("Reset")
            }
        }
    }
}


@Composable
fun StopWatchApp() {
    // 1. 상태(State)와 로직(LaunchedEffect)은 상위 컴포저블에 둔다.
    var timeInMillis by remember { mutableStateOf(12345L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isRunning) {
        if (isRunning) {
            while (true) {
                delay(10L)
                timeInMillis += 10L
            }
        }
    }

    // 2. 하위 컴포저블을 호출하며, 상태와 이벤트 핸들러(람다)를 전달합니다.
    StopwatchScreen(
        timeInMillis = timeInMillis,
        onStartClick = { isRunning = true },
        onStopClick = { isRunning = false },
        onResetClick = {
            isRunning = false
            timeInMillis = 0L
        }
    )
}

// 하위 컴포저블: UI 표시 및 이벤트 전달 (Dumb/Stateless Component)
@Composable
fun StopwatchScreen(
    timeInMillis: Long, // 3. 상태를 직접 소유하지 않고 파라미터로 받습니다.
    onStartClick: () -> Unit, // 4. 이벤트가 발생했을 때 호출할 람다 함수를 받습니다.
    onStopClick: () -> Unit,
    onResetClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatTime(timeInMillis), // 전달받은 상태로 UI를 그립니다.
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            // 5. 버튼 클릭 시, 상태를 직접 변경하는 대신 전달받은 람다 함수를 호출합니다.
            Button(onClick = onStartClick) { Text("Start") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onStopClick) { Text("Stop") }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onResetClick) { Text("Reset") }
        }
    }
}

// 시간을 MM:SS:ss 형식으로 변환하는 헬퍼 함수
private fun formatTime(timeInMillis: Long): String {
    val minutes = (timeInMillis / 1000) / 60
    val seconds = (timeInMillis / 1000) % 60
    val millis = (timeInMillis % 1000) / 10
    return String.format("%02d:%02d:%02d", minutes, seconds, millis)
}






@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    val count = remember {mutableStateOf(0)}
    CounterApp(count)
}

@Preview(showBackground = true)
@Composable
fun StopWatchPreview() {
    StopWatchApp()
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun ColorToggleButtonAppPreview() {
    ColorToggleButtonApp()
}







