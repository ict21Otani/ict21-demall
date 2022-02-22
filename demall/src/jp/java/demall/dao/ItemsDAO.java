/**
 *
 */
package jp.java.demall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.java.demall.search.Items;
import jp.nib.ict21.ecsite.common.dao.CommonDAO;

/**
 * @author ta2ro
 * 商品テーブルのDAO
 *
 */
public class ItemsDAO extends CommonDAO {

	/***
	 *カテゴリとキーワードで検索 ページなし
	 *@retuen 検索結果
	 *@param キーワード
	 *@param  カテゴリ
	 */
	public List<Items> findByKeywordAndCategory(String keyword, int category) throws SQLException {

		String sql = "SELECT item_id, name, manufacturer, category_id, color, price, stock,recommended FROM items where CONCAT(name,color) like ? ";
		//カテゴリすべての時以外の追加分
		if (category != 0) {
			sql += " AND category_id=?";
		}

		List<Items> list = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, "%" + keyword + "%");
				if (category != 0) {

					ps.setInt(2, category);
				}

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				while (rs.next()) {
					Items item = new Items();

					item.setItemId(rs.getInt("item_id"));
					item.setName(rs.getString("name"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setColor(rs.getString("color"));
					item.setPrice(rs.getInt("price"));
					item.setRecommended(rs.getBoolean("recommended"));
					item.setStock(rs.getInt("stock"));
					list.add(item);
				}
			}
		} catch (SQLException e) {
			throw e;
		}

