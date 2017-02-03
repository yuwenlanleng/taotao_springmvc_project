package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.ItemParamService;

import zhk_taotao_common.ItemWrap;

@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/item/param/list")
	@ResponseBody
	public ItemWrap getAllItemParamItem(int page, int rows) {
		ItemWrap itemWrap = itemParamService.getAllItemParam(page, rows);
		return itemWrap;
	}
}
