package jp.nib.ict21.ecsite.purchase.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.cart.ItemsInCart;
import jp.java.demall.common.constant.ConstFilePath;
import jp.nib.ict21.ecsite.common.CommonServlet;
import jp.nib.ict21.ecsite.purchase.service.PurchaseComfirmService;

/**
 * Servlet implementation class PurchuseConfirmServlet
 */
@WebServlet("/purchaseconfirm")
public class PurchaseComfirmServlet extends CommonServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ログインしている場合
		if(isLoggedIn(request)) {
			PurchaseComfirmService service = new PurchaseComfirmService();

			List<ItemsInCart> list= new ArrayList<ItemsInCart>();
			int total=0;
			try {
				list=service.execute(getLoginUser(request));
				total=service.cartTotal(list);

				request.setAttribute("total", total);
				request.setAttribute("cart", list);

				String path  = "/WEB-INF/purchaseConfirm.jsp";

				request.getRequestDispatcher(path).forward(request, response);


			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}

		}else {
			//session切れ ログイン画面に戻る
			String path  ="/WEB-INF/login.jsp";

			request.getRequestDispatcher(path).forward(request, response);
		}


	}

}
