package jp.java.demall.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends CommonServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CartService service = new CartService();
		List<ItemsInCart> cart = null;
		//ログインしている場合
		if (isLoggedIn(request)) {
			try {
				//カート一覧取得
				cart = service.execute(getLoginUser(request));
				//合計金額取得
				int total = service.cartTotal(cart);
				request.setAttribute("size", cart.size());
				request.setAttribute("cart", cart);
				request.setAttribute("total", total);
			} catch (SQLException e) {

				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}

			String path = "/WEB-INF/cart.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			//ログインしていない場合
		} else {
			//ログイン画面へ
			String path = "WEB-INF/login.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CartService service = new CartService();
		String path = null;
		//セッション保持しているなら
		if (isLoggedIn(request)) {
			try {

				//かごに新規追加
				service.excute(request, getLoginUser(request));

			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}
		//セッション保持していない場合
		} else {
			//ログイン画面へ
			path = "WEB-INF/login.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
		//表示はdogetを利用する。
		doGet(request, response);
	}

}
