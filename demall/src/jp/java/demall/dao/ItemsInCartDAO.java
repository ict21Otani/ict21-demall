package jp.java.demall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.java.demall.cart.ItemsInCart;
import jp.java.demall.search.Items;
import jp.nib.ict21.ecsite.common.dao.CommonDAO;

public class ItemsInCartDAO extends CommonDAO {

	/**
	 * カート追加
	 * @throws SQLException
	 * @param 追加する商品の買い物かご用の情報
	 */
	public void InsertCart(ItemsInCart cart) throws SQLException {
		String sql = "INSERT INTO items_in_cart( user_id, item_id, amount, booked_date) VALUES (?,?,?,'now()')";
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, cart.getUserId());
				ps.setInt(2, cart.getItemId());
				ps.setInt(3, cart.getAmount());
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			throw e;
		}

	}

	/**
	 * userの買い物かごの情報をすべて取得
	 */
	public List<ItemsInCart> findByUserId(String userId) throws SQLException {
		String sql = "SELECT user_id, item_id, amount, booked_date FROM items_in_cart where user_id=?";
		ItemsDAO dao=new ItemsDAO();

		List<ItemsInCart> list = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, userId);

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				while (rs.next()) {
					ItemsInCart cart = new ItemsInCart();

					cart.setUserId(userId);
					cart.setItemId(rs.getInt("item_id"));
					cart.setBookedDate(rs.getTimestamp("booked_date").toLocalDateTime());
					cart.setAmount(rs.getInt("amount"));

					Items items =dao.findById(cart.getItemId());
					cart.setItems(items);
					list.add(cart);

				}

			} catch (SQLException e) {
				throw e;
			}

		}
		return list;
	}


	/**
	 * userの買い物かごの情報をすべて取得
	 */
	public List<ItemsInCart> findByUserId(Connection con ,String userId) throws SQLException {
		String sql = "SELECT user_id, item_id, amount, booked_date FROM items_in_cart where user_id=?";
		ItemsDAO dao=new ItemsDAO();

		List<ItemsInCart> list = new ArrayList<>();

			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, userId);

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				while (rs.next()) {
					ItemsInCart cart = new ItemsInCart();

					cart.setUserId(userId);
					cart.setItemId(rs.getInt("item_id"));
					cart.setBookedDate(rs.getTimestamp("booked_date").toLocalDateTime());
					cart.setAmount(rs.getInt("amount"));

					Items items =dao.findById(cart.getItemId());
					cart.setItems(items);
					list.add(cart);

				}

			} catch (SQLException e) {
				throw e;
			}
			return list;
		}



	/**
		登録済みかどうかを調べる
	 */
	public ItemsInCart findByUserIdAndItemId(String userId, int itemId) throws SQLException {

		String sql = "SELECT user_id, item_id, amount, booked_date FROM items_in_cart where user_id=? and item_id=?";

		ItemsInCart cart = null;
		//州品情報取得用
		ItemsDAO dao=new ItemsDAO();

		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, userId);
				ps.setInt(2, itemId);

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				if (rs.next()) {
					cart = new ItemsInCart();
					cart.setUserId(userId);
					cart.setItemId(rs.getInt("item_id"));
					cart.setBookedDate(rs.getTimestamp("booked_date").toLocalDateTime());
					cart.setAmount(rs.getInt("amount"));
					Items items =dao.findById(cart.getItemId());
					cart.setItems(items);
				}
			} catch (SQLException e) {
				throw e;
			}

		}
		return cart;
	}

	/**
	 * カート更新
	 * @throws SQLException
	 * @param 追加する商品の買い物かご用の情報
	 */
	public void updateAmount(ItemsInCart cart, int amount) throws SQLException {
		String sql = "UPDATE items_in_cart SET amount=?, booked_date=now() WHERE user_id=? AND item_id=?";

		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, amount);
				ps.setString(2, cart.getUserId());
				ps.setInt(3, cart.getItemId());

				ps.executeUpdate();

			} catch (SQLException e) {
				throw e;
			}

		}

	}
	//
	/**
	 * カート削除 useridとItemId
	 * @throws SQLException
	 * @param 削除するかごの中の商品情報
	 */
	public void deleteByUserIdAndItemId(ItemsInCart cart) throws SQLException {
		String sql = "delete from items_in_cart where user_id=? and item_id=?";

		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, cart.getUserId());
				ps.setInt(2, cart.getItemId());

				ps.executeUpdate();

			} catch (SQLException e) {
				throw e;
			}

		}

	}
	/**
	 * カート削除 userId
	 * @throws SQLException
	 * @param 削除するかごの中の商品情報
	 */
	public void deleteByUserId(Connection con ,String userId) throws SQLException {
		String sql = "delete from items_in_cart where user_id=?";


			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, userId);
				ps.executeUpdate();

			} catch (SQLException e) {
				throw e;
			}



	}
}
