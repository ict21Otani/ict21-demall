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
 * Servlet implementation class PurchaseCancelCommitController
 */
@WebServlet("/purchasecancelcommit")
public class PurchaseCancelCommitController  extends CommonServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dogetは処理を行わない　エラー画面
		response.sendRedirect(ConstFilePath.ILLEGALE_ACCESS);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PurchaseCancelCommitService service=new PurchaseCancelCommitService();
		List<Purchases> list=null;
		if(isLoggedIn(request)) {
		try {
			//キャンセル用サービス呼び出し
			list = service.execute(request);
			request.setAttribute("list", list);
			String path = "/WEB-INF/purchaseCancelCommit.jsp";
			request.getRequestDispatcher(path).forward(request, response);

		} catch (SQLException e) {

			e.printStackTrace();
			response.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}
		}else {
			response.sendRedirect(ConstFilePath.ILLEGALE_ERROR_HTML);
			return;
		}
	}

}
