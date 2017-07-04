package me.ifma.activitybar.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.ifma.activitybar.service.WxService;
import me.ifma.activitybar.weixin.utils.WxUtils;

@RequestMapping("/weixin")
@Controller
public class WeiXinHandler{
	@Autowired
	WxService wxservice;
	
	@RequestMapping(value="/coreServlet",method=RequestMethod.POST)
	public void coreServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletInputStream is = request.getInputStream();
		//获取消息的Map
		Map<String, String> msgMap = WxUtils.parseMsg(is);
		String respMsg = null;
		respMsg = wxservice.getResponseMsg(msgMap);
		System.out.println(respMsg);
		out.write(respMsg);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/coreServlet",method=RequestMethod.GET)
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
}
