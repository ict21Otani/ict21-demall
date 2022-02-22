package jp.java.demo.ecsite.manage.shop.item.edit;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.search.Items;

/**
 *商品編集画面のビジネスロジック
 * @author ta2ro
 *
 */
public class ItemEditService {

	/***
	 *　
	 * @param request
	 * @return IDに基づく商品を返す。
	 * @throws SQLException
	 */
	public Items execute(HttpServletRequest req) throws SQLException {

		Items items = new Items();
		ItemsDAO dao = new ItemsDAO();
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		items.setItemId(itemId);
		try {
			//アイテムを取得する
			items = dao.findById(items.getItemId());

		} catch (SQLException e) {
			throw e;
		}
		return items;
	}

}
