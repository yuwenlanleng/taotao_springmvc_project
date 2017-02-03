package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

import zhk_taotao_common.ItemWrap;
import zhk_taotao_util.IDUtils;
import zhk_taotao_util.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	private TaotaoResult addItemDescResult;

	@Override
	public TbItem getTbItemByItemId(long itemId) {
		// TODO Auto-generated method stub

		TbItemExample tbItemExample = new TbItemExample();
		Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> tbItemList = tbItemMapper.selectByExample(tbItemExample);
		if (tbItemList != null && tbItemList.size() > 0) {
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
	public TaotaoResult addItem(TbItem item, String desc, String paramData) throws Exception {
		// TODO Auto-generated method stub
		Date createTime = new Date();
		long itemId = IDUtils.genItemId();
		System.out.println(itemId);
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(createTime);
		item.setUpdated(createTime);
		tbItemMapper.insert(item);
		TaotaoResult addItemDescResult = addItemDesc(item.getId(), desc);
		if (addItemDescResult.getStatus() != 200) {
			throw new Exception();
		}
		addItemDescResult = addItemParamItem(item.getId(), paramData);
		if (addItemDescResult.getStatus() != 200) {
			throw new Exception();
		}
		TaotaoResult ok = TaotaoResult.ok();
		return ok;
	}

	private TaotaoResult addItemDesc(long itemId, String desc) {
		Date createDate = new Date();
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(createDate);
		tbItemDesc.setUpdated(createDate);
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
	}

	private TaotaoResult addItemParamItem(long itemId, String paramData) {
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(paramData);
		// TODO Auto-generated method stub
		Date createDate = new Date();
		tbItemParamItem.setCreated(createDate);
		tbItemParamItem.setUpdated(createDate);
		tbItemParamItemMapper.insert(tbItemParamItem);
		return TaotaoResult.ok();
	}
	
}
