package jp.java.demall.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.login.User;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class UpdateConfirmController
 */
@WebServlet("/updateuserconfirm")
public class UpdateUserConfirmController extends CommonServlet {
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

		UpdateUserConfirmService service = new UpdateUserConfirmService();
		User user=null;
		String path = null;

		if(isLoggedIn(request)) {
			user = service.execute(request);
			request.setAttribute("user", user);
			//パスワードの*表示
			String star=service.getPasswordStar(user.getPassword());

			request.setAttribute("pwdstar",star);

			//更新用データをセッションにセット
			request.getSession().setAttribute("upd", user);


			 path = "/WEB-INF/updateUserConfirm.jsp";
			 request.getRequestDispatcher(path).forward(request, response);
		}else {
			//セッション切れ

			response.sendRedirect(ConstFilePath.ILLEGALE_ERROR_HTML);
			return;
		}


	}

}
