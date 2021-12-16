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
 * Servlet implementation class UpdateUserCommitController
 */
@WebServlet("/updateusercommit")
public class UpdateUserCommitController extends CommonServlet  {
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

		//セッションが切れてない時
		if (isLoggedIn(request)) {
			User user;
			UpdateUserCommitService service =new UpdateUserCommitService();

			try {
				user=service.execute(request.getSession());
				request.setAttribute("user",user);
				//セッションに更新したものを登録する
				setLoginUser(request, user);
				request.setAttribute("pwdstar", service.getPasswordStar(user.getPassword()));

				String path ="/WEB-INF/updateUserCommit.jsp";
				request.getRequestDispatcher(path).forward(request, response);

			} catch (SQLException e) {
				response.sendRedirect(ConstFilePath.ERROR_HTML);
				return;
			} catch (NoSuchAlgorithmException e) {
				//パスワード暗号化に失敗した時
				e.printStackTrace();
				response.sendRedirect(ConstFilePath.ERROR_HTML);
			}
		}else {
			response.sendRedirect(ConstFilePath.ILLEGALE_ERROR_HTML);
			return;
		}



	}

}
