package com.taotao.portal.wrap;

import java.util.List;

public class SearchResult {
	private long taoTalCount;
	private long currentPage;
	private long taotalPage;
	private List<ItemSearchWrap> itemSearchWrapList;
	public long getTaoTalCount() {
		return taoTalCount;
	}
	public void setTaoTalCount(long taoTalCount) {
		this.taoTalCount = taoTalCount;
	}
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}
	public long getTaotalPage() {
		return taotalPage;
	}
	public void setTaotalPage(long taotalPage) {
		this.taotalPage = taotalPage;
	}
	public List<ItemSearchWrap> getItemSearchWrapList() {
		return itemSearchWrapList;
	}
	public void setItemSearchWrapList(List<ItemSearchWrap> itemSearchWrapList) {
		this.itemSearchWrapList = itemSearchWrapList;
	}
	
	
}
