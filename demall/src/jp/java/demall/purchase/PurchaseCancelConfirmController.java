package jp.java.demall.purchase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.nib.ict21.ecsite.common.CommonServlet;
import jp.nib.ict21.ecsite.purchase.data.Purchases;

/**
 * Servlet implementation class PurchaseCancelConfirmController
 */
@WebServlet("/purchasecancelconfirm")
public class PurchaseCancelConfirmController extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Purchases> list;

		PurchaseCancelConfirmService service = new PurchaseCancelConfirmService();
		//ログインセッションがある
		if (isLoggedIn(request)) {
			try {
				list = service.execute(request);
				request.setAttribute("list", list);
				String path = "/WEB-INF/purchaseCancelConfirm.jsp";
				request.getRequestDispatcher(path).forward(request, response);

			} catch (SQLException e) {
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}
			//セッション切れ
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

		doGet(request, response);
	}

}
