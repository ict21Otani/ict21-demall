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

import jp.nib.ict21.ecsite.common.dao.CommonDAO;
import jp.nib.ict21.ecsite.purchase.data.Purchases;

/**
 * @author NiB
 *
 */
public class PurchaseDAO extends CommonDAO {

	/**
	 * そのユーザーに紐づく全購入データ
	 *@param userID
	 *@return 購入データ
	 *throws SQLException
	 **/
	public List<Purchases> findByPurchuseUser(String purchased_user) throws SQLException {
		String sql = "SELECT purchase_id, purchased_user, purchased_date, destination, cancel FROM "
				+ " Purchases where purchased_user = ? Order by purchase_id" ;

		Purchases purchases = null;
		PurchaseDetailDAO dao = new PurchaseDetailDAO();

		List<Purchases> list = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, purchased_user);

				ResultSet rs = ps.executeQuery();
				while (rs.next() == true) {
					purchases = new Purchases();
					purchases.setPurchase_id(rs.getInt("purchase_id"));
					purchases.setPurchased_user(rs.getString("purchased_user"));
					purchases.setPurchased_date(rs.getTimestamp("purchased_date").toLocalDateTime());

					//配送先　空文字でもnullでもない
					if (rs.getString("destination") != null && !rs.getString("destination").equals("")) {
						purchases.setDestination(rs.getString("destination"));
					} else {
						//nullから文字は自宅の扱い
						purchases.setDestination("自宅");
					}
					purchases.setCancel(rs.getBoolean("cancel"));

					purchases.setPurchasesDetailsList(dao.findByPurchasId(purchases.getPurchase_id()));
					list.add(purchases);
				}

			} catch (SQLException e) {
				throw e;

			}
		} catch (SQLException e) {
			throw e;

		}

		return list;
	}


	/**
	 * そのユーザーに紐づく全購入データ
	 *@param userID
	 *@return 購入データ
	 *throws SQLException
	 **/
	public List<Purchases> findByPurchuseUser(Connection con ,String purchased_user) throws SQLException {
		String sql = "SELECT purchase_id, purchased_user, purchased_date, destination, cancel FROM "
				+ " Purchases where purchased_user = ? Order by purchase_id" ;

		Purchases purchases = null;
		PurchaseDetailDAO dao = new PurchaseDetailDAO();

		List<Purchases> list = new ArrayList<>();

			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, purchased_user);

				ResultSet rs = ps.executeQuery();
				while (rs.next() == true) {
					purchases = new Purchases();
					purchases.setPurchase_id(rs.getInt("purchase_id"));
					purchases.setPurchased_user(rs.getString("purchased_user"));
					purchases.setPurchased_date(rs.getTimestamp("purchased_date").toLocalDateTime());

					//配送先　空文字でもnullでもない
					if (rs.getString("destination") != null && !rs.getString("destination").equals("")) {
						purchases.setDestination(rs.getString("destination"));
					} else {
						//nullから文字は自宅の扱い
						purchases.setDestination("自宅");
					}
					purchases.setCancel(rs.getBoolean("cancel"));

					purchases.setPurchasesDetailsList(dao.findByPurchasId(purchases.getPurchase_id()));
					list.add(purchases);
				}

			} catch (SQLException e) {
				throw e;

			}


		return list;
	}


	/**
	 * 注文に紐づく全購入データ
	 *@param userID
	 *@return 購入データ
	 *throws SQLException
	 **/
	public List<Purchases> findByPurchuseId(String purchased_id) throws SQLException {
		String sql = "SELECT purchase_id, purchased_user, purchased_date, destination, cancel FROM "
				+ " Purchases where purchase_id = ? Order by purchased_date";

		Purchases purchases = null;
		PurchaseDetailDAO dao = new PurchaseDetailDAO();

		List<Purchases> list = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, Integer.parseInt(purchased_id));

				ResultSet rs = ps.executeQuery();
				while (rs.next() == true) {
					purchases = new Purchases();
					purchases.setPurchase_id(rs.getInt("purchase_id"));
					purchases.setPurchased_user(rs.getString("purchased_user"));
					purchases.setPurchased_date(rs.getTimestamp("purchased_date").toLocalDateTime());
					//配送先　空文字でもnullでもない
					if (rs.getString("destination") != null && !rs.getString("destination").equals("")) {
						purchases.setDestination(rs.getString("destination"));
					} else {
						//nullから文字は自宅の扱い
						purchases.setDestination("自宅");
					}
					purchases.setCancel(rs.getBoolean("cancel"));

					purchases.setPurchasesDetailsList(dao.findByPurchasId(purchases.getPurchase_id()));
					list.add(purchases);
				}

			} catch (SQLException e) {
				throw e;

			}
		} catch (SQLException e) {
			throw e;

		}

		return list;
	}

	/***
	 * 	//purchase_idを採番
	 * @param con　コネクション
	 * @return
	 * @throws SQLException
	 */
	public int createPurchaseId(Connection con) throws SQLException {

		int purchaseId = -1;

		String sql = "select nextval('seq_purchase_id')";

		try (PreparedStatement pst = con.prepareStatement(sql)) {
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				//getIntには列名の代わりに結果セットの出現順を指定
				purchaseId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;

		}

		return purchaseId;
	}

	/***
	 *　購入データ挿入
	 * @param con
	 * @param purchases
	 * @throws SQLException
	 */
	public void insertPurchuse(Connection con, Purchases purchases) throws SQLException {

		String sql = "INSERT INTO purchases(purchase_id, purchased_user, purchased_date, destination, cancel)"
				+ " VALUES (?, ?, NOW(), ?, ?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, purchases.getPurchase_id());
			ps.setString(2, purchases.getPurchased_user());
			ps.setString(3, purchases.getDestination());
			ps.setBoolean(4, false);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;

		}

	}

	/***
	 *　商品キャンセル更新
	 * @param purchases
	 * @throws SQLException
	 */
	public void updateCancel(int purchasesId) throws SQLException {

		String sql = "UPDATE purchases SET cancel='t' WHERE purchase_id=?";
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, purchasesId);

				ps.executeUpdate();

			} catch (SQLException e) {
				throw e;

			}
		} catch (SQLException e) {
			throw e;

		}

	}

	/***
	 *　購入データ削除
	 * @param con
	 * @param purchases
	 * @throws SQLException
	 */
	public void deletePurchuse(Connection con, String userId) throws SQLException {

		String sql = "DELETE FROM purchases WHERE purchased_user=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, userId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;

		}

	}

}
