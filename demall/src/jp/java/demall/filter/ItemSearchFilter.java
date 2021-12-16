package jp.java.demall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.common.constant.ConstParam;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet Filter implementation class SearchResultFillter
 * 検索結果を表示するときのフィルター
 */
@WebFilter("/search")
public class ItemSearchFilter  implements Filter {

	/**
	 * @see CommonServlet#CommonServlet()
	 */
	public ItemSearchFilter() {
		super();

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/*日本語対応処理*/
		//リクエストの日本語対応（文字化け対応）
		request.setCharacterEncoding("UTF-8");

		//レスポンスの日本語対応
		response.setContentType("text/html;charset=UTF-8");

		//リクエストをチェック@category
		//ない場合、空文字の場合
		if (request.getParameter("category") == null || "".equals(request.getParameter("category"))) {
			//遷移がおかしいのエラーページへ
			String path = ConstFilePath.ILLEGALE_ACCESS;
			request.getRequestDispatcher(path).forward(request, response);
			return;
		} else {
			try {
				int category = Integer.parseInt(request.getParameter("category"));
				//カテゴリが0から1,2,の範囲（既定の範囲）外
				if (category < 0 || 2 < category) {
					String path = ConstFilePath.ILLEGALE_ACCESS;
					request.getRequestDispatcher(path).forward(request, response);
					return;
				}
			} catch (NumberFormatException e) {
				//遷移がおかしいのエラーページへ
				String path = ConstFilePath.ILLEGALE_ACCESS;
				request.getRequestDispatcher(path).forward(request, response);
				return;

			}
		}

		//リクエストをチェック@page<整数0からConstParam.MAX_PAGEまで>
		try {
			if (request.getParameter("page") != null) {
				int page = Integer.parseInt(request.getParameter("page"));
				//負の数のページの時と、決められたページを超えた不正リクエスト
				if (page < 0 || page > ConstParam.MAX_PAGE) {
					String path = ConstFilePath.ILLEGALE_ACCESS;
					request.getRequestDispatcher(path).forward(request, response);
					return;
				}
			}
		} catch (NumberFormatException e) {
			//数字以外が入ってきたとき
			String path = ConstFilePath.ILLEGALE_ACCESS;
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
