package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.service.CartService;
import com.taotao.portal.wrap.CatCookieWrap;

import zhk_taotao_util.TaotaoResult;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/cart/add/{itemId}")
	public String addCartCookie(@PathVariable long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		cartService.addItemToCart(itemId, num, request, response);
		return "cartSuccess";
	}

	@RequestMapping("/cart/cart")
	public String getCartCookie(HttpServletRequest request,Model model) {
		List<CatCookieWrap> itemCatFromCookie = cartService.getItemCatFromCookie(request);
		if (itemCatFromCookie != null) {
			model.addAttribute("cartList",itemCatFromCookie);
		}else{
			model.addAttribute("cartList","");
		}
		return "cart";
	}
	
	@RequestMapping("/cart/delete/{itemId}")
	public String getCartCookie(@PathVariable long itemId,HttpServletRequest request,HttpServletResponse response,Model model) {
		TaotaoResult taotaoResult = cartService.deleteItemCatCookie(itemId,request,response);
		return "redirect:/cart/cart.html";
	}
	
	@RequestMapping("/cart/update/increment")
	public String increment(@RequestParam("num") int num,@RequestParam("itemId") long itemId,HttpServletRequest request,HttpServletResponse response) {
		cartService.incrementAndDecrement(num, itemId, request, response);
		return "redirect:/cart/cart.html";
	}
	
	@RequestMapping("/cart/update/decrement")
	public String decrement(@RequestParam("num") int num,@RequestParam("itemId") long itemId,HttpServletRequest request,HttpServletResponse response) {
		cartService.incrementAndDecrement(num, itemId, request, response);
		return "redirect:/cart/cart.html";
	}
}
