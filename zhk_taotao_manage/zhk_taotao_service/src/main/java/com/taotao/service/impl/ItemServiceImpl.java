package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

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
import zhk_taotao_util.IDUtils;
import zhk_taotao_util.TaotaoResult;
	
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

	@Override
	public TaotaoResult addItem(TbItem item) {
		// TODO Auto-generated method stub
		Date createTime = new Date();
		long itemId = IDUtils.genItemId();
		System.out.println(itemId);
		item.setId(itemId);
		item.setStatus((byte)1);
		item.setCreated(createTime);
		item.setUpdated(createTime);
		tbItemMapper.insert(item);
		TaotaoResult ok = TaotaoResult.ok();
		return ok;
	}
	
}
