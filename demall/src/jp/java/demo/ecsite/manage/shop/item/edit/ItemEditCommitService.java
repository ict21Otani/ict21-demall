/**
 *
 */
package jp.java.demo.ecsite.manage.shop.item.edit;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.search.Items;

/**
 * @author ta2ro
 *
 */
public class ItemEditCommitService {

	public Items excute(HttpServletRequest request) throws SQLException {

		Items items = new Items();

		Items item=(Items) request.getSession().getAttribute("editItem");
		ItemsDAO dao= new ItemsDAO();
		//データベースに登録
		try {
			dao.updateItems(item);//TODO SQLが正常化確認する
		} catch (SQLException e) {
			throw e;
		}
		return item;

	}
}
