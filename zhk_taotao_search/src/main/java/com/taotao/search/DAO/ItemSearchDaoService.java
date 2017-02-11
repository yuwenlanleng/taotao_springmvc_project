package com.taotao.search.DAO;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.search.wrap.SearchResult;

public interface ItemSearchDaoService {

	/**
	 * 或的Item封装信息的
	 * 
	 * @return
	 * @throws SolrServerException 
	 */
	public SearchResult getItemSearchWrapList(SolrQuery solrQuery) throws SolrServerException;
}
