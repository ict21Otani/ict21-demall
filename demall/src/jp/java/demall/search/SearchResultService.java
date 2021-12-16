/**
 *
 */
package jp.java.demall.search;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.common.constant.ConstParam;
import jp.java.demall.dao.ItemsDAO;

/**
 * @author ta2ro
 * リクエストから商品検索サービスクラス
 */
public class SearchResultService {

	/***
	 * 商品検索　ページ数指定
	 * @param request
	 * @param 何ページ目か
	 * @return　検索結果
	 * @throws SQLException
	 */
	public List<Items> execute(HttpServletRequest request, int page) throws SQLException {
		ItemsDAO dao = new ItemsDAO();

		try {

			return dao.findByKeywordAndCategoryNuberOfResult(request.getParameter("keyword"),
					Integer.parseInt(request.getParameter("category")), ConstParam.COUNT_ONEPAGE_ITEMS, page);
		} catch (SQLException e) {
			throw e;
		}

	}

	/***
	 * 商品検索　ページ数指定ナシ　リクエストから取り出し。
	 * @param request
	 * @return　検索結果
	 * @throws SQLException
	 */
	public List<Items> execute(HttpServletRequest request) throws SQLException {
		ItemsDAO dao = new ItemsDAO();
		int page = 1;
		try {
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}

			return dao.findByKeywordAndCategoryNuberOfResult(request.getParameter("keyword"),
					Integer.parseInt(request.getParameter("category")), ConstParam.COUNT_ONEPAGE_ITEMS, page);
		} catch (SQLException e) {
			throw e;
		}

	}



	/**
	 *
	 * @param request
	 * @return カテゴリ名
	 */
	public String getCategoryName(HttpServletRequest request) {

		String categoryName;
		String category = request.getParameter("category");

		if (category.equals("0")) {
			categoryName = "すべて";

		} else if (category.equals("1")) {
			categoryName = "帽子";

		} else {
			categoryName = "鞄";

		}

		return categoryName;

	}

	/**
	 *
	 * @param カテゴリID
	 * @return カテゴリ名
	 */
	public String getCategoryName(int id) {

		String categoryName;


		if (id==0) {
			categoryName = "すべて";

		} else if ((id==1) ) {
			categoryName = "帽子";

		} else {
			categoryName = "鞄";

		}

		return categoryName;

	}

	/***
	 * ページネーションのページ数を返す
	 * @param listSize
	 * @return
	 * @throws SQLException
	 */
	public int getPageCount(HttpServletRequest request) throws SQLException {
		ItemsDAO dao = new ItemsDAO();
		int cnt;
		try {
			cnt = dao.countByKeywordAndCategory(request.getParameter("keyword"),
					Integer.parseInt(request.getParameter("category")));
		} catch (SQLException e) {
			throw e;
		}

		//最低１ページ＋増加分を返す
		return 1 + (int)(cnt / (ConstParam.COUNT_ONEPAGE_ITEMS+0.5));// (cnt / (ConstParam.COUNT_ONEPAGE_ITEMS + 1));

	}

}
