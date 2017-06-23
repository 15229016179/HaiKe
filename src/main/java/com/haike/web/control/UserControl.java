package com.haike.web.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haike.web.entity.UserInfo;
import com.haike.web.service.UserService;
import com.haike.web.util.ConnectionFactory;

/**
 * @author xiaoming
 *
 */
public class UserControl extends HttpServlet {
	
	UserService userService = new UserService();

	public UserControl(){
		super();
	}
	
	public void destory(){
		super.destroy();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		
		String userName = req.getParameter("userName");
		String passWord = req.getParameter("passWord");
		UserInfo user = new UserInfo();
		user.setUsername(userName);
		user.setPassword(passWord);
		
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection conn = connectionFactory.getConnection();
		try {
			if(userService.checkUser(conn, user)){
				System.out.println("userServlet.doPost()"+"登陆成功");
				RequestDispatcher rd = req.getRequestDispatcher("/login/welcome.jsp");
				req.setAttribute("userName", userName);
				req.setAttribute("passWord", passWord);
				req.setAttribute("login", "1");
				rd.forward(req, resp);
			}else{
				System.out.println("userServlet.doPost()"+"登陆失败");
				RequestDispatcher rd = req.getRequestDispatcher("/login/welcome.jsp");
				req.setAttribute("userName", userName);
				req.setAttribute("passWord", passWord);
				rd.forward(req, resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
