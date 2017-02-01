package com.taotao.service;

import com.taotao.pojo.TbItemParam;

import zhk_taotao_util.TaotaoResult;

public interface ItemParamService {
	TaotaoResult getItemParamByCatId(long itemCatId);
	TaotaoResult addParam(TbItemParam tbItemParam);
}
