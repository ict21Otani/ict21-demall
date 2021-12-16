package jp.nib.ict21.ecsite.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CommonFilter
 * 全適用フィルター
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

    /**
     * Default constructor.
     */
    public CommonFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * フィルター処理
	 *ログインの記録や日本語化処理
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*前処理*/

		/*日本語対応処理*/
		//リクエストの日本語対応（文字化け対応）
		request.setCharacterEncoding("UTF-8");

		//レスポンスの日本語対応
		response.setContentType("text/html;charset=UTF-8");

		//前処理↑
		chain.doFilter(request, response);//dopost,doget呼び出し
		//後処理↓

		/*後処理*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
