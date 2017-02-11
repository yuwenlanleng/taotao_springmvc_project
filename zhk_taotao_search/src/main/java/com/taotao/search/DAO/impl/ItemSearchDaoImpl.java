package com.taotao.search.DAO.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.DAO.ItemSearchDaoService;
import com.taotao.search.wrap.ItemSearchWrap;
import com.taotao.search.wrap.SearchResult;

@Repository
public class ItemSearchDaoImpl implements ItemSearchDaoService {

	@Autowired
	private SolrServer solrServer;

	@Override
	public SearchResult getItemSearchWrapList(SolrQuery solrQuery) throws SolrServerException {
		// TODO Auto-generated method stub
		SearchResult searchResult = new SearchResult();
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		searchResult.setTaoTalCount(solrDocumentList.getNumFound());
		List<ItemSearchWrap> itemSearWrapList = new ArrayList<>();
		for (SolrDocument solrDocument : solrDocumentList) {
			ItemSearchWrap itemSearchWrap = new ItemSearchWrap();
			itemSearchWrap.setId((String)solrDocument.get("id"));
			List<String> highLightlist = highlighting.get(solrDocument.get("id")).get("item_title");
			if(highlighting != null && highlighting.size() > 0){
				itemSearchWrap.setTitle(highLightlist.get(0));
			}else{
				itemSearchWrap.setTitle((String)solrDocument.get("item_title"));
				
			}
			itemSearchWrap.setCategoryName((String)solrDocument.get("item_category_name"));
			itemSearchWrap.setImage((String)solrDocument.get("item_image"));
			itemSearchWrap.setItemDesc((String)solrDocument.get("item_desc"));
			itemSearchWrap.setPrice((long)solrDocument.get("item_price"));
			itemSearchWrap.setSellPoint((String)solrDocument.get("item_sell_point"));
			itemSearWrapList.add(itemSearchWrap);
		}
		searchResult.setItemSearchWrapList(itemSearWrapList);
		return searchResult;
	}

}
