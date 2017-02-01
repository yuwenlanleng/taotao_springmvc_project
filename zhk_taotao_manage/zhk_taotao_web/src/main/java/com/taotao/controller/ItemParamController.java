package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

import zhk_taotao_util.TaotaoResult;

@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/item/param/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCatId(@PathVariable("itemCatId") long itemCatId) {
		TaotaoResult result = itemParamService.getItemParamByCatId(itemCatId);
		return result;
	}

	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	public TaotaoResult addItemParam(@PathVariable long cid,String paramData) {
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		itemParamService.addParam(tbItemParam);
		return TaotaoResult.ok();
	}
}
