package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.search.service.SearchItemService;
import com.taotao.search.wrap.SearchResult;

import zhk_taotao_util.TaotaoResult;

@Controller
public class ItemSearchController {

	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/query")
	@ResponseBody
	public TaotaoResult getSearchResult(@RequestParam(value = "q") String searchString,
										@RequestParam(value = "page",defaultValue="1") int page, 
										@RequestParam(value = "rows", defaultValue = "60") int rows)
			throws SolrServerException, UnsupportedEncodingException {
		if (searchString == null || searchString == "") {
			return TaotaoResult.build(500, "搜索内容不能为空");
		}
		searchString = new String(searchString.getBytes("iso-8859-1"), "utf-8");
		SearchResult seachResult = searchItemService.getSeachResult(searchString, page, rows);
		return TaotaoResult.ok(seachResult);
	}
}
