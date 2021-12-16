package jp.java.demall.withdrow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class WithdrowController
 */
@WebServlet("/withdrawconfirm")
public class WithdrowConfirmController extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 画面遷移のみ
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ログインログイン済み
		if (isLoggedIn(request)) {
			String path = "/WEB-INF/withdrawConfirm.jsp";
			request.setAttribute("user", getLoginUser(request));
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			//ログイン画面へ
			String path = "/WEB-INF/login.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
