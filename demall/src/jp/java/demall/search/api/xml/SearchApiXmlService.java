package jp.java.demall.search.api.xml;

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
public class SearchApiXmlService {
/***
 *
 * XMLを生成して返す。（文字列のリスト）
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

		//XML用リスト
		List<String> xmlList=new ArrayList<String>();

		//データをXML形式に直す インデントはブラウザが勝手にやってくださるのでここで入れたりしない。
		//最初の一行
		xmlList.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

		//二行目以降
		xmlList.add("<Items>");

		//繰り返し
		for(Items item:itemList) {
		xmlList.add("<Item>");
		//ITEM名
		xmlList.add("<name>"+item.getName()+"</name>");
		//価格
				xmlList.add("<price>"+item.getPrice()+"</price>");
				//カテゴリ
				xmlList.add("<category>"+item.getCategoryId()+"</category>");

		//閉じたぐ
		xmlList.add("</Item>");
		//繰り返し
		}
		//閉じたぐ
		xmlList.add("</Items>");
		//xmlList.add("</xml>");//一番親の閉じたぐ XMLに閉じタグいらない


		return xmlList;
	}
}
