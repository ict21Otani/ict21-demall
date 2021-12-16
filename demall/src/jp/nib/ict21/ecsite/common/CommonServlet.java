package jp.nib.ict21.ecsite.common;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import jp.java.demall.login.User;

/**
 * Servlet implementation class CommonServlet
 */
@WebServlet("/CommonServlet")
public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommonServlet() {
		super();

	}

	@Override
	public void init() {
		try {
			super.init();
		} catch (ServletException e1) {
			e1.printStackTrace();
		}

		//サーブレット生成の初期化時にデータベースコネクタ取得(JVMで一つあればいい)
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected User getLoginUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(CommonKeys.SESSION_LOGGED_IN_KEY);
	}

	//ログインに成功したらセッションのログイン情報をセット
	protected void setLoginUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(CommonKeys.SESSION_LOGGED_IN_KEY, user);
	}

	//現在ログイン状態にあるかどうかを調べる
	protected boolean isLoggedIn(HttpServletRequest request) {

		if (getLoginUser(request) != null) {
			return true;
		} else {
			return false;
		}
	}
}
