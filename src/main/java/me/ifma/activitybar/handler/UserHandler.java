package me.ifma.activitybar.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.entity.Item;
import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.service.ActivityService;
import me.ifma.activitybar.service.CardService;
import me.ifma.activitybar.service.ItemService;
import me.ifma.activitybar.service.UserService;
import me.ifma.activitybar.util.MyUtils;

@RequestMapping("/user")
@Controller
public class UserHandler {
	@Autowired
	UserService userService;
	@Autowired
	ItemService itemService;
	@Autowired
	ActivityService activityService;
	@Autowired
	CardService cardService;

	@RequestMapping("/login")
	public String login(String name, String password, HttpServletRequest request) {
		password = MyUtils.md5(password);
		User u = userService.get(name, password);
		if (u != null) {
			request.getSession().setAttribute("user", u);
			return "success";
		} else {
			return "fail";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Map<String, Object> map) {
		request.getSession().removeAttribute("user");
		String msg = "退出成功！";
		map.put("msg", msg);
		return "msg";
	}

	@RequestMapping("/showMyActivity")
	public String showMyActivity(HttpServletRequest request) {
		User u = (User) request.getSession().getAttribute("user");
		u.setItems(new ArrayList<Activity>());
		List<Activity> activityItem = u.getItems();
		List<Item> items = itemService.getByUserid(u.getId());
		for (int i = 0; i < items.size(); i++) {
			Integer activityid = items.get(i).getActivityid();
			Activity activity = activityService.getById(activityid);
			for (int j = 0; j < activityItem.size(); j++) {
				if (activity.equals(activityItem.get(j))) {
					break;
				}
			}
			activityItem.add(activity);
		}
		return "view/myActivity";
	}

	@RequestMapping("/verifyName")
	public void verifyName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		boolean isRegisted = userService.verifyName(name);
		String str = "网络异常，请稍等···";
		if (isRegisted) {
			str = "用户名已占用。";
		} else {
			str = "";
		}
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}
	@RequestMapping("/verifyCreatorName")
	public void verifyCreatorName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		boolean isRegisted = userService.verifyName(name);
		System.out.println(isRegisted);
		String str = "网络异常，请稍等···";
		if (isRegisted) {
			str = "";
		} else {
			str = "用户不存在";
		}
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}

	@RequestMapping("/regist")
	public String regist(HttpServletRequest request) {
		String name = request.getParameter("regname");
		String pwd = request.getParameter("regpassword");
		// 加密
		pwd = MyUtils.md5(pwd);
		// 判断用户输入的是否是邮箱，如果是邮箱则用邮箱注册；如果是用户名的话就用用户名注册
		String reg = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
		boolean flag = name.matches(reg);
		// 是一个邮箱
		if (flag) {
			String emailStr = name;
			// 发邮件
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.163.com");
			email.setSSLOnConnect(true);
			email.setSmtpPort(465);
			// 设置用户
			email.setAuthentication("maazhiyuan", "mazhiyuan1202");
			try {
				email.setFrom("maazhiyuan@163.com", "马志远", "utf-8");
				email.addTo(emailStr);
				email.setSubject("邮箱确认");
				// 获取验证码
				String cap = String.valueOf(MyUtils.getRandom());
				HttpSession session = request.getSession();
				session.setAttribute("capcha", cap);
				session.setAttribute("pwd", pwd);
				session.setAttribute("email", emailStr);
				String content = "验证码：" + cap;
				email.setMsg(content);
				email.send();
				// 跳到指定页面，输入验证码
				return "view/confirmCapcha";
			} catch (EmailException e) {
				e.printStackTrace();
			}
		} else {
			User u = userService.regist(name, pwd);
			if (u != null) {
				request.getSession().setAttribute("user", u);
				return "success";
			} else {
				return "fail";
			}
		}
		return "fail";
	}

	@RequestMapping("/confirmEmail")
	public String confirmEmail(HttpServletRequest request) {
		String capcha = request.getParameter("capcha");
		Object attribute = request.getSession().getAttribute("capcha");
		String cap = (String) attribute;
		if (capcha.equals(cap)) {
			String pwd = (String) request.getSession().getAttribute("pwd");
			String email = (String) request.getSession().getAttribute("email");
			User u  = userService.registByEmail(email, pwd);
			if (u!=null) {
				request.getSession().setAttribute("user", u);
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}
	
	@RequestMapping("/modifyInfo")
	public String modifyInfo(HttpServletRequest request){
		User u = (User)(request.getSession().getAttribute("user"));
		String gender =	((String) request.getParameter("gender"));
		Integer genderInt = 0;
		if("女".equals(gender)){
			genderInt = 0;
		}else{
			genderInt = 1;
		}
		Integer age = Integer.valueOf((String)request.getParameter("age"));
		u.setGender(genderInt);
		u.setAge(age);
		userService.update(u);
		String msg = "更新个人信息成功！";
		request.setAttribute("msg", msg);
		return "msg";
	}
	
	@RequestMapping("/modifypwd")
	public String modifypwd(HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("user");
		String oldpwd = request.getParameter("oldpwd");
		String pwd = request.getParameter("password");
		String pwd2 = request.getParameter("password2");
		oldpwd = MyUtils.md5(oldpwd);
		pwd = MyUtils.md5(pwd);
		pwd2 = MyUtils.md5(pwd2);
		if(!oldpwd.equals(u.getPassword())){
			request.setAttribute("msg", "原密码不正确。");
			return "msg";
		}
		if(!pwd.equals(pwd2)){
			request.setAttribute("msg", "两次密码不一致。");
			return "msg";
		}
		u.setPassword(pwd);
		userService.update(u);
		request.setAttribute("msg", "修改成功");
		return "msg";
	}
	
	@RequestMapping("/recharge")
	public String recharge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		String cardid = request.getParameter("cardid");
		String result = cardService.recharge(cardid,u);
		request.setAttribute("msg", result);
		return "msg";
	}
}
