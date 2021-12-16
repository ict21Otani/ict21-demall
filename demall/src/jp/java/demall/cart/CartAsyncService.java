/**
 *
 */
package jp.java.demall.cart;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsDAO;

/**
 * @author ta2ro
 * 買い物かごの非同期通信用サービスクラス
 */
public class CartAsyncService {
	/***
	 * Ajax(非同期)で在庫数だけ変更するための変数
	 * @param request
	 * @throws SQLException
	 */
	public void execute(HttpServletRequest request) throws SQLException {
		ItemsDAO dao = new ItemsDAO();
		int stock = Integer.parseInt(request.getParameter("stock")) ;
		int itemId = Integer.parseInt(request.getParameter("item_id")) ;
		try {
			//TODO カートのアも運との変更にする。
			dao.updateItemStock( itemId, stock);
		} catch (SQLException e) {
			throw e;
		}

	}

}
