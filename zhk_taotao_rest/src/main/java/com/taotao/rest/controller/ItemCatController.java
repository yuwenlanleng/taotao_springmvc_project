package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.ItemCatWrap;
import com.taotao.rest.service.ItemService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemService itemService;

	/*
	 * @RequestMapping(value="/itemcat/list",
	 * produces=MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	 * 
	 * @ResponseBody public String getItemCatList(String callback) { CatResult
	 * catResult = itemCatService.getItemCatList(); //把pojo转换成字符串 String json =
	 * JsonUtils.objectToJson(catResult); //拼装返回值 String result = callback + "("
	 * + json + ");"; return result; }
	 */

	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		ItemCatWrap itemCatWrap = itemService.getItemCat(callback);
		MappingJacksonValue mj = new MappingJacksonValue(itemCatWrap);
		mj.setJsonpFunction(callback);
		return mj;
	}
}
