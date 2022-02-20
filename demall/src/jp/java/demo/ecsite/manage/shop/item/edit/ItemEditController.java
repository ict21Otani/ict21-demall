package jp.java.demo.ecsite.manage.shop.item.edit;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.search.Items;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class RegistItemController
 * アイテムの情報入力後登録で確認画面へ遷移
 */
@WebServlet("/itemedit")
public class ItemEditController extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * item編集画面への遷移
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ここでアイテムを検索して返す。
		ItemEditService service = new ItemEditService();
		Items item=null;
		String path = "/WEB-INF/itemEdit.jsp";

		try {
			item=service.execute(request);
			//検索結果リクエストにセット
			request.setAttribute("items", item);


		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.getRequestDispatcher(path).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);

	}

}
