package me.ifma.activitybar.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.service.UserService;
import me.ifma.activitybar.weixin.utils.WxUtils;
import net.sf.json.JSONObject;
@Component("WxFilter")
public class WxFilter extends HttpFilter {
@Autowired(required=true)
UserService userService;
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Object u = request.getSession().getAttribute("user");
		if(u==null){
			String code = request.getParameter("code");
			if(code!=null){
				//调用方法能过code获取jsonObject
				JSONObject jsonObject = WxUtils.getAccessTokenOpenId(code);
				String openid = (String) jsonObject.get("openid");
				User user = userService.getByOpenId(openid);
				if(user!=null){
					request.getSession().setAttribute("user", user);
				}
				chain.doFilter(request, response);
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

}
