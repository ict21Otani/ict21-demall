/**
 *
 */
package jp.java.demall.purchase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.nib.ict21.ecsite.purchase.dao.PurchaseDAO;
import jp.nib.ict21.ecsite.purchase.data.Purchases;

/**
 * @author ta2ro
 *
 */
public class PurchaseCancelConfirmService {

	/***
	 *購入履歴キャンセル確認
	 * @throws SQLException
	 */
	List<Purchases> execute(HttpServletRequest request) throws SQLException {

		List<Purchases> list = new ArrayList<>();

		PurchaseDAO dao = new PurchaseDAO();
		try {
			list = dao.findByPurchuseId(request.getParameter("purchaseId"));

		} catch (SQLException e) {
			throw e;
		}
		return list;
	}
}
