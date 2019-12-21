package com.example.demo.entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;


/**
* 验证码对象
*/
public class ImageCode implements Serializable {

	private static final long serialVersionUID = -7777648777202617982L;

	private BufferedImage image;

	private String code;

	private boolean isExpire;
	
	public ImageCode(BufferedImage image, String code, boolean isExpire) {
        this.image = image;
        this.code = code;
        this.isExpire = isExpire;
    }

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isExpire() {
		return isExpire;
	}

	public void setExpire(boolean isExpire) {
		this.isExpire = isExpire;
	}
	
	

}
