package KW.Test.User.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import cn.itcast.commons.CommonUtils;
import KW.Test.User.domain.User;
import KW.Test.User.service.UserException;
import KW.Test.User.service.UserService;
import KW.utils.toBean.toBean;

@WebServlet("/RegisteServlet")
public class RegisteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService userservice=new UserService();
		//一句话封装表单
		//已将数据封装完了
		//User from=CommonUtils.toBean(request.getParameterMap(), User.class);
		User from=toBean.toBean(request.getParameterMap(), User.class);
		/*
		 * 调用UserService的regist()方法
		 * 得到异常，保存到request中
		 * 没有异常，注册成功
		 */
		// 用来装载所有错误信息
		/*Map<String,String> errors = new HashMap<String,String>();
		
		// 对用户名进行校验
		String username = form.getUsername();//获取表单的username
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 3 || username.length() > 15) {
			errors.put("username", "用户名长度必须在3~15之间！");
		}
		
		// 对密码进行校验
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 3 || password.length() > 15) {
			errors.put("password", "密码长度必须在3~15之间！");
		}
		
		
		// 对验证码进行校验
		String sessionVerifyCode = (String) request.getSession().getAttribute("session_vcode");
		String verifyCode = form.getVerifyCode();
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if(verifyCode.length() != 4) {
			errors.put("verifyCode", "验证码长度必须为4！");
		} else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		
		
		 * 判断map是否为空，不为空，说明存在错误
		 
		if(errors != null && errors.size() > 0) {
			
			 * 1. 保存errors到request域
			 * 2. 保存form到request域，为了回显
			 * 3. 转发到regist.jsp
			 
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		*/
		/*
		 * 添加表单效验
		 * 1，创建一个Map，用来封装表单错误信息】】
		 * 2.有错的时候就将错误信息添加，其中key为表单字段
		 */
		
		Map<String,String> errors=new HashMap<String,String >();
		String username=from.getName();
		if(username==null||username.trim().isEmpty())
		{
			errors.put("name", "用户名不能为空");
		}
		else if(username.length()<3||username.length()>15)
		{
			errors.put("name", "用户名的长度为3~15之间");
		}
		String pass=from.getPass();
		if(pass==null||pass.trim().isEmpty())
		{
			errors.put("pass", "密码不能为空");
		}
		else if(pass.length()<3||pass.length()>15)
		{
			errors.put("pass", "密码的长度为3~15之间");
		}
		
		
		if(errors!=null&&errors.size()>0)
		{
			request.setAttribute("errors", errors);
			request.setAttribute("form", from);
			request.getRequestDispatcher("/user/registe.jsp").forward(request, response);
			return;
		}
		try{
			userservice.registe(from);
			request.setAttribute("name", from.getName());
			request.setAttribute("pass", from.getPass());
				response.getWriter().print("<h1>注册成功</h1>"
						+ "<a href='"+request.getContextPath()+"/user/Login.jsp'>点击登录</a>");
	
		}catch(UserException e)
		{
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/user/registe.jsp").forward(request, response);
		}
	}
}
