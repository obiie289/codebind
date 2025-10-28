# w06 버블게임 

## 설명 
아래 코드처럼 “버블을 못 맞추고 바닥(빈 캔버스)을 탭하면 남은 시간 2초 차감”이 이미 구현되어 있다. 
pointerInput 안에서 탭 좌표로 원형 히트테스트를 하고, 적중한 버블이 없으면 gameTime을 2 줄이고 0 이하로 내려가지 않게 보정한 뒤 0이면 게임오버로 전환한다.
변경·확인 포인트
	•	위치: BubbleGameScreen의 Canvas → pointerInput 블록
	•	로직:
	•	탭 좌표와 각 버블 중심의 거리 d 계산
	•	d <= radius 인 버블이 있으면 제거 + 점수 증가
	•	없으면 gameTime = max(gameTime - 2, 0); 0이면 isGameOver = true
  

```kt
.pointerInput(gameState.isGameOver) {
    if (!gameState.isGameOver) {
        detectTapGestures { offset ->
            val tappedBubble = gameState.bubbles.lastOrNull { bubble ->
                val distance = sqrt(
                    (offset.x - bubble.position.x).pow(2) +
                    (offset.y - bubble.position.y).pow(2)
                )
                distance <= bubble.radius
            }
            if (tappedBubble != null) {
                gameState.bubbles = gameState.bubbles - tappedBubble
                gameState.score++
            } else {
                // 바닥 탭 페널티: 남은 시간 2초 차감, 하한 0
                if (gameState.gameTime > 0) {
                    gameState.gameTime = (gameState.gameTime - 2).coerceAtLeast(0)
                    if (gameState.gameTime == 0) {
                        gameState.isGameOver = true
                    }
                }
            }
        }
    }
}
```

## 결과 


<img width="342" height="760" alt="스크린샷 2025-10-29 03 41 17" src="https://github.com/user-attachments/assets/9d8f51d0-97f7-48c0-91c7-ed880fa1f194" />



<img width="343" height="757" alt="스크린샷 2025-10-29 03 41 46" src="https://github.com/user-attachments/assets/a68f387d-2320-4866-898a-32d32a7bd2c1" />



	•	히트 판정: 마지막에 그려진 버블부터 검사하기 위해 `lastOrNull` 사용(시각상 최상단 버블 우선).
	•	페널티 보정: `coerceAtLeast(0)`로 음수 방지. 0이 되면 즉시 `isGameOver = true`로 루프 정지.
	•	성능: 거리 계산은 `sqrt((dx)^2 + (dy)^2)`로 간단하며, 버블 수가 10~15개 수준이면 매 탭 비용은 매우 낮다.

















  
