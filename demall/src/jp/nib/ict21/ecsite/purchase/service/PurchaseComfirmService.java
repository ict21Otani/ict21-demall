package jp.nib.ict21.ecsite.purchase.service;

import java.sql.SQLException;
import java.util.List;

import jp.java.demall.cart.CartService;
import jp.java.demall.cart.ItemsInCart;
import jp.java.demall.login.User;

/***
 *
 * @author NiB
 * 購入確認画面へ遷移するときのサービス
 *
 */
public class PurchaseComfirmService extends CartService {

	@Override
	public List<ItemsInCart> execute(User user) throws SQLException {

		try {

			return super.execute(user);
		} catch (SQLException e) {
			throw e;
		}

	}
}
