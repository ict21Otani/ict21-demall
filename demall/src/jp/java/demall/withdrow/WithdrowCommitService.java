/**
 *
 */
package jp.java.demall.withdrow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.java.demall.dao.ItemsInCartDAO;
import jp.java.demall.dao.UserDAO;
import jp.java.demall.login.User;
import jp.nib.ict21.ecsite.common.dao.CommonDAO;
import jp.nib.ict21.ecsite.purchase.dao.PurchaseDAO;
import jp.nib.ict21.ecsite.purchase.dao.PurchaseDetailDAO;
import jp.nib.ict21.ecsite.purchase.data.Purchases;

/**
 * @author ta2ro
 *
 */
public class WithdrowCommitService {


	/**
	 * 退会処理のサービスクラス
	 * @param user
	 * @throws SQLException
	 */
	void execute(User user) throws SQLException {

		String userId=user.getUserId();


		//DAOのインスタンス化
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		PurchaseDetailDAO detailsDAO = new PurchaseDetailDAO();
		ItemsInCartDAO itemsInCartDAO = new ItemsInCartDAO();
		UserDAO userDAO = new UserDAO();


		try(Connection con=new CommonDAO().getConnection()){
			con.setAutoCommit(false);
			try {
			//①ユーザーIDで対応する注文番号を取得
				List<Purchases> findPurchuselist = purchaseDAO.findByPurchuseUser(con,userId);

			//②①でとってきた注文番号に合致する注文詳細を削除(リストの回数分一つずつ削除)
				for(Purchases purchases:findPurchuselist) {
					detailsDAO.deleteByPurchasId(con, purchases.getPurchase_id());
				}

			//③注文をユーザーIDで削除
				purchaseDAO.deletePurchuse(con, userId);
			//④買い物かごをユーザーIDで削除
				itemsInCartDAO.deleteByUserId(con, userId);
			//⑤ユーザー情報を削除
				userDAO.deleteUser(con,userId);
				//全処理が完了したらコミット
				con.commit();
			}catch (SQLException e) {
				//エラーが出たらロールバック
				con.rollback();
				throw e;
			}
		} catch (SQLException e) {
			//コネクションエラー
			throw e;
		}
	}

}
