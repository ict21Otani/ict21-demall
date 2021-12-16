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
 * 会員登録画面で登録ボタンを押したところから
 */
@WebServlet("/userregistconfirm")
public class UserRegisterConfirmController extends CommonServlet {
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
		//登録処理
		//フィルターで入力処理

		UserRegisterConfirmService service = new UserRegisterConfirmService();
		User user=service.excute(request);

		request.setAttribute("user", user);
		//セッションにセット
		request.getSession().setAttribute("regist", user);

		//パスワードの*表示
		String star=service.getPasswordStar(user.getPassword());

		request.setAttribute("pwdstar", star);

		String path="/WEB-INF/registerUserConfirm.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

}
