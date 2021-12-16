package jp.java.demall.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import jp.java.demall.common.CommonService;
import jp.java.demall.dao.UserDAO;
import jp.java.demall.login.User;

/**
 * 会員登録クラスのサービス情報
 *
 */
public class UserRegisterCommitService extends CommonService {

	/**
	 * 入力情報をセットして返す。
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 *
	 * */
	public void excute(HttpSession httpSession)
			throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

		User user = (User) httpSession.getAttribute("regist");
		//パスワード暗号化

		user.setPassword(encryptPasswordSHA256hex(user.getPassword()));

		UserDAO dao = new UserDAO();
		try {
			dao.insertUser(user);
		} catch (SQLException e) {
			throw e;
		}

	}

	/***
	 * paswardの文字数に合わせて*表示を返す
	 */
	public String getPasswordStar(String pwd) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < pwd.length(); i++) {
			sb.append("*");
		}

		return sb.toString();
	}

}
