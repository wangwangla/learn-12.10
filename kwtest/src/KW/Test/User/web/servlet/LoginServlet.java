package KW.Test.User.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KW.Test.User.domain.User;
import KW.Test.User.service.UserException;
import KW.Test.User.service.UserService;
import KW.utils.toBean.toBean;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService userservice=new UserService();
		/*
		 * 封装表单数据
		 * 调用service的login方法。得到返回值
		 * 	>如果有异常，就获取异常信息，保存到request域中，在保存到from，，转发到login
		 *  >如果没有就保存session中，去welcome
		 */
		User form=toBean.toBean(request.getParameterMap(), User.class);
		try
		
		{
			User user=userservice.login(form);
			request.getSession().setAttribute("sessionUser", user);
			request.getSession().setMaxInactiveInterval(1);
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
		}
		catch(UserException e)
		{
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("User", form);
			request.getRequestDispatcher("/user/Login.jsp").forward(request, response);		
		}
	}

}
