/**
 *
 */
package jp.java.demall.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import jp.java.demall.dao.UserDAO;
import jp.java.demall.login.User;

/**
 * @author ta2ro
 * 会員情報の更新
 */
public class UpdateUserCommitService extends UpdateUserConfirmService {

	public User execute(HttpSession httpSession) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

		User user =(User) httpSession.getAttribute("upd");
		System.out.println(user.getPassword().getBytes("UTF-8").toString());
		user.setPassword(user.getPassword().getBytes("UTF-8").toString());

		//パスワード暗号化
		user.setPassword(encryptPasswordSHA256hex(user.getPassword()));
		//DB更新
		UserDAO dao = new UserDAO();
		try {
			dao.updateUser(user);
		} catch (SQLException e) {
			throw e;
		}
		return user;


	}
}
