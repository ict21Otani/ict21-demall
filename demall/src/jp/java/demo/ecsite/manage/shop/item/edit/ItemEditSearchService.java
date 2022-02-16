package jp.java.demo.ecsite.manage.shop.item.edit;

import java.sql.SQLException;
import java.util.List;

import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.search.Items;

/**
 *
 * @author ta2ro
 *
 */
public class ItemEditSearchService {

	/***
	 *
	 * @param request
	 * @return リクエストセッションから取り出した商品情報（登録したもの）
	 * @throws SQLException
	 */
	public List<Items> execute() throws SQLException {

		List<Items> list=null;
		ItemsDAO dao= new ItemsDAO();
		//データベースに登録
		try {
			list=dao.findAll();

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}

}
