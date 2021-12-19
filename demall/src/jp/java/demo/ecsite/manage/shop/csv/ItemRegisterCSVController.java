package jp.java.demo.ecsite.manage.shop.csv;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nib.ict21.ecsite.common.CommonServlet;

/**
 * Servlet implementation class ItemRegisterCSVController
 * @MultipartConfig(location="/tmp", maxFileSize=1048576)はアップロード先のフォルダ作る必要あり
 * ワークスペース下～\Catalina\localhost\xxxxx（プロジェクト名）\tmp←config(上の行で指定)
 */
@WebServlet("/itemregistcsv")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class ItemRegisterCSVController extends CommonServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CSVページへの遷移
		String path="/WEB-INF/itemRegisterCsv.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CSVページで取り込みボタンを押したときの処理

		//リクエストからファイルを取り出す
		ItemRegistCSVService service = new ItemRegistCSVService();

		try {
			String realPath=getServletContext().getRealPath("/WEB-INF/uploaded");
			service.excute(request,realPath);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		String path="/WEB-INF/itemRegisterCsvCommit.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
