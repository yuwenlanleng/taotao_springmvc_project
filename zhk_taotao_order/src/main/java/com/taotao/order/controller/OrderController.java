package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.order.service.OrderService;
import com.taotao.order.wrap.OrderWrap;

import zhk_taotao_util.TaotaoResult;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String addOrder(@RequestBody OrderWrap orderWrap){
		TaotaoResult taotaoResult = orderService.addOrder(orderWrap, orderWrap.getOrderItems(), orderWrap.getTbOrderShipping());
		if(taotaoResult.getStatus() == 200){
			return String.valueOf(taotaoResult.getData());
		}
		return "";
	}
}
