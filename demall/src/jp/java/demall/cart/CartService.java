package jp.java.demall.cart;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsInCartDAO;
import jp.java.demall.login.User;

/***
 * 買い物かご関連のサービスクラス
 * @author ta2ro
 *
 */
public class CartService {
	/***
	 *かごに追加する
	 * @param request リクエスト（item情報）
	 * @param user ユーザー情報
	 * @throws SQLException
	 */
	public void excute(HttpServletRequest request, User user) throws SQLException {

		ItemsInCartDAO dao = new ItemsInCartDAO();

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String userId = user.getUserId();

		//存在チェック
		ItemsInCart cart = dao.findByUserIdAndItemId(userId, itemId);

		try {
			if (null == cart) {
				//新規登録時

				cart = new ItemsInCart();
				cart.setItemId(itemId);
				cart.setAmount(amount);
				cart.setUserId(userId);
				dao.InsertCart(cart);
			} else {
				//すでに登録済みの商品の場合

				//在庫数合算
				amount = amount + cart.getAmount();
				dao.updateAmount(cart, amount);
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	/***
	 *かごを表示するための情報を返す
	 * @param request リクエスト（item情報）
	 * @param user ユーザー情報
	 * @throws SQLException
	 */
	public List<ItemsInCart> execute(User user) throws SQLException {

		ItemsInCartDAO dao = new ItemsInCartDAO();

		String userId = user.getUserId();
		try {
			return dao.findByUserId(userId);

		} catch (SQLException e) {
			throw e;
		}
	}
	/***
	 * カート合計金額返す
	 * @param itemList
	 * @return　カート合計金額
	 */
		public int cartTotal(List<ItemsInCart> itemList) {

			int total = 0;

			for (ItemsInCart item : itemList) {

				int price = item.getItems().getPrice();
				int amount = item.getAmount();

				//単価と購入数から商品ごとの金額をだし、それを合計する。
				total += price * amount;

			}

			return total;

		}
}
