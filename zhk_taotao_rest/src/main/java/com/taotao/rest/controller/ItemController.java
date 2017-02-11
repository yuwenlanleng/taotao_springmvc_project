package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.service.ItemDescService;
import com.taotao.rest.service.ItemParamService;
import com.taotao.rest.service.ItemService;

import zhk_taotao_util.TaotaoResult;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemDescService itemDescService;

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/item/info/{itemId}")
	@ResponseBody
	public TbItem getItemResult(@PathVariable long itemId) {
		TbItem itemInfoResult = itemService.getItemInfoByItemId(itemId);
		return itemInfoResult;
	}

	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public TbItemDesc getDescrResult(@PathVariable long itemId) {
		TbItemDesc itemDescInfoResult = itemDescService.getItemDescByItemId(itemId);
		return itemDescInfoResult;
	}

	@RequestMapping("/item/param/{itemId}")
	@ResponseBody
	public TbItemParamItem getParamResult(@PathVariable long itemId) {
		TbItemParamItem itemParamResult = itemParamService.getItemParam(itemId);
		return itemParamResult;
	}
}
