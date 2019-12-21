package com.example.demo.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.ImageCode;

@Controller
public class VariableController {

	/**
     * 生成验证码
	 * @throws IOException 
     */
    @RequestMapping("/createCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	ImageCode code = createImageCode();
    	request.getSession().setAttribute("code", code);
    	ImageIO.write(code.getImage(), "jpeg", response.getOutputStream());
    }
    
    public ImageCode createImageCode() {
    	int width = 100; // 验证码图片宽度
        int height = 36; // 验证码图片长度
        int length = 4; // 验证码位数
        BufferedImage imagBuilder = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random r = new Random();
        Graphics g = imagBuilder.getGraphics();
        g.setColor(new Color(10, 30, 50));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(new Color(160, 200, 100));
        for(int i = 0; i < 4; i++) {
            g.drawLine(0, 0, r.nextInt(100), r.nextInt(36));
        }

        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(r.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110), 20 + r.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();
        return new ImageCode(imagBuilder, sRand.toString(), true);
      
    }
}
