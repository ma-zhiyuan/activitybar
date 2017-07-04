package me.ifma.activitybar.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.entity.Admin;
import me.ifma.activitybar.entity.Card;
import me.ifma.activitybar.service.ActivityService;
import me.ifma.activitybar.service.AdminService;
import me.ifma.activitybar.service.CardService;
import me.ifma.activitybar.util.MyUtils;

@RequestMapping("/admin")
@Controller
public class AdminHandler {
	@Autowired
	AdminService adminService;
	@Autowired
	ActivityService activityService;
	@Autowired
	CardService cardService;
	
	@RequestMapping("/login")
	public String login(String name,String pwd,Map<String,Object> map){
		pwd = MyUtils.md5(pwd);
		Admin admin = adminService.getByNamePwd(name, pwd);
		if(admin!=null){
			List<Activity> activitys = activityService.getAdminAll();
			map.put("activitys", activitys);
			return "view/admin_listAll";
		}else{
			return "forward:tologin";
		}
	}
	
	@RequestMapping("/tologin")
	public void tologin(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("/admin.jsp").forward(request, response);
	}

	@RequestMapping("/adminAll")
	public String adminAll(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
		List<Activity> activitys = activityService.getAdminAll();
		map.put("activitys", activitys);
		return "view/admin_listAll";
	}
	
	@RequestMapping("/addCards")
	public void addCards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/ms-download");
		response.setHeader("Content-disposition", "attachment;filename=cards.txt");
		String number = request.getParameter("number");
		String balance = request.getParameter("balance");
		List<Card> cards = cardService.add(Integer.parseInt(number),Double.parseDouble(balance));
		ServletOutputStream os = response.getOutputStream();
		for(Card c:cards){
			String str = "会员卡号："+c.getId()+"\n";
			os.write(str.getBytes());
		}
		os.close();
	}
}
