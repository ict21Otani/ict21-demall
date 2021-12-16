/**
 *
 */
package jp.java.demall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.java.demall.login.User;
import jp.nib.ict21.ecsite.common.dao.CommonDAO;

/**
 * @author ta2ro
 * userテーブルのDAO
 */
public class UserDAO extends CommonDAO {
	/***
	 *
	 * @param userId
	 * @return user情報　ないときはnull
	 * @throws SQLException
	 */
	public User findById(String userId) throws SQLException {

		String sql = "SELECT user_id, password, name, address " +
				"  FROM users where user_id=?";
		User user = null;
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, userId);

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				if (rs.next()) {
					user = new User();
					user.setUserId(rs.getString("user_id"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setAddress(rs.getString("address"));
				}
			}
		} catch (SQLException e) {
			throw e;
		}

		return user;

	}

	/***
	 * 会員　新規登録
	 * @param user 登録情報
	 * @throws SQLException
	 */
	public void insertUser(User user) throws SQLException {

		String sql = "INSERT INTO users( user_id, password, name, address) VALUES (?,?,?,? )";
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, user.getUserId());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getAddress());

				ps.executeUpdate();

			}
		} catch (SQLException e) {
			throw e;
		}

	}

	/***
	 * 会員　情報更新
	 * @param user 登録情報
	 * @throws SQLException
	 *
	 */
	public void updateUser(User user) throws SQLException {

		String sql = "UPDATE users SET  password=?, name=?, address=? WHERE user_id=?";
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {


				ps.setString(1, user.getPassword());
				ps.setString(2, user.getName());
				ps.setString(3, user.getAddress());
				ps.setString(4, user.getUserId());

				ps.executeUpdate();

			}
		} catch (SQLException e) {
			throw e;
		}

	}

	/***
	 * user_idでユーザー情報を消す
	 * @param user 登録情報
	 * @throws SQLException
	 */
	public void deleteUser(Connection con, String userId) throws SQLException {

		String sql ="DELETE FROM users WHERE user_id=?";


			try (PreparedStatement ps = con.prepareStatement(sql)) {


				ps.setString(1, userId);

				ps.executeUpdate();


		} catch (SQLException e) {
			throw e;
		}

	}

}
