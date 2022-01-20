package jp.java.demall.search.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.search.Items;

/**
 * API用のサーブレットレスポンスはXML
 * @author ta2ro
 *
 */
public class SearchApiService {
	/***
	 *
	 * Json,XMLを生成して返す。（文字列のリスト）
	 *
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public List<String> execute(HttpServletRequest request) throws SQLException {

		//データ取得
		ItemsDAO dao = new ItemsDAO();
		List<Items> itemList = new ArrayList<Items>();
		try {

			itemList = dao.findByKeywordAndCategory(request.getParameter("keyword"),
					Integer.parseInt(request.getParameter("category")));
		} catch (SQLException e) {
			throw e;
		}
		String type = request.getParameter("resp-type");

		//戻り値用用リスト
		List<String> returnList = new ArrayList<String>();
		if (null != type && false == type.equals("")) {
			//リクエストがnullでなくて文字でない時
			//XML指定
			if (type.equals("xml")) {
				//XMLの処理

				List<String> xmlList = new ArrayList<String>();
				//データをXML形式に直す インデントはブラウザが勝手にやってくださるのでここで入れたりしない。
				//最初の一行
				xmlList.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				//件数表示

				//二行目以降
				xmlList.add("<Items>");
				xmlList.add("<ItemCount>" + itemList.size() + "</ItemCount>");
				if (itemList.size() > 0) {//一件以上帰ってきたとき
					//繰り返し
					for (Items item : itemList) {
						xmlList.add("<Item>");
						//ITEM名
						xmlList.add("<name>" + item.getName() + "</name>");
						//価格
						xmlList.add("<price>" + item.getPrice() + "</price>");
						//カテゴリ
						xmlList.add("<category>" + item.getCategoryId() + "</category>");

						//閉じたぐ
						xmlList.add("</Item>");
						//繰り返し
					}
				}
				//閉じたぐ
				xmlList.add("</Items>");
				//xmlList.add("</xml>");//一番親の閉じたぐ XMLに閉じタグいらない

				//戻り値セット
				returnList = xmlList;
			} else if (type.equals("json")) {
				List<String> jsonList = new ArrayList<String>();
				int cnt = 0;//回数カウント用
				//最初の行
				jsonList.add("{ \"Item\":[");
				if (itemList.size() > 0) {//一件以上帰ってきたとき
					//繰り返し部分
					for (Items item : itemList) {
						cnt++;
						//配列始まりタグ
						jsonList.add("{");
						//アイテム名
						jsonList.add("\"name\": \"" + item.getName() + "\",");
						//プライス
						jsonList.add("\"price\":" + item.getPrice() + ",");
						//カテゴリ
						jsonList.add("\"category\":" + item.getCategoryId());

						//最終行以外
						if (cnt < itemList.size()) {
							//,をいれる
							jsonList.add("},");

						} else {
							//最終行は,なし。
							jsonList.add("}");
						}
					}
				}
				//最後の行
				jsonList.add("]}");

				//戻り値セット
				returnList = jsonList;
			}

		} else {
			//nullを返して　コントローラーでエラー処理
			return null;

		}

		//Jsonの処理

		return returnList;
	}
}
