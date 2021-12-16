/**
 *
 */
package jp.java.demall.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * @author ta2ro
 * GET　 ページネーション処理
 * ほかの画面から検索画面に戻る→ページネーションの実装のためこの処理はIndexServletへ移行
 *
 * POST 検索ボタン押したときのコントローラー
 *
 */
@WebServlet("/search")
public class ItemSearchController extends CommonServlet {

	/**
	 * ページネーション処理
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		SearchResultService service = new SearchResultService();
		List<Items> list=null;
		String path = null;
		try {

			//検索ワードとカテゴリーをリクエストにセット
			//キーワードセット（キーワード指定なし）
			if(req.getParameter("keyword") ==null || "".equals(req.getParameter("keyword"))) {
				req.setAttribute("keyword", "");

			}else{
				//指定あり
				req.setAttribute("keyword", req.getParameter("keyword"));
			}


			//リクエスト用カテゴリ（数字）セット
			req.setAttribute("category", req.getParameter("category"));
			//カテゴリを文字に置き換えたものをセット
			req.setAttribute("categoryname", service.getCategoryName(req));



			//ページネーション情報をセット
			//ページ番号をセット

			req.setAttribute("page",req.getParameter("page"));

			//ページ数
			req.setAttribute("pagecount", service.getPageCount(req));

			//検索結果を取得
			list = service.execute(req);
			//検索結果リクエストにセット
			req.setAttribute("items", list);


		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}

		//main画面に戻る
		path="/WEB-INF/searchResult.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}

	/***
	 * 検索ボタン押下時
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		SearchResultService service = new SearchResultService();
		List<Items> list=null;
		String path = null;

		try {
			//検索サービス呼び出し　POSTは1ページ目から表示するので指定。
			list = service.execute(req,1);


			//検索結果リクエストにセット
			req.setAttribute("items", list);

			//検索ワードとカテゴリーをリクエストにセット
			//キーワードセット（キーワード指定なし）
			if(req.getParameter("keyword") ==null || "".equals(req.getParameter("keyword"))) {
				req.setAttribute("keyword", "");//空文字
			}else{
				//指定あり
				req.setAttribute("keyword", req.getParameter("keyword"));
			}
			//リクエスト用カテゴリ（数字）セット
			req.setAttribute("category", req.getParameter("category"));
			//カテゴリを文字に置き換えたものをセット
			req.setAttribute("categoryname", service.getCategoryName(req));

			//ページネーション情報をセット
			//ページ数
			req.setAttribute("pagecount", service.getPageCount(req));
			//POSTの時は１ページ目としてページ番号をセット
			req.setAttribute("page", 1);

			path="/WEB-INF/searchResult.jsp";
		} catch (SQLException e) {

			e.printStackTrace();
			resp.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}



		//画面遷移
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
