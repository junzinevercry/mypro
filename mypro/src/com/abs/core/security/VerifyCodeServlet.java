package com.abs.core.security;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abs.core.util.VerifyCode;

/**
 * Servlet implementation class Test
 */
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        //设置浏览器不缓存本页
        response.setHeader("Cache-Control", "no-cache");
        
        //生成验证码，写入用户session
        String verifyCode=VerifyCode.generateTextCode(VerifyCode.TYPE_NUM_ONLY,4,"0oOIl");
        request.getSession().setAttribute("verifyCode",verifyCode);
        
        //输出验证码给客户端
        response.setContentType("image/jpeg");
        BufferedImage bim=VerifyCode.generateImageCode(verifyCode, 50, 23, 0,false,Color.LIGHT_GRAY,Color.BLACK,null);
        ImageIO.write(bim, "JPEG",response.getOutputStream());    
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
