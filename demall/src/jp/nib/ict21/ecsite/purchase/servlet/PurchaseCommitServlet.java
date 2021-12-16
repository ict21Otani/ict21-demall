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
import jp.nib.ict21.ecsite.purchase.service.PurchaseCommitService;

/**
 * Servlet implementation class PurchaseCommitServlet
 */
@WebServlet("/purchasecommit")
public class PurchaseCommitServlet extends CommonServlet{
	private static final long serialVersionUID = 1L;

    /**
     * @see CommonServlet#CommonServlet()
     */
    public PurchaseCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		if(isLoggedIn(request	)) {
			PurchaseCommitService service = new PurchaseCommitService();

			List<ItemsInCart> list= new ArrayList<ItemsInCart>();
			int total=0;
			try {

				//DBカート一覧取得
				list=service.execute(request,getLoginUser(request));
				total=service.cartTotal(list);


				//表示情報のセット
				request.setAttribute("total", total);
				request.setAttribute("cart", list);

				//配送先の設定

				request.setAttribute("destination",service.getDestination(request) );



				String path  = "/WEB-INF/purchaseCommit.jsp";

				request.getRequestDispatcher(path).forward(request, response);


			} catch (SQLException e) {

				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}

		}else {

			//遷移がおかしいのエラーページへ
			//session切れ ログイン画面に戻る
			String path  = ConstFilePath.ILLEGALE_ERROR_HTML;
			request.getRequestDispatcher(path).forward(request, response);

		}


	}

}
