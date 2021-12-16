package jp.java.demall.withdrow;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.login.User;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class WithdrowCommitController
 */
@WebServlet("/withdrawcommit")
public class WithdrowCommitController extends CommonServlet  {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dogetは処理を行わない　エラー画面
		response.sendRedirect(ConstFilePath.ILLEGALE_ACCESS);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション切れてない
		if(isLoggedIn(request)) {
			WithdrowCommitService service = new WithdrowCommitService();
			User  user =getLoginUser(request);
			//リクエストにユーザー情報セット
			request.setAttribute("user",getLoginUser(request));
			try {
				//テーブルデータ削除
				service.execute(user);

				//セッション削除
				request.getSession().invalidate();

				//画面遷移
				String path ="/WEB-INF/withdrawCommit.jsp";
				request.getRequestDispatcher(path).forward(request, response);


			} catch (SQLException e) {

				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			}

		}
		//セッション切れ
		else {
			//セッション切れ
			response.sendRedirect(ConstFilePath.ILLEGALE_ERROR_HTML);
			return;
		}
	}

}
