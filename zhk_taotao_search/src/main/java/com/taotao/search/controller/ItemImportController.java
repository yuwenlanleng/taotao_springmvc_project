package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.search.service.ItemImportService;

import zhk_taotao_util.TaotaoResult;

@Controller
public class ItemImportController {

	@Autowired
	private ItemImportService itemImportService;
	
	@RequestMapping("/import")
	@ResponseBody
	public TaotaoResult importAllItemList() {
		TaotaoResult importAllItemListResult = itemImportService.importAllItemList();
		return importAllItemListResult;
	}
}
