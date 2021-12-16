package jp.java.demo.ecsite.manage.shop.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class RegistItemController
 */
@WebServlet("/shoplogin")
public class ShopLoginController extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(isLoggedIn(request)){//セッションあり
			//管理者ログイン遷移　
			String path = "/WEB-INF/itemRegister.jsp";

			request.getRequestDispatcher(path).forward(request, response);

		}else {//セッションなし
			//アイテム登録画面へ
			String path = "/WEB-INF/shopLogin.jsp";


			request.getRequestDispatcher(path).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ShopLoginService service = new ShopLoginService();
		try {
			ShopUsers user = service.execute(request);

			if (null != user) {
				//アイテム登録画面へ
				String path = "/WEB-INF/itemRegister.jsp";
				//セッションをセット
				setLoginUser(request, user);
				request.getRequestDispatcher(path).forward(request, response);
			} else {
				//情報がないとき、一致しない時はログインへ戻す

				String path = "/WEB-INF/shopLogin.jsp";
				request.getRequestDispatcher(path).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String path = "/WEB-INF/itemRegister.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}
}
