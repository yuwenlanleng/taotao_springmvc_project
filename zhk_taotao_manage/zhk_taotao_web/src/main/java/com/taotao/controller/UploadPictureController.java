package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.UpLoadPicture;

import zhk_taotao_util.JsonUtils;

@Controller
public class UploadPictureController {

	@Autowired
	private UpLoadPicture upLoadService;

	@RequestMapping("/pic/upload")
	@ResponseBody
	String uploadPicture(MultipartFile uploadFile) {
		Map resultMap = upLoadService.upLoadPicture(uploadFile);
		String resultJson = JsonUtils.objectToJson(resultMap);
		return resultJson;
	}
}
