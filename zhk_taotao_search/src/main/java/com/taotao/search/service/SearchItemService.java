package com.taotao.search.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.search.wrap.ItemSearchWrap;
import com.taotao.search.wrap.SearchResult;

public interface SearchItemService {
	/**
	 * 查询得到所有的相关数据库数据
	 * 
	 * @return
	 */
	public List<ItemSearchWrap> getAllItemSearchWrap();

	/**
	 * 通过分页查询结果
	 * 
	 * @param searchString
	 * @param startPage
	 * @param pageSize
	 * @return
	 * @throws SolrServerException 
	 */
	public SearchResult getSeachResult(String searchString, int startPage, int pageSize) throws SolrServerException;
}
