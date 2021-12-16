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
@WebServlet("/cartremovecommit")
public class CartRemoveCommitContoroller extends CommonServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//getで遷移した場合エラー

		String path = ConstFilePath.ILLEGALE_ACCESS;

		request.getRequestDispatcher(path).forward(request, response);
		return;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CartRemoveService service = new CartRemoveService();
		ItemsInCart cart = null;
		//表示用
		try {
			//ログインしているとき
			if (isLoggedIn(request)) {
				cart = service.excuteCommit(request, getLoginUser(request));
				String path = "WEB-INF/removeFromCartCommit.jsp";
				request.setAttribute("cart", cart);
				request.getRequestDispatcher(path).forward(request, response);
			} else {
				//session切れ エラー画面へ
				String path = ConstFilePath.ILLEGALE_ERROR_HTML;

				request.getRequestDispatcher(path).forward(request, response);
				return;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			response.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}


	}

}
