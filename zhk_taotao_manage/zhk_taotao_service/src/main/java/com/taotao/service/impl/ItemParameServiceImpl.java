package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

import zhk_taotao_util.TaotaoResult;

@Service
public class ItemParameServiceImpl implements ItemParamService {

	@Autowired
	TbItemParamMapper tbItemParamMapper;

	@Override
	public TaotaoResult getItemParamByCatId(long catId) {
		// TODO Auto-generated method stub
		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		Criteria createCriteria = tbItemParamExample.createCriteria();
		createCriteria.andItemCatIdEqualTo(catId);
		List<TbItemParam> tbItemParamsList = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
		if (tbItemParamsList != null && tbItemParamsList.size() > 0) {
			return TaotaoResult.ok(tbItemParamsList.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult addParam(TbItemParam tbItemParam) {
		// TODO Auto-generated method stub
		Date createDate = new Date();
		tbItemParam.setCreated(createDate);
		tbItemParam.setUpdated(createDate);
		tbItemParamMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}

}
