package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

import zhk_taotao_common.ItemCatWrap;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<ItemCatWrap> getItemCatWrapByParentId(long parentId) {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		Criteria createCriteria = tbItemCatExample.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
		List<ItemCatWrap> itemCatWrapList = new ArrayList<>();
		for (TbItemCat tbItemCat : tbItemCatList) {
			ItemCatWrap itemCatWrap = new ItemCatWrap();
			itemCatWrap.setId(tbItemCat.getId());
			itemCatWrap.setText(tbItemCat.getName());
			itemCatWrap.setState(tbItemCat.getIsParent() ? "closed" : "open");
			itemCatWrapList.add(itemCatWrap);
		}
		return itemCatWrapList;
	}

	@Override
	public TbItemCat getItemCatByCatId(long itemCatId) {
		// TODO Auto-generated method stub
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		Criteria createCriteria = tbItemCatExample.createCriteria();
		createCriteria.andIdEqualTo(itemCatId);
		List<TbItemCat> result = tbItemCatMapper.selectByExample(tbItemCatExample);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

}
