package jp.java.demall.itemdetail;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.dao.ItemsDAO;
import jp.java.demall.search.Items;

public class ItemDetailService {

	/**
	 * @throws SQLException
	 * @param リクエスト
	 */
	public Items execute(HttpServletRequest request) throws SQLException {
		try {
			return new ItemsDAO().findById(Integer.parseInt(request.getParameter("itemId")));
		} catch (SQLException e) {
			throw e;
		}

	}
}
