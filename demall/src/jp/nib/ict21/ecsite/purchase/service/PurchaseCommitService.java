package jp.nib.ict21.ecsite.purchase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.cart.CartService;
import jp.java.demall.cart.ItemsInCart;
import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.dao.ItemsInCartDAO;
import jp.java.demall.login.User;
import jp.java.demall.search.Items;
import jp.nib.ict21.ecsite.common.dao.CommonDAO;
import jp.nib.ict21.ecsite.purchase.dao.PurchaseDAO;
import jp.nib.ict21.ecsite.purchase.dao.PurchaseDetailDAO;
import jp.nib.ict21.ecsite.purchase.data.Purchases;
import jp.nib.ict21.ecsite.purchase.data.PurchasesDetails;


/***
 *
 * @author NiB
 * 購入確定機能のサービスクラス
 */
public class PurchaseCommitService extends CartService {

	public List<ItemsInCart> execute(HttpServletRequest req, User user) throws SQLException {

		ItemsInCartDAO cartDao = new ItemsInCartDAO();

		PurchaseDAO purchaseDao = new PurchaseDAO();
		PurchaseDetailDAO detailDao = new PurchaseDetailDAO();
		ItemsDAO itemDao=new ItemsDAO();

		String userId = user.getUserId();

		List<ItemsInCart> list = new ArrayList<ItemsInCart>();

		CommonDAO commonDao = new CommonDAO();

		/*
		 *
		 * 登録用購入情報
		 * */
		Purchases purchase = new Purchases();
		purchase.setPurchased_user(userId);

		if ("registered".equals(req.getParameter("destination"))) {
			purchase.setDestination(null);
		} else {
			purchase.setDestination(req.getParameter("address"));
		}

		//購入明細用データの作成
		PurchasesDetails detail = new PurchasesDetails();

		try (Connection con = commonDao.getConnection()) {

			con.setAutoCommit(false);
			try {
				//カート情報ゲット
				list = cartDao.findByUserId(con, userId);

				//カート削除
				cartDao.deleteByUserId(con, userId);

				//purchasesに登録1注文1件
				//purchaseIDの取得
				purchase.setPurchase_id(purchaseDao.createPurchaseId(con));
				//登録
				purchaseDao.insertPurchuse(con, purchase);

			//purchas detailに登録
				detail.setPurchase_id(purchase.getPurchase_id());
				detail.setPurchuses(purchase);

				//アイテムリスト数分登録
				for (ItemsInCart cart : list) {
					detail.setItem_id(cart.getItemId());
					detail.setAmount(cart.getAmount());
					//purchaseDtailの挿入
					detailDao.insertPurchuseDetails(con, detail);
					//itemテーブルの在庫を減らしに行く
					//itemsの在庫取得
					Items item = itemDao.findById(cart.getItemId());
					//在庫計算計算
					int stock=item.getStock()-cart.getAmount();
					//マイナスなら0にする。
					if(stock<0) {
						stock=0;
					}

					//update処理
					itemDao.updateItemStock(con, cart.getItemId(), stock);
				}


				con.commit();
			} catch (SQLException e) {
				con.rollback();
				e.printStackTrace();
				throw e;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		//表示用のカートの情報を返す
		return list;

	}


	public String getDestination(HttpServletRequest request) {

		if(request.getParameter("destination").equals("registered"))
		{
			return "ご自宅";
		}else {
			return request.getParameter("address");
		}

	}

}
