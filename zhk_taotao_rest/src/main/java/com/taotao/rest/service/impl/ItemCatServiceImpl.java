package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.ItemCatWrap;
import com.taotao.rest.pojo.ItemNode;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public ItemCatWrap getItemCat(String callback) {
		// TODO Auto-generated method stub
		ItemCatWrap itemCatWrap = new ItemCatWrap();
		itemCatWrap.setData(getItemNodeList(0));
		return itemCatWrap;
	}

	private List<ItemNode> getItemNodeList(long parentId) {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
		List itemNodeList = new ArrayList<>();
		int count = 0;
		for (TbItemCat tbItemCat : tbItemCatList) {
			ItemNode itemNode = new ItemNode();
			if (tbItemCat.getIsParent()) {
				if (parentId == 0) {
					count++;
					itemNode.setName("<a href='/products/1.html'>" + tbItemCat.getName() + "</a>");
				} else {
					itemNode.setName(tbItemCat.getName());
				}
				itemNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				itemNode.setNode(getItemNodeList(tbItemCat.getId()));
				itemNodeList.add(itemNode);
				if (parentId == 0 && count >= 14) {
					break;
				}
			} else {
				itemNodeList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}

		}
		return itemNodeList;

	}
}
