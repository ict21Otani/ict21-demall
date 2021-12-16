package jp.java.demall.user;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
 * 会員登録画面で登録ボタンを押したところから
 */
@WebServlet("/userregistcommit")
public class UserRegisterCommitController extends CommonServlet {
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
		//登録処理

		UserRegisterCommitService service = new UserRegisterCommitService();

		try {
			service.excute(request.getSession());
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		} catch (NoSuchAlgorithmException e) {
			//パスワード暗号化に失敗した時
			e.printStackTrace();
			response.sendRedirect(ConstFilePath.ERROR_HTML);
		}
		User user = (User) request.getSession().getAttribute("regist");

		request.setAttribute("username", user.getName());

		String path = "/WEB-INF/registerUserCommit.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

}
