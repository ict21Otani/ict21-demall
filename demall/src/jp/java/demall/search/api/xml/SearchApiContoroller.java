package jp.java.demall.search.api.xml;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.java.demall.common.constant.ConstFilePath;
import jp.java.demall.search.ItemSearchController;

/***
 * api機能のコントローラー
 * @author shisa
 *
 */
@WebServlet("/search-api")
public class SearchApiContoroller extends ItemSearchController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//URL直たたきでくる前提

		SearchApiService service = new SearchApiService();
		List<String> list = new ArrayList<String>();
		try {
			//XMLの内容を取得
			list = service.execute(req);
		} catch (SQLException e) {
			//エラー画面表示
			e.printStackTrace();
			resp.sendRedirect(ConstFilePath.ERROR_HTML);
			return;
		}
		//正常に帰ってきたときだけ処理する。
		if (null != list) {
			//XMLの時だけ設定する。
			if (req.getParameter("resp-type").equals("xml")) {
				//出力設定をXML
				resp.setContentType("text/xml;charset=UTF-8");
			}else if(req.getParameter("resp-type").equals("json")){
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
			}
			PrintWriter out = resp.getWriter();
			//XMLを一行ずつ出力する
			for (String apiStr : list) {
				out.println(apiStr);
			}
		}else {
			//エラーの時
			resp.sendRedirect(ConstFilePath.ILLEGALE_ACCESS);
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO ポストで飛んでくる場合はフォーム画面がいる。
		//POSTのリクエストを受け取ってdoGetに処理を委譲
		doGet(req, resp);
	}

}
