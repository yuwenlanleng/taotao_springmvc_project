package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.service.SearchService;
import com.taotao.portal.wrap.SearchResult;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/search")
	public String getAllList(@RequestParam("q") String searchString,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		if (!StringUtils.isEmpty(searchString)) {
			try {
				searchString = new String(searchString.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SearchResult allSearchListResult = searchService.getAllSearchList(searchString, page);
		model.addAttribute("query", searchString);
		if (allSearchListResult != null) {
			model.addAttribute("totalPages", allSearchListResult.getTaotalPage());
			model.addAttribute("itemList", allSearchListResult.getItemSearchWrapList());
			model.addAttribute("page", allSearchListResult.getCurrentPage());
		}
		return "search";
	}
}
