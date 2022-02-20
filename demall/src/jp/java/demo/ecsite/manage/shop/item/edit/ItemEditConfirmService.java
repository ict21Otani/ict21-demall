/**
 *
 */
package jp.java.demo.ecsite.manage.shop.item.edit;

import javax.servlet.http.HttpServletRequest;

import jp.java.demall.search.Items;

/**
 * @author ta2ro
 *
 */
public class ItemEditConfirmService {

	public Items excute(HttpServletRequest request) {

		Items items = new Items();
		items.setName(request.getParameter("name"));
		items.setCategoryId(Integer.parseInt(request.getParameter("category")));
		items.setColor(request.getParameter("color"));
		items.setStock(Integer.parseInt(request.getParameter("stock")));
		items.setManufacturer(request.getParameter("manufacturer"));
		items.setPrice(Integer.parseInt(request.getParameter("price")));
		//オススメチェックがないときはnull
		if (request.getParameter("recomended")!=null) {
			items.setRecommended(true);
		} else {
			items.setRecommended(false);
		}

		return items;

	}
}
