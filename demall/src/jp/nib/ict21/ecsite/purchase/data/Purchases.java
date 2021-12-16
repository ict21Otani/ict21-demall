package jp.nib.ict21.ecsite.purchase.data;

import java.time.LocalDateTime;
import java.util.List;

public class Purchases {

	private int purchase_id;
	private String purchased_user;
	private LocalDateTime purchased_date;
	private String destination;
	private boolean cancel;
	/**PurchasesDetails複数保持用*/
	private List<PurchasesDetails> purchasesDetailsList;

	public List<PurchasesDetails> getPurchasesDetailsList() {
		return purchasesDetailsList;
	}
	public void setPurchasesDetailsList(List<PurchasesDetails> purchasesDetailsList) {
		this.purchasesDetailsList = purchasesDetailsList;
	}
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public String getPurchased_user() {
		return purchased_user;
	}
	public void setPurchased_user(String purchased_user) {
		this.purchased_user = purchased_user;
	}
	public LocalDateTime getPurchased_date() {
		return purchased_date;
	}
	public void setPurchased_date(LocalDateTime purchased_date) {
		this.purchased_date = purchased_date;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

}