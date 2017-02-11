package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.ContentCatService;

import zhk_taotao_common.ItemCatWrap;
import zhk_taotao_util.TaotaoResult;

@Controller
public class ContentCatController {

	@Autowired
	private ContentCatService ContentCatService;

	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<ItemCatWrap> getContentCatList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		List<ItemCatWrap> contentItemCatList = ContentCatService.getContentItemCatList(parentId);
		return contentItemCatList;
	}

	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult addContentCat(long parentId, String name) {
		TaotaoResult result = ContentCatService.addContentCat(parentId, name);
		return result;
	}

	@RequestMapping("/content/category/delete")
	@ResponseBody
	public TaotaoResult deleteContentCat(long parentId, long id) {
		TaotaoResult deleteResult = ContentCatService.deleteContentCat(parentId, id);
		return deleteResult;
	}

	@RequestMapping("/content/category/update")
	@ResponseBody
	public TaotaoResult updateContentCat(long id, String name) {
		TaotaoResult result = ContentCatService.updateContentCat(id, name);
		return result;
	}
}
