package jp.java.demall.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class UpdateUserController
 */
@WebServlet("/updateuser")
public class UpdateUserController extends CommonServlet  {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * //会員情報変更リンクから、セッションより会員情報を取り出して画面遷移
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//セッション切れていない時
		if(isLoggedIn(request)){
			request.setAttribute("user", getLoginUser(request));

			String path="/WEB-INF/updateUser.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}else{//セッション切れはログインへ戻る

			response.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
