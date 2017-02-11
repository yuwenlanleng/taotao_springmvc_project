package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.DAO.ItemSearchDaoService;
import com.taotao.search.mapper.ItemSearchWrapMapper;
import com.taotao.search.service.SearchItemService;
import com.taotao.search.wrap.ItemSearchWrap;
import com.taotao.search.wrap.SearchResult;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemSearchWrapMapper itemSearchWrapMapper;

	@Autowired
	private ItemSearchDaoService itemSearchDaoService;

	@Override
	public List<ItemSearchWrap> getAllItemSearchWrap() {
		// TODO Auto-generated method stub
		List<ItemSearchWrap> itemSearchList = itemSearchWrapMapper.getItemSearchList();
		return itemSearchList;
	}

	@Override
	public SearchResult getSeachResult(String searchString, int startPage, int pageSize) throws SolrServerException {
		// TODO Auto-generated method stub
		SolrQuery solrQuey = new SolrQuery();
		solrQuey.setQuery(searchString);
		solrQuey.setStart((startPage-1)*pageSize);
		solrQuey.setRows(pageSize);
		solrQuey.set("df", "item_keywords");//默认搜索域
		solrQuey.setHighlight(true);
		solrQuey.addHighlightField("item_title");
		solrQuey.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuey.setHighlightSimplePost("<em>");
		SearchResult searchResult = itemSearchDaoService.getItemSearchWrapList(solrQuey);
		long taotalPageCount = 0; 
		taotalPageCount = searchResult.getTaoTalCount()%pageSize > 0?taotalPageCount +1:taotalPageCount;
		searchResult.setTaotalPage(taotalPageCount);
		searchResult.setCurrentPage(startPage);
		return searchResult;
	}

}
