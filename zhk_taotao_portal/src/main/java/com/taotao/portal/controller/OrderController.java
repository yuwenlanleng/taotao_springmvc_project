package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import com.taotao.portal.wrap.CatCookieWrap;
import com.taotao.portal.wrap.OrderWrap;

import zhk_taotao_util.TaotaoResult;

@Controller
public class OrderController {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/order-cart")
	public String showOrderCartPage(Model model, HttpServletRequest request) {
		List<CatCookieWrap> itemCatFromCookieList = cartService.getItemCatFromCookie(request);
		model.addAttribute("cartList", itemCatFromCookieList);
		return "order-cart";
	}

	@RequestMapping("/order/add")
	public String addOrder(OrderWrap orderWrap, Model model) {
		TaotaoResult taotaoResult = orderService.addOrder(orderWrap);
		if (taotaoResult.getStatus() == 200) {
			model.addAttribute("orderId", String.valueOf(taotaoResult.getData()));
			model.addAttribute("payment",orderWrap.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3));
			return "success";
		}else{
			model.addAttribute("message", taotaoResult.getData());
			return "/error/exception";
		}
	}
}
