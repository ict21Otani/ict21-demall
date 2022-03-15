/**
 *
 */
package jp.java.demall.login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * @author ta2ro
 * ログイン管理のコントローラークラス
 *
 */
@WebServlet("/login")
public class LoginController extends CommonServlet {

	/***
	 * URLで直接遷移など来た時の処理
	 * ログイン画面へ
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = null;

		//autoログイン処理
		LoginService service = new LoginService();

		try {
			//cookieを使ってデータ取得してログイン(getの時のみの処理)
			user = service.excuteAutoLogin(req);

			//ユーザーがあったとき
			if (null != user) {
				//ログインあつかいのフラグ
				req.setAttribute("logined", "1");
				//セッションにセット
				setLoginUser(req, user);
				//検索画面へ遷移
				String path = "/WEB-INF/index.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				return;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(ConstFilePath.ERROR_HTML);
			return ;
		}

		//cookieがないとき,ユーザー情報なし、不一致は通常のログイン
		String path = "/WEB-INF/login.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}

	/***
	 * ログインボタンでボタン遷移
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		LoginService service = new LoginService();

		//ユーザーデータを取得　ないときはnull
		User user = null;



		try {
			//ユーザー情報取得
			user = service.execute(req);
			String path;

			//user見つかってパスワードも一致,もしくはautoログイントークンがあった時
			if (null != user) {
				path = "/WEB-INF/index.jsp";
				req.setAttribute("logined", "1");

				//キートークン新規生成
				String code=LoginService.getAutoLoginToken();

				//autoログインを判定してある場合はcookieをセット
				Cookie[] cookie = service.checkAutoLogin(req, user,code);
				//copkie情報が帰ってきたときのみ、cookieを作成
				if (null != cookie) {
					for (Cookie ck : cookie) {
						//cookieセットする
						resp.addCookie(ck);
					}
				}else {
					//cookieがなかった。
					//TODO cookieのトークン情報を削除する処理(autoログイン解除１)
					//TODO DBのトークン情報を削除する処理(autoログイン解除２)

				}
				setLoginUser(req, user);

				//見つからないかパスワード不一致
			} else {
				req.setAttribute("loginfailed", "1");
				path = "/WEB-INF/login.jsp";
				setLoginUser(req, user);
			}

			//画面遷移
			req.getRequestDispatcher(path).forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(ConstFilePath.ERROR_HTML);
		} catch (NoSuchAlgorithmException e) {
			// 暗号化ができなかった（システムエラー）
			e.printStackTrace();
			resp.sendRedirect(ConstFilePath.ERROR_HTML);
		}

	}
}
