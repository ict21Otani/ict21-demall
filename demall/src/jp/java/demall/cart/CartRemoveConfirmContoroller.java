package jp.java.demall.cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class CartRemoveComfirmContoroller
 */
@WebServlet("/cartremoveconfirm")
public class CartRemoveConfirmContoroller extends CommonServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartRemoveService service = new CartRemoveService();
		ItemsInCart cart = null;
		//セッション保持しているときのみ処理
		if (isLoggedIn(request)) {
			try {
				cart = service.excuteComfirm(request, getLoginUser(request));
				request.setAttribute("cart", cart);
				String path = "WEB-INF/removeFromCartConfirm.jsp";
				request.getRequestDispatcher(path).forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}
		//セッション切れ
		} else {
			//ログイン画面へ戻す
			String path = "WEB-INF/login.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
