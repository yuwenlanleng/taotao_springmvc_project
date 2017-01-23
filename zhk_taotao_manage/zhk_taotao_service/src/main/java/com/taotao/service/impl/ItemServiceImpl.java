package com.taotao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

import zhk_taotao_common.ItemWrap;
	
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Override
	public TbItem getTbItemByItemId(long itemId) {
		// TODO Auto-generated method stub
		
		TbItemExample tbItemExample = new TbItemExample();
		Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
		if(tbItemList != null && tbItemList.size() > 0){
			return tbItemList.get(0);
		}
		return null;
	}

	@Override
	public ItemWrap getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		TbItemExample tbItemExample = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> tbItemPageList = tbItemMapper.selectByExample(tbItemExample);
		ItemWrap itemWrap = new ItemWrap();
		itemWrap.setRows(tbItemPageList);
		PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemPageList);
		long total = pageInfo.getTotal();
		itemWrap.setTotal(total);
		return itemWrap;
	}
	
}
