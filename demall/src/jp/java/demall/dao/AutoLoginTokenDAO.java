/**
 *
 */
package jp.java.demall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.nib.ict21.ecsite.common.dao.CommonDAO;

/**
 * @author ta2ro
 * オートログイン用のトークンテーブルのDAO
 */
public class AutoLoginTokenDAO extends CommonDAO {
	/***
	 *
	 * @param userId
	 * @return user情報　ないときはnull
	 * @throws SQLException
	 */
	public String findById(String token) throws SQLException {

		String sql = "SELECT user_id FROM public.auto_login_token where key_token=?;";
		String userId=null;
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, token);

				ResultSet rs = ps.executeQuery();

				//検索結果あったとき
				if (rs.next()) {
					userId=	rs.getString("user_id");
				}
			}
		} catch (SQLException e) {
			throw e;
		}

		return userId;

	}

	/***
	 * autoログイン用のトークン　新規登録
	 * @param userId,token 登録情報
	 * @throws SQLException
	 */
	public void insertToken(String userId,String token) throws SQLException {

		String sql = "INSERT INTO public.auto_login_token(user_id, key_token) VALUES (?, ?);";
		try (Connection con = getConnection()) {
			try (PreparedStatement ps = con.prepareStatement(sql)) {

				ps.setString(1, userId);
				ps.setString(2, token);

				ps.executeUpdate();

			}
		} catch (SQLException e) {
			throw e;
		}

	}


	/***
	 * user_idでユーザー情報を消す //TODO tokenように治す必要あり
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
