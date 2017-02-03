package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;

import zhk_taotao_common.ItemParamWrap;
import zhk_taotao_common.ItemWrap;
import zhk_taotao_util.TaotaoResult;

@Service
public class ItemParameServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemCatService itemCateService;

	@Override
	public TaotaoResult getItemParamByCatId(long catId) {
		// TODO Auto-generated method stub
		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		Criteria createCriteria = tbItemParamExample.createCriteria();
		createCriteria.andItemCatIdEqualTo(catId);
		List<TbItemParam> tbItemParamsList = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
		if (tbItemParamsList != null && tbItemParamsList.size() > 0) {
			return TaotaoResult.ok(tbItemParamsList.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult addParam(TbItemParam tbItemParam) {
		// TODO Auto-generated method stub
		Date createDate = new Date();
		tbItemParam.setCreated(createDate);
		tbItemParam.setUpdated(createDate);
		tbItemParamMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}

	@Override
	public ItemWrap getAllItemParam(int page,int rows) {
		// TODO Auto-generated method stub
		TbItemParamItemExample tbItemParamExample = new TbItemParamItemExample();
		PageHelper.startPage(page, rows);
		List<TbItemParamItem> tbItemParamPageList = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamExample);
		List<ItemParamWrap> itemParamWrapList = new ArrayList<>();
		for (TbItemParamItem tbItemParamItem : tbItemParamPageList) {
			TbItem tbItem = itemService.getTbItemByItemId(tbItemParamItem.getItemId());
			TbItemCat tbItemCate = itemCateService.getItemCatByCatId(tbItem.getCid());
			ItemParamWrap itemParamWrap = new ItemParamWrap();
			itemParamWrap.setId(tbItemParamItem.getId());
			itemParamWrap.setItemCatId(tbItem.getCid());
			itemParamWrap.setItemCatName(tbItemCate.getName());
			itemParamWrap.setParamData(tbItemParamItem.getParamData());
			itemParamWrap.setCreated(tbItemParamItem.getCreated());
			itemParamWrap.setUpdated(tbItemParamItem.getUpdated());
			itemParamWrapList.add(itemParamWrap);
		}
		ItemWrap itemWrap = new ItemWrap();
		itemWrap.setRows(itemParamWrapList);
		PageInfo<TbItemParamItem> pageInfo = new PageInfo<>(tbItemParamPageList);
		long total = pageInfo.getTotal();
		itemWrap.setTotal(total);
		return itemWrap;
	}

	
}
