package jp.java.demall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class RegisterUserFilter
 */
@WebFilter("/userregistconfirm")
public class RegisterUserFilter  implements Filter {

	/**
	 * Default constructor.
	 */
	public RegisterUserFilter() {

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
		//doPostの前

		/*日本語対応処理*/
		//リクエストの日本語対応（文字化け対応）
		request.setCharacterEncoding("UTF-8");

		//レスポンスの日本語対応
		response.setContentType("text/html;charset=UTF-8");

////---
			final String regist_path="/WEB-INF/registerUser.jsp";
try {
		//画面遷移
				String path=null;

				//nullcheck id
				String id=request.getParameter("id");
				if ( id == null ||id.equals("") ) {
					request.setAttribute("idnull", "1");
					path =regist_path;
				} else {
					request.setAttribute("idnull", "0");


					//255文字チェック
					if(id.length()>255) {
						request.setAttribute("id255", "1");
						path =regist_path;
					}
				}

				//nullcheck pwd
				String pwd=request.getParameter("password1");
				if ( pwd == null ||pwd.equals("") ) {
					request.setAttribute("pwdnull", "1");
					path =regist_path;
				} else {
					request.setAttribute("pwdnull", "0");
					//255文字チェック
					if(pwd.length()>255){
						path =regist_path;
						request.setAttribute("pwd255", "1");
					}

				}

				//nullcheck name
				String name=request.getParameter("name");
				if ( name == null ||name.equals("") ) {
					request.setAttribute("namenull", "1");
					path =regist_path;
				} else {
					request.setAttribute("namenull", "0");
					//32文字チェック
					if(name.length()>32){
						path =regist_path;
						request.setAttribute("name32", "1");
					}

				}
				//nullcheck name
				String address=request.getParameter("address");
				if ( address == null ||address.equals("") ) {
					path =regist_path;
					request.setAttribute("address-null", "1");
				} else {
					request.setAttribute("address-null", "0");
					//255文字チェック
					if(address.length()>255){
						path =regist_path;
						request.setAttribute("address255", "1");
					}
				}



		////----


		//パスワードの一致チェック
		String pw1 = request.getParameter("password1");
		String pw2 = request.getParameter("password2");

		//リクエストがどちらもあるとき
		if (pw1 != null && pw2 != null) {
			//２つのパスワードが違うとき
			if(!pw1.equals(pw2)) {
				//登録画面に戻す
				path =regist_path;

				//パスワードと確認用不一致の表示用フラグ
				request.setAttribute("pwdunmatch", "1");
				/*
				request.setAttribute("id",request.getParameter("id"));
				request.setAttribute("name",request.getParameter("name"));
				request.setAttribute("pwd1",request.getParameter("password1"));
				request.setAttribute("pwd2",request.getParameter("password2"));
				request.setAttribute("address",request.getParameter("address"))*/;

				//request.getRequestDispatcher(path).forward(request, response);return;


			}
		}

		//pathが設定されているときだけ画面遷移
		if (null != path) {
			//入力内容保持処理
			request.setAttribute("id",request.getParameter("id"));
			request.setAttribute("name",request.getParameter("name"));
			request.setAttribute("pwd1",request.getParameter("password1"));
			request.setAttribute("pwd2",request.getParameter("password2"));
			request.setAttribute("address",request.getParameter("address"));
			request.getRequestDispatcher(path).forward(request, response);
			return;

		}



}catch (Exception e) {
	e.printStackTrace();

}




		// pass the request along the filter chain
		chain.doFilter(request, response);
		//doPostの後
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
