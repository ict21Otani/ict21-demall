/**
 *
 */
package jp.java.demall.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import jp.java.demall.common.CommonService;
import jp.java.demall.common.constant.ConstParam;
import jp.java.demall.dao.UserDAO;

/**
 * @author ta2ro
 *
 */
public class LoginService extends CommonService {

	/***
	 * リクエストから会員IDを引っ張ってくる。
	 * @param request
	 * @return user　会員情報 ないときはnull
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */

	User execute(HttpServletRequest request)
			throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

		UserDAO dao = new UserDAO();
		User user;

		//リクエスト（画面入力）のパスワード
		String pwd = request.getParameter("password");

		try {

			//入力されたパスワードの暗号化
			String enpPwd = encryptPasswordSHA256hex(pwd);

			//ユーザー情報取得
			user = dao.findById(request.getParameter("id"));
			//DBに情報があったとき
			if (user != null) {

				if (user.getPassword().equals(enpPwd))
					return user;
				//一致しなかった
				else
					return null;

			} else {//DBの情報なかったとき
				return null;
			}
		} catch (SQLException e) {
			throw e;
		}

	}

	/***
	 * cookie利用時に使う
	 * @param Cookie[] cookies
	 * @return Userログイン情報
	 * @throws SQLException
	 */
	public User excuteAutoLogin(HttpServletRequest req) throws SQLException {
		User user = null;
		String userId = null;
		String password = null;
		//cookie取得
		Cookie[] cookies = req.getCookies();
		//cookieのautoログイン情報(cookieがあるとき)

		if (null != cookies) {
			//クッキーからIDとPW取り出し
			for (Cookie ck : cookies) {
				if (ck.getName().equals(ConstParam.KEY_AUTO_LOGIN_ID)) {
					userId = ck.getValue();
				} else if (ck.getName().equals(ConstParam.KEY_AUTO_LOGIN_PWD)) {
					password = ck.getValue();
				}

			}


			UserDAO dao = new UserDAO();

			try {
				//idでユーザー情報取得
				user = dao.findById(userId);
				//取得できた時
				if (null != user) {
					//DBのパスワードとcookieのパスワード比較
					if (user.getPassword().equals(password)) {
						//パスワード一致した時そのままuser情報を返す
						return user;
					} else {
						return null;
					}

				}
			} catch (SQLException e) {
				throw e;
			}
		}

		return user;
	}

	/**
	 * 自動ログインが有効でリクエストが送られてきたらcookie情報を作成して返す
	 * ない場合は、nullを返す。
	 * @param request
	 * @param user
	 * @return Cookie情報
	 */

	public Cookie[] checkAutoLogin(HttpServletRequest request, User user) {

		String autoLogin = request.getParameter("autologin");//チェックないときはnull
		Cookie cookieAry[] = new Cookie[3];
		//オートログインにチェックあり
		if (null != autoLogin) {

			cookieAry[0] = new Cookie(ConstParam.KEY_AUTO_LOGIN, "1");
			cookieAry[1] = new Cookie(ConstParam.KEY_AUTO_LOGIN_ID, user.getUserId());
			cookieAry[2] = new Cookie(ConstParam.KEY_AUTO_LOGIN_PWD, user.getPassword());

			for (int i = 0; i < cookieAry.length; i++) {
				cookieAry[i].setMaxAge(60 * 60 * 24 * 30);//期限30日

			}
			return cookieAry;
		} else {

			return null;
		}

	}
}
