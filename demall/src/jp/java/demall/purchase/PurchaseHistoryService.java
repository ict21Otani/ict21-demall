/**
 *
 */
package jp.java.demall.purchase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.java.demall.login.User;
import jp.nib.ict21.ecsite.purchase.dao.PurchaseDAO;
import jp.nib.ict21.ecsite.purchase.data.Purchases;

/**
 * @author ta2ro
 * 購入履歴サービスクラス
 */
public class PurchaseHistoryService {
	/***
	 *
	 * @param user
	 * @return 購入履歴のリスト
	 * @throws SQLException
	 */
	public List<Purchases> execute(User user) throws SQLException {

		PurchaseDAO dao = new PurchaseDAO();

		List<Purchases> list = new ArrayList<>();
		try {
			list = dao.findByPurchuseUser(user.getUserId());

		} catch (SQLException e) {
			throw e;
		}
		return list;

	}
}
