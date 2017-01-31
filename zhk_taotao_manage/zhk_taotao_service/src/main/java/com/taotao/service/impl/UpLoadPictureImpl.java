package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.UpLoadPicture;

import zhk_taotao_util.FtpUtil;
import zhk_taotao_util.IDUtils;

@Service
public class UpLoadPictureImpl implements UpLoadPicture {

	@Value("${FTP_UPLOAD_HOST}")
	private String FTP_UPLOAD_HOST;
	@Value("${FTP_UPLOAD_PORT}")
	private Integer FTP_UPLOAD_PORT;
	@Value("${FTP_UPLOAD_USERNAME}")
	private String FTP_UPLOAD_USERNAME;
	@Value("${FTP_UPLOAD_PASSWORD}")
	private String FTP_UPLOAD_PASSWORD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${UPLOAD_URL}")
	private String UPLOAD_URL;

	@Override
	public Map upLoadPicture(MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		String filePath = new DateTime().toString("/YYYY/MM/dd");
		String oldFileName = uploadFile.getOriginalFilename();
		String extendsName = oldFileName.substring(oldFileName.indexOf("."));
		String newFileName = IDUtils.genImageName() + extendsName;
		Map map = new HashMap<>();
		try {
			Boolean result = FtpUtil.uploadFile(FTP_UPLOAD_HOST, FTP_UPLOAD_PORT, FTP_UPLOAD_USERNAME,
					FTP_UPLOAD_PASSWORD, FTP_BASEPATH, filePath, newFileName, uploadFile.getInputStream());
			if (!result) {
				map.put("error", 1);
				map.put("message", "图片上传失败");
			} else {
				map.put("error", 0);
				map.put("url", UPLOAD_URL + filePath + "/" + newFileName);
			}
			System.out.println( UPLOAD_URL + filePath + "/" + newFileName);
		} catch (IOException e) {
			map.put("error", 1);
			map.put("message", "图片上传失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
