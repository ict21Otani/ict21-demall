/**
 *
 */
package jp.java.demall.purchase;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.nib.ict21.ecsite.purchase.dao.PurchaseDAO;
import jp.nib.ict21.ecsite.purchase.data.Purchases;

/**
 * @author ta2ro
 *
 */
public class PurchaseCancelCommitService extends PurchaseCancelConfirmService {

	/***
	 *キャンセル確定用のサービス
	 *@param リクエスト
	 */
	@Override
	List<Purchases> execute(HttpServletRequest request) throws SQLException {

		//表示用にデータを取っておく
		List<Purchases> list = super.execute(request);

		PurchaseDAO dao =new PurchaseDAO();
		//キャンセルのフラグをたてに行く
		dao.updateCancel(Integer.parseInt(request.getParameter("purchaseId")));

		return list;
	}
}