package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

import zhk_taotao_common.ItemWrap;
import zhk_taotao_util.TaotaoResult;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/content/query/list")
	@ResponseBody
	public ItemWrap getAllContentByContentCat(long categoryId, int page, int rows) {
		ItemWrap itemWrapResult = contentService.getAllContentByPage(categoryId, page, rows);
		return itemWrapResult;
	}

	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent tbContent) {
		contentService.addContent(tbContent);
		return TaotaoResult.ok();
	}

}
