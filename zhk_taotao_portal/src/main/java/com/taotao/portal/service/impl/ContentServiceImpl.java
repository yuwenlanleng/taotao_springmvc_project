package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;

import zhk_taotao_util.HttpClientUtil;
import zhk_taotao_util.JsonUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${HTTPCLIENTURL}")
	private String HTTPCLIENTURL;

	@Value("${HTTPCLIENTREQUESTURL}")
	private String HTTPCLIENTREQUESTURL;

	@Override
	public String getContentList() {
		// TODO Auto-generated method stub
		try {
			String httpClientDoGetResult = HttpClientUtil.doGet(HTTPCLIENTURL + HTTPCLIENTREQUESTURL);
			TaotaoResult formatToList = TaotaoResult.formatToList(httpClientDoGetResult, TbContent.class);
			List<TbContent> tbContentList = (List<TbContent>) formatToList.getData();
			List<Map> resultList = new ArrayList<>();
			for (TbContent tbContent : tbContentList) {
				Map map = new HashMap<>();
				map.put("srcB", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("src", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("href", tbContent.getUrl());
				map.put("heithB", 240);
				resultList.add(map);
			}
			String json = JsonUtils.objectToJson(resultList);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
