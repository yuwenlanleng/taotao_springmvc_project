package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.rest.service.ItemDescService;

import zhk_taotao_util.TaotaoResult;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	TbItemDescMapper tbItemDescMapper;

	@Override
	public TbItemDesc getItemDescByItemId(long itemId) {
		// TODO Auto-generated method stub
		TbItemDesc tbItemResult = tbItemDescMapper.selectByPrimaryKey(itemId);
		return tbItemResult;
	}
}
