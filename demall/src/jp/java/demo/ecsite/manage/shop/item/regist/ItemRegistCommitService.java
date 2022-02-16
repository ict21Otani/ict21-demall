package jp.java.demo.ecsite.manage.shop.item.regist;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.search.Items;

/**
 *
 * @author ta2ro
 *
 */
public class ItemRegistCommitService {

	/***
	 *
	 * @param request
	 * @return リクエストセッションから取り出した商品情報（登録したもの）
	 * @throws SQLException
	 */
	public Items execute(HttpServletRequest request) throws SQLException {

		Items item=(Items) request.getSession().getAttribute("registItem");
		ItemsDAO dao= new ItemsDAO();
		//データベースに登録
		try {
			dao.insertItems(item);
		} catch (SQLException e) {
			throw e;
		}
		return item;
	}

}
