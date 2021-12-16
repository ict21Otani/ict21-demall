/**
 *
 */
package jp.java.demall.cart;

import java.time.LocalDateTime;

import jp.java.demall.search.Items;

/**
 * @author ta2ro
 * items_in_cartテーブルの情報クラス
 */
public class ItemsInCart {
	private String userId;
	private int itemId;
	private int amount;
	private LocalDateTime bookedDate;
	private Items items;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDateTime getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(LocalDateTime bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}
}
