package jp.java.demo.ecsite.manage.shop.item.edit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.search.Items;
import jp.java.demall.search.SearchResultService;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class RegistItemController
 * アイテムの情報入力後登録で確認画面へ遷移
 */
@WebServlet("/itemreditconfirm")
public class ItemEditConfirmController extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO ゲットでは来ないのでエラー

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Items items = new ItemEditConfirmService().excute(request);
		request.setAttribute("items", items);

		SearchResultService service = new SearchResultService();
		String category=service.getCategoryName(items.getCategoryId());

		request.setAttribute("categoryName",category);

		if (items.isRecommended()) {
			request.setAttribute("recomend", "オススメにする");
		} else {
			request.setAttribute("recomend", "オススメにしない");
		}
		//セッションに登録情報セット
		request.getSession().setAttribute("editItem", items);

		//アイテム登録画面に遷移する。(実際は管理者ログイン アイテム登録ができたらログイン実装)
		String path = "/WEB-INF/itemEditConfirm.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

}
