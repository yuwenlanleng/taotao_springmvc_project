package com.taotao.portal.service;

import com.taotao.portal.wrap.SearchResult;

public interface SearchService {
	public SearchResult getAllSearchList(String searchString ,int page);
}
