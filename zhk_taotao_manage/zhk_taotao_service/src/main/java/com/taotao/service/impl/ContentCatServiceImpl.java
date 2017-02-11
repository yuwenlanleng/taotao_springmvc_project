package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentCatService;

import zhk_taotao_common.ItemCatWrap;
import zhk_taotao_util.TaotaoResult;

@Service
public class ContentCatServiceImpl implements ContentCatService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<ItemCatWrap> getContentItemCatList(long parentId) {
		// TODO Auto-generated method stub
		TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
		Criteria criteria = tbContentCategoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> resultList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
		List<ItemCatWrap> itemCatWrapList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : resultList) {
			ItemCatWrap itemCatWrap = new ItemCatWrap();
			itemCatWrap.setId(tbContentCategory.getId());
			itemCatWrap.setText(tbContentCategory.getName());
			itemCatWrap.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			itemCatWrapList.add(itemCatWrap);
		}
		return itemCatWrapList;
	}

	@Override
	public TaotaoResult addContentCat(long parentId, String name) {
		// TODO Auto-generated method stub
		Date createDate = new Date();
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setName(name);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setStatus(1);
		tbContentCategory.setCreated(createDate);
		tbContentCategory.setUpdated(createDate);
		tbContentCategoryMapper.insert(tbContentCategory);
		TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		return TaotaoResult.ok(tbContentCategory);
	}

	@Override
	public TaotaoResult deleteContentCat(long parentId, long id) {
		// TODO Auto-generated method stub
		TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		tbContentCategoryMapper.deleteByPrimaryKey(id);
		TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
		Criteria criteria = tbContentCategoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> childList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
		if (childList.size() <= 0) {
			parentNode.setIsParent(false);
		}

		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCat(long nodeId, String name) {
		// TODO Auto-generated method stub
		TbContentCategory node = tbContentCategoryMapper.selectByPrimaryKey(nodeId);
		node.setName(name);
		tbContentCategoryMapper.updateByPrimaryKey(node);
		return TaotaoResult.ok();
	}

}
