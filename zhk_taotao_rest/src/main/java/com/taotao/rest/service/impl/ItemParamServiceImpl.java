package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public TbItemParamItem getItemParam(long itemId) {
		TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
		Criteria criteria = tbItemParamItemExample.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		// TODO Auto-generated method stub
		List<TbItemParamItem> resultList = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
		if(resultList != null && resultList.size() > 0){
			return resultList.get(0);
		}
		return null;
	}

}
