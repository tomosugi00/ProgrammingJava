package blackjack.model;

public enum GameStatus
{
	// ゲーム開始前
	READY,
	// スプリット可能状態
	PL_SPLIT,
	// ドロー可能状態
	PL_DRAW,
	// DLプレイ中
	DE_DRAW,
	//判定
	JUDGE
}
