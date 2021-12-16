package jp.java.demall.itemdetail;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.search.Items;
import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class ItemDetailController
 */
@WebServlet("/itemdetail")
public class ItemDetailController extends CommonServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ItemDetailService service = new ItemDetailService();
		Items item=null;
		try {
			item=service.execute(request);

			String path = "WEB-INF/itemDetail.jsp";
			request.setAttribute("item",item);
			request.getRequestDispatcher(path).forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
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
