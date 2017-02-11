package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.portal.service.SearchService;
import com.taotao.portal.wrap.SearchResult;

import zhk_taotao_util.ExceptionUtil;
import zhk_taotao_util.HttpClientUtil;
import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${HTTPCLIENTREQUESTFORSEARCH}")
	private String HTTPCLIENTREQUESTFORSEARCH;

	@Value("${HTTPCLIENTITEMSEARCHBASEURL}")
	private String HTTPCLIENTITEMSEARCHBASEURL;

	@Override
	public SearchResult getAllSearchList(String searchString, int page) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("q", searchString);
			paramMap.put("page", String.valueOf(page));
			String httpClientResult = HttpClientUtil.doGet(HTTPCLIENTITEMSEARCHBASEURL + HTTPCLIENTREQUESTFORSEARCH, paramMap);
			TaotaoResult formatToPojo = TaotaoResult.formatToPojo(httpClientResult, SearchResult.class);
			if (formatToPojo.getStatus() == 200) {
				SearchResult searchResult = (SearchResult)formatToPojo.getData();
				return searchResult;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
