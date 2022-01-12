package jp.java.demall.search.api.xml;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.search.ItemSearchController;

@WebServlet("/search-api")
public class SearchApiXmlContoroller extends ItemSearchController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//URL直たたきでくる前提

		SearchApiXmlService service = new SearchApiXmlService();
		List<String> list=new ArrayList();
		try {
			list = service.execute(req);
		} catch (SQLException e) {
			//エラー画面表示
			e.printStackTrace();
			resp.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}
		req.setAttribute("xml", list);



	//main画面に戻る
	String path="/WEB-INF/searchResultApi.jsp";
	req.getRequestDispatcher(path).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO ポストで飛んでくる場合はフォーム画面がいる。
		//POSTのリクエストを受け取ってdoGetに処理を委譲
		doGet(req,resp);
	}


}
