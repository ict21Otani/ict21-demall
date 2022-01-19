function onLoadCanvas() {
	'use strict';

	// くじを引くボタンを消す
	document.getElementById("kujibiki_btn").style.display = "none";

	/* 描画コンテキスト定義 */
	const canvas = document.querySelector('canvas');
	const ctx = canvas.getContext('2d');

	/* 描画区域の大きさ */
	let width = 680;
	let height = 340;

	//
	const dispText = "抽選中・・・";
	const atari = "おめでとう　当たり";
	const hazure = "残念、、、はずれ";
	const code = "50％OFFクーポンコード";
	const codeNum="1A1B2CXTAL";// TODO 自動生成してDBと照合できるようにする.

	let kujiF = true;

	let oy = 100;
	let ox = 150;
	let ty = oy;
	let tx = ox;
	let py = -0.3;
	/* テキスト表示関連 */
	// 変更インターバル
	const TEXT_CHANGE = 100;

	let animeCnt = 0;


	/*//100人に一人用の入れる
	const kujibikiAry= [0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,1,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0
		];
	*/
	//デモ用50％
	const kujibikiAry= [1,1,1,1,1,1,1,1,1,1,
		1,1,1,1,1,1,1,1,1,1,
		1,1,1,1,1,1,1,1,1,1,
		1,1,1,1,1,1,1,1,1,1,
		1,1,1,1,1,1,1,1,1,1,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0
		];
	//くじ引き処理

	//乱数取得
	let ram=Math.floor(Math.random() * 100);

	if(kujibikiAry[ram]==0){
		//はずれ
		kujiF=false;
	}else{
		//当たり
		kujiF=true;
	}

	/**
	 * アニメーションループ（演出描画処理）
	 */
	function main() {

		/* 描画部分 */

		// 全体クリア
		ctx.fillStyle = '#aaaa00';
		ctx.fillRect(0, 0, width, height);

		// テキストの表示
		// メッセージ描画
		// 文字のスタイルを指定
		ctx.font = '32px serif';
		ctx.fillStyle = '#454545';

		// 文字の配置を指定(真ん中)
		ctx.textBaseline = 'center';
		ctx.textAlign = 'center';

		if (animeCnt < 30) {
			// x=x+houkou*speed;//横
			// y=y+jouge*speed;//縦
			ctx.fillText(dispText, tx, ty);

			if (ty < oy - 16 || ty > oy + 16) {
				py = py * -1;
			}
			ty = ty + py;
		} else {
			ty = oy-50;
			ctx.font = '16px serif';
			ctx.fillStyle = '#111111';
			if (kujiF==true) {
				// あたり
				ctx.fillText(atari, tx, ty);
				ctx.fillText(code, tx, ty +20);
				ctx.fillText(codeNum, tx, ty +20+18);
			} else {
				// はずれ
				ctx.fillText(hazure, tx, ty);
			}
		}

		// アニメーション操作用のカウンタ
		animeCnt = animeCnt + 0.23;

		// mainループ再呼び出し
		requestAnimationFrame(main);

	}
	// mainループ（ゲームループ）最初の呼び出し
	requestAnimationFrame(main);

}