		return list;

	}

	/***
	 *全商品取得（商品変更用）
	 *カテゴリとキーワードで検索　全表示
	 *@retuen 検索ヒット件数
	 */
	public List<Items> findAll() throws SQLException {

		List<Items> list = new ArrayList<>();
		String sql = "SELECT  item_id, name, manufacturer, category_id, color, price, stock,recommended  FROM items order by item_id";

		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				while (rs.next()) {
					Items item = new Items();

					item.setItemId(rs.getInt("item_id"));
					item.setName(rs.getString("name"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setColor(rs.getString("color"));
					item.setPrice(rs.getInt("price"));
					item.setRecommended(rs.getBoolean("recommended"));
					item.setStock(rs.getInt("stock"));
					list.add(item);
				}
			}
		} catch (SQLException e) {
			throw e;
		}

		return list;

	}

	/***
	 *全商品数取得（ページ数をカウントするため）
	 *カテゴリとキーワードで検索　全表示
	 *@retuen 検索ヒット件数
	 *@param キーワード
	 *@param  カテゴリ
	 */
	public int countByKeywordAndCategory(String keyword, int category) throws SQLException {

		int cnt = 0;
		String sql = "SELECT count(item_id) FROM items where CONCAT(name,color) like ? ";
		//カテゴリすべての時以外の追加分
		if (category != 0) {
			sql += " AND category_id=?";
		}

		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				//like　追加
				ps.setString(1, "%" + keyword + "%");

				//カテゴリがすべてではない時
				if (category != 0) {
					//カテゴリ追加
					ps.setInt(2, category);
				}

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				if (rs.next()) {
					cnt = rs.getInt(1);

				}
			}
		} catch (SQLException e) {
			throw e;
		}

		return cnt;

	}

	/***
	 *商品詳細用　一件を返す。
	 *@retuen 検索結果にヒットした商品データ
	 *@param 商品ID
	 */
	public Items findById(int itemId) throws SQLException {

		String sql = "SELECT item_id, name, manufacturer, category_id, color, price, stock,recommended FROM items where item_id=? ";
		Items item = null;

		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, itemId);

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				if (rs.next()) {
					item = new Items();

					item.setItemId(rs.getInt("item_id"));
					item.setName(rs.getString("name"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setColor(rs.getString("color"));
					item.setPrice(rs.getInt("price"));
					item.setRecommended(rs.getBoolean("recommended"));
					item.setStock(rs.getInt("stock"));
				}
			}
		} catch (SQLException e) {
			throw e;
		}

		return item;

	}

	/***
	 * 購入後　在庫減少
	 * @param itemId
	 * @param 在庫数（変更する値）
	 * @throws SQLException
	 */
	public void updateItemStock(Connection con, int itemId, int stock) throws SQLException {

		String sql = "UPDATE items  SET stock=?  WHERE item_id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, stock);

			ps.setInt(2, itemId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;
		}

	}

	/***
	 * 在庫数だけ変更する
	 *@param 商品ID
	 *@param 在庫数
	 */
	public void updateItemStock(int itemId, int stock) throws SQLException {

		String sql = "UPDATE items SET stock=? WHERE item_id=?";

		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setInt(1, stock);
				ps.setInt(2, itemId);

				ps.executeUpdate();

			}
		} catch (SQLException e) {
			throw e;
		}

	}

	/***
	 *カテゴリとキーワードで検索　決まった件数で検索結果を返す
	 *@retuen 検索結果
	 *@param キーワード
	 *@param  カテゴリ
	 *@param  limit 表示件数
	 *@param  page  page番号()
	 *
	 */
	public List<Items> findByKeywordAndCategoryNuberOfResult(String keyword, int category, int limit, int page)
			throws SQLException {

		String sql = "SELECT item_id, name, manufacturer, category_id, color, price, stock,recommended FROM items where CONCAT(name,color) like ? ";
		//カテゴリすべての時以外の追加分
		if (category != 0) {
			sql += " AND category_id=? ";

		}
		sql += " ORDER BY item_id LIMIT ? OFFSET ? ";

		List<Items> list = new ArrayList<>();
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, "%" + keyword + "%");
				//カテゴリ指定あり
				if (category != 0) {

					ps.setInt(2, category);
					//何件表示するか
					ps.setInt(3, limit);
					//何番目から表示するか
					//オフセット計算
					/**
					 * オフセット0が一番初めからになるので
					 * 1ページ目の場合　1-1=0*表示数で0から表示を開始する。
					 * 2ページ目は2-1=1*10で10番目から開始
					 * というように計算で出している。
					 **/
					ps.setInt(4, (page - 1) * limit);//オフセット

				} else {//カテゴリ指定すべて
						//何件表示するか
					ps.setInt(2, limit);
					//何番目から表示するか
					//オフセット計算
					ps.setInt(3, (page - 1) * limit);
				}

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				while (rs.next()) {
					Items item = new Items();

					item.setItemId(rs.getInt("item_id"));
					item.setName(rs.getString("name"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setCategoryId(rs.getInt("category_id"));
					item.setColor(rs.getString("color"));
					item.setPrice(rs.getInt("price"));
					item.setRecommended(rs.getBoolean("recommended"));
					item.setStock(rs.getInt("stock"));
					list.add(item);
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
	public int createItemId(Connection con) throws SQLException {

		int id = -1;

		//シーケンスから次の番号をとってくる。
		String sql = "select nextval('seq_items_itemid')";

		try (PreparedStatement pst = con.prepareStatement(sql)) {
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				//getIntには列名の代わりに結果セットの出現順を指定
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}

		return id;
	}

	/**
	 *
	 * 商品登録
	 *
	 *
	 *
	 * */
	public void insertItems(Items items) throws SQLException {

		String sql = "INSERT INTO items( item_id, name, manufacturer, category_id, color, price, stock,  recommended) VALUES (?, ?,?,?,?, ?, ?,?)";
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setInt(1, createItemId(con));
				ps.setString(2, items.getName());
				ps.setString(3, items.getManufacturer());
				ps.setInt(4, items.getCategoryId());
				ps.setString(5, items.getColor());
				ps.setInt(6, items.getPrice());
				ps.setInt(7, items.getStock());
				ps.setBoolean(8, items.isRecommended());

				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				throw e;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 *
	 * 商品更新
	 *
	 *
	 *
	 * */
	public void updateItems(Items items) throws SQLException {

		String sql = "UPDATE items SET name=?, manufacturer=?, category_id=?, color=?, price=?, stock=?,  recommended=? where item_id=?";
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, items.getName());
				ps.setString(2, items.getManufacturer());
				ps.setInt(3, items.getCategoryId());
				ps.setString(4, items.getColor());
				ps.setInt(5, items.getPrice());
				ps.setInt(6, items.getStock());

				ps.setBoolean(7, items.isRecommended());

				ps.setInt(8, items.getItemId());
				System.out.println(ps);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				throw e;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
