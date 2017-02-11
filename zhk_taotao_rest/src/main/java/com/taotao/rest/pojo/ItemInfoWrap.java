package com.taotao.rest.pojo;

import com.taotao.pojo.TbItem;

public class ItemInfoWrap extends TbItem {
	String image = getImage();
	private String[] images;
	

	public String[] getImages() {
		if(image != null){
			String[] images = image.split(",");
			return images;
		}
		return null;
	}

}
