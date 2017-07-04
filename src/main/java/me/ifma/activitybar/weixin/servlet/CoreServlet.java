package me.ifma.activitybar.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ifma.activitybar.service.WxService;
import me.ifma.activitybar.util.MyUtils;
import me.ifma.activitybar.weixin.utils.WxUtils;


public class CoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915889559605864605L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("准备接入");
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if(WxUtils.verify(signature, timestamp, nonce)){
			out.write(echostr);
			System.out.println("接入成功");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletInputStream is = request.getInputStream();
		//获取消息的Map
		Map<String, String> msgMap = WxUtils.parseMsg(is);
		String respMsg = null;
			respMsg = new WxService().getResponseMsg(msgMap);
			System.out.println(respMsg);
		out.write(respMsg);
		out.flush();
		out.close();
	}

}
