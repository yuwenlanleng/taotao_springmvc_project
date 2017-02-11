package com.taotao.search.wrap;

import java.util.List;

public class SearchResult {
	private long taoTalCount;
	private long currentPage;
	private long taotalPage;
	public long getTaotalPage() {
		return taotalPage;
	}
	public void taotalPageCount(long taotalPage) {
		this.taotalPage = taotalPage;
	}
	private List<ItemSearchWrap> itemSearchWrapList;
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<ItemSearchWrap> getItemSearchWrapList() {
		return itemSearchWrapList;
	}
	public void setItemSearchWrapList(List<ItemSearchWrap> itemSearchWrapList) {
		this.itemSearchWrapList = itemSearchWrapList;
	}
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
	public void setTaotalPage(long taotalPage) {
		this.taotalPage = taotalPage;
	}
	
}
