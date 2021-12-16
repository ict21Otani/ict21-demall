package jp.java.demall.cart;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsInCartDAO;
import jp.java.demall.login.User;

/***
 * 買い物かご削除のサービスクラス
 * @author ta2ro
 *
 */
public class CartRemoveService {

	/**
	 * 確認画面用サービス
	 * @param request
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public ItemsInCart excuteComfirm(HttpServletRequest request,User user) throws SQLException{

	ItemsInCart cart;
	try {
		cart = new ItemsInCartDAO().findByUserIdAndItemId(user.getUserId(), Integer.parseInt(request.getParameter("itemId")));
	} catch (SQLException e) {
		throw e;
	}

		return cart;
	}
	/***
	 * 削除確定画面用サービス
	 * @param request
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public ItemsInCart excuteCommit(HttpServletRequest request,User user) throws SQLException{

		ItemsInCart cart;
		ItemsInCartDAO dao = new ItemsInCartDAO();
		try {
			//表示はcomfirmを利用
			cart = excuteComfirm(request,user);
			//削除しに行く
			dao.deleteByUserIdAndItemId(cart);
		} catch (SQLException e) {
			throw e;
		}

			return cart;
		}
}
