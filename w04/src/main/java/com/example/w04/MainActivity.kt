package com.example.w04

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w04.ui.theme.CandyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CandyTheme {

               HomeScreen()

                }
            }
        }
    }



data class Message(val name: String, val msg: String)
data class Profile(val name: String, val intro: String)

@Composable
fun HomeScreen() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(), //전체 화면을 차지
            contentAlignment = Alignment.Center // 중앙 정렬
        )
        {
             //MessageCard(Message("야이 이거바라", "내놔요 좋은말할때 여자친구내놔"))
             ProfileCard(Profile("강해린", "너무 귀엽당, 너무 귀엽당,"))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { }) {
                Text(text = "버튼#1")
            }
            Button(onClick = { }) {
                Text(text = "버튼#2")
            }
        }

        }
    }



@Composable
fun ProfileCard(data: Profile) {
    Row(
        // Row 자체에 패딩을 주어 다른 Composable과 간격을 둡니다.
        modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            // painterResource를 사용해 drawable 리소스를 불러옵니다.
            painter = painterResource(R.drawable.ne),
            contentDescription = "연락처 프로필 사진",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp)) // 이미지와 텍스트 사이에 수평 간격을 추가합니다.
        Column {
            Text(
                text = data.name,
                // MaterialTheme의 색상표를 사용해 다크모드에 자동 대응합니다.
                color = Color.Green,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.intro,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun MessageCard(me: Message) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = me.name,
                color = Color.Green,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = me.msg,
                color = Color.Green,
            style = MaterialTheme.typography.titleLarge
            )
        }
    }
}


@Preview(
    name = "Profile Card Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewProfileCard() {
    CandyTheme {
        ProfileCard(Profile("강해린", "너무 귀엽당, 너무 귀엽당,"))
    }
}

@Preview(
    name = "Message Card Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewMessageCard() {
    CandyTheme {
        MessageCard(Message("야이 이거바라", "내놔요 좋은말할때 여자친구내놔"))
    }
}



