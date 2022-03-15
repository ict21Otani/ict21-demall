/**
 *
 */
package jp.java.demall.login;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import jp.java.demall.common.CommonService;
import jp.java.demall.common.constant.ConstParam;
import jp.java.demall.dao.AutoLoginTokenDAO;
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
		String token = null;
		//cookie取得
		Cookie[] cookies = req.getCookies();

		if (null != cookies) {
			//cookieがあっと期の処理
			//クッキーからIDとPW取り出し
			for (Cookie ck : cookies) {
				//TODO ここでキートークンだけとってくる。
				//TODO 削除予定　キートークン追加後　IDとPW削除
				if (ck.getName().equals(ConstParam.KEY_AUTO_LOGIN_TOKEN)) {
					token = ck.getValue();
				}

			}

			UserDAO dao = new UserDAO();
			AutoLoginTokenDAO tokenDao = new AutoLoginTokenDAO();
			try {
				//テーブルからキートークンでUserIDを取得する userId= 取得
				userId = tokenDao.findById(token);

				//idが取得できたときのみユーザー情報取得
				user = dao.findById(userId);
				//取得できた時　autoログインになっている。
				if (null != user) {
					return user;
				} else {
				//cookie期限切れか、チェックを外した。
					return null;
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
	 * @throws SQLException
	 */

	public Cookie[] checkAutoLogin(HttpServletRequest request, User user, String code) throws SQLException {

		String autoLogin = request.getParameter("autologin");//チェックないときはnull
		Cookie cookieAry[] = new Cookie[2];
		//オートログインにチェックあり
		if (null != autoLogin) {
			//TODO セット内容の変更　ここをトークンセットにする必要。12は消して３を１にする
			cookieAry[0] = new Cookie(ConstParam.KEY_AUTO_LOGIN, "1");
			//TODO 毎回変更するようにすること（前のやつは消す）
			cookieAry[1] = new Cookie(ConstParam.KEY_AUTO_LOGIN_TOKEN, code);
			try {
				//トークンとUserIDをDBに登録 //TODO 毎回変更するようにすること（前のやつは消す）インサート前にuserIdでデリートすればいい
				new AutoLoginTokenDAO().insertToken(user.getUserId(), code);
			} catch (SQLException e) {
				throw e;
			}
			for (int i = 0; i < cookieAry.length; i++) {
				cookieAry[i].setMaxAge(60 * 60 * 24 * 10);//期限10日
			}
			return cookieAry;
		} else {
			//TODO キートークンを削除する。(userID)
			return null;
		}

	}
	/***
	 * autoログイン用のハッシュのキートークンを発行して返す。
	 * @return　キートークン
	 */
	public static String getAutoLoginToken() {

		ByteBuffer buf = ByteBuffer.allocate(32);

		// 16byteのランダム文字列
		byte[] randomBytes = new byte[16];

		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(randomBytes);

		//ハッシュに変換
		byte[] hashBytes = DigestUtils.sha256(Hex.encodeHexString(randomBytes));
		buf.put(hashBytes);

		return Base64.encodeBase64URLSafeString(buf.array());

	}

}
