/**
 *
 */
package jp.nib.ict21.ecsite.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.java.demall.dao.ItemsDAO;
import jp.nib.ict21.ecsite.common.dao.CommonDAO;
import jp.nib.ict21.ecsite.purchase.data.PurchasesDetails;

/**
 * @author NiB
 *
 */
public class PurchaseDetailDAO extends CommonDAO {
	//purchase_detail_idを採番
	public int createPurchaseDetailId(Connection con) throws SQLException {

		int detailId = -1;

		String sql = "select nextval('seq_pur_detail_id')";

		try (PreparedStatement pst = con.prepareStatement(sql)) {
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				//getIntには列名の代わりに結果セットの出現順を指定
				detailId = rs.getInt(1);
			}
		} catch(SQLException e) {
			throw e;

		}

		return detailId;
	}

	public void insertPurchuseDetails(Connection con, PurchasesDetails details) throws SQLException {

		String sql = "INSERT INTO purchase_details(purchase_detail_id, purchase_id, item_id, amount) VALUES (?, ?, ?, ?)";

		//purchase_detail_idを採番し、戻り値で返す
		int detailId = createPurchaseDetailId(con);

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, detailId);
			ps.setInt(2, details.getPurchase_id());
			ps.setInt(3, details.getItem_id());
			ps.setInt(4, details.getAmount());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}

	}
	/**
	 * 購入履歴詳細取得
	 */
	public List<PurchasesDetails> findByPurchasId(int purchasedId) throws SQLException {
		String sql = "SELECT purchase_detail_id, purchase_id, item_id, amount FROM purchase_details where purchase_id = ?";

		PurchasesDetails purchasesDettail = null;

		ItemsDAO dao = new ItemsDAO();

		List<PurchasesDetails> list= new ArrayList<>();
		try(Connection con = getConnection()){
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, purchasedId);

				ResultSet rs = ps.executeQuery();
				while(rs.next() == true) {
					purchasesDettail = new PurchasesDetails();
					purchasesDettail.setPurchase_detail_id(rs.getInt("purchase_detail_id"));
					purchasesDettail.setPurchase_id(rs.getInt("purchase_id"));
					purchasesDettail.setItem_id(rs.getInt("item_id"));
					purchasesDettail.setAmount(rs.getInt("amount"));


					purchasesDettail.setItems(dao.findById(purchasesDettail.getItem_id()));
					list.add(purchasesDettail);
				}


			} catch(SQLException e) {
				throw e;

			}
		} catch(SQLException e) {
			throw e;

		}

		return list;
	}

	/**
	 * purchusIdで削除する
	 *
	 */
	public void deleteByPurchasId(Connection con,int purchasedId) throws SQLException {
		String sql =" DELETE FROM purchase_details WHERE purchase_id = ?";

			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, purchasedId);

				 ps.executeUpdate();

			} catch(SQLException e) {
				throw e;

			}



	}


}
