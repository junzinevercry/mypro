package com.abs.service.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.abs.ctms.util.ReadFileUtils;

/**
 * Servlet implementation class PubMsgServlet
 */
public class PubMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //得到留言标题、内容及用户输入的验证码
        String verifyCode=request.getParameter("verifyCode");
        
        //取session中的正确验证码
        String legalCode=null;
        if(request.getSession().getAttribute("rand")!=null)
            legalCode=(String)(request.getSession().getAttribute("rand"));
    
        String next;
        //String hasValidation = ReadFileUtils.getFilePath("HAS_VALIDATION", "public");
        //if(hasValidation != null || "Y".equals(hasValidation)){
        
	        //比较session中的验证码与用户输入是否一致（这里忽略了大小写）
	        if(verifyCode!=null&&verifyCode.equalsIgnoreCase(legalCode)){
	            next="true";
	        }else{
	            next="false";
			}
//    	}else{
//    		next="true";
//    	}
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(next);
        out.flush();
        out.close();
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

