package jp.java.demall.common.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.login.LoginService;
import jp.java.demall.login.User;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class IndexServlet
 * indexでの遷移 メイン(検索)1画面へ
 */
@WebServlet("/index")
public class IndexServlet extends CommonServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ログインしているとき(セッションがあるとき)
		if (isLoggedIn(request)) {
			//ログイン判定フラグをセット
			request.setAttribute("logined", "1");
		} else {//ログインしていない時
			//autoログインにて追加--start
			//cookieがあるときはautoログインする
			User user = new User();

			LoginService service = new LoginService();
			try {
				user = service.excuteAutoLogin(request);
				if (null != user) {
					//ログイン情報があったのでセッションセット
					setLoginUser(request, user);
					//ログイン判定フラグをセット
					request.setAttribute("logined", "1");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}
			//autoログインにて追加--end
		}

		//main画面に戻る
		String path = "WEB-INF/index.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
