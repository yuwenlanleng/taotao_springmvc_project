package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.service.ContentService;

import zhk_taotao_util.ExceptionUtil;
import zhk_taotao_util.TaotaoResult;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/content/list/{categoryId}")
	@ResponseBody
	public TaotaoResult getContentListByCategoryId(@PathVariable long categoryId) {
		try {
			TaotaoResult result = contentService.getAllContentListByCategoryId(categoryId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
			// TODO: handle exception
		}
	}
}
