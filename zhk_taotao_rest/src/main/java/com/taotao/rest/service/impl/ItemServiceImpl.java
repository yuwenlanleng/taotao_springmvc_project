package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Override
	public TbItem getItemInfoByItemId(long itemId) {
		// TODO Auto-generated method stub
		TbItem tbItemResult = tbItemMapper.selectByPrimaryKey(itemId);
		return tbItemResult;
	}

}
