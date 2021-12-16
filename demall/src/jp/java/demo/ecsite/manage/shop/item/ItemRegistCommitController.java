package jp.java.demo.ecsite.manage.shop.item;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.search.Items;
import jp.java.demall.search.SearchResultService;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class ItemRegistCommitController
 */
@WebServlet("/itemregistcommit")
public class ItemRegistCommitController extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//dogetは処理を行わない　エラー画面
		response.sendRedirect(ConstFilePath.ILLEGALE_ACCESS);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Items items = null;
		//セッション切れだと行わない ログイン実装まではこのまま
		//if(isLoggedIn(request)) {
		//サービス呼び出してインサート
		ItemRegistCommitService service = new ItemRegistCommitService();
		try {
			items = service.execute(request);
		} catch (SQLException e) {

			e.printStackTrace();
			response.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}
		//登録した情報でアイテムをとってくる。
		SearchResultService itemService = new SearchResultService();
		String category = itemService.getCategoryName(items.getCategoryId());
		request.setAttribute("categoryName", category);

		if (items.isRecommended()) {
			request.setAttribute("recomend", "オススメにする");
		} else {
			request.setAttribute("recomend", "オススメにしない");
		}

		//リクエストにセット
		request.setAttribute("items", items);

		//アイテム登録画面に遷移する。(実際は管理者ログイン アイテム登録ができたらログイン実装)
		String path = "/WEB-INF/itemRegisterCommit.jsp";
		request.getRequestDispatcher(path).forward(request, response);

		/*}else {

			//セッション切れ
		}*/
	}

}
