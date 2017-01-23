package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

import zhk_taotao_common.ItemWrap;

@Controller
public class ItemController {

	/**
	 * 通过id查询商品记录
	 */
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemByItemId(@PathVariable long itemId) {
		TbItem tbItem = itemService.getTbItemByItemId(itemId);
		System.out.println(tbItem);
		return tbItem;
	}

	/**
	 * 分页查询商品列表
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public ItemWrap getItemList(int page,int rows) {
		ItemWrap itemList = itemService.getItemList(page, rows);
		return itemList;
		
		
	}	
}
