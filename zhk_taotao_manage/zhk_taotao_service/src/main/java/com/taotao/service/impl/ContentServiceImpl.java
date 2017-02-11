package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

import zhk_taotao_common.ItemWrap;
import zhk_taotao_util.TaotaoResult;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public ItemWrap getAllContentByPage(long categoryId, int page, int rows) {
		// TODO Auto-generated method stub
		TbContentExample tbContentExample = new TbContentExample();
		ItemWrap itemWrap = new ItemWrap();
		Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> tbContentList = tbContentMapper.selectByExample(tbContentExample);
		PageInfo<TbContent> pageInfo = new PageInfo<>(tbContentList);
		itemWrap.setTotal(pageInfo.getTotal());
		itemWrap.setRows(tbContentList);
		return itemWrap;
	}
	
	@Override
	public TaotaoResult addContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		tbContentMapper.insert(tbContent);
		return TaotaoResult.ok();
	}

}
