package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.service.ItemService;
import com.taotao.portal.wrap.ItemInfoWrap;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/info/{itemId}")
	public String getItemResult(@PathVariable long itemId,Model model) {
		ItemInfoWrap tbItem = itemService.getItemInfoByItemId(itemId);
		model.addAttribute("item", tbItem);
		return "item";
	}

	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public String getDescrResult(@PathVariable long itemId) {
		TbItemDesc tbItem = itemService.getItemDescByItemId(itemId);
		return tbItem.getItemDesc();
	}

	@RequestMapping(value ="/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getParamResult(@PathVariable long itemId) {
		String tbItem = itemService.getItemParamByItemId(itemId);
		return tbItem;
	}
}
