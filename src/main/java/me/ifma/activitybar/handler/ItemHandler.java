package me.ifma.activitybar.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.entity.Item;
import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.service.ActivityService;
import me.ifma.activitybar.service.ItemService;
import me.ifma.activitybar.service.UserService;

@RequestMapping("/item")
@Controller
public class ItemHandler {
	@Autowired
	ItemService itemService;
	@Autowired
	ActivityService activityService;
	@Autowired
	UserService userService;
	@RequestMapping("/add")
	public String add(int id,Map<String,Object>map,HttpServletRequest request){
		String msg = "";
		User u = (User)request.getSession().getAttribute("user");
		if(u == null){
			msg = "您还未登陆，不能参加活动！";
			map.put("msg", msg);
			return "msg"；
		}
		double cost = activityService.getById(id).getCost();
		if(itemService.get(u.getId(), id)!=null){
			msg = "您已参加该活动，不能重复参加！";
		}else{
			if(itemService.getCount(id)>=activityService.getById(id).getPersonnum()){
				msg = "该活动参加人数已满，不能继续参加，请选择其他活动！";
			}else{
				if(u.getBalance()>=cost){
					u.setBalance(u.getBalance()-cost);
					userService.update(u);
					User byId = userService.getById(activityService.getById(id).getCreator());
					byId.setBalance(byId.getBalance()+cost);
					userService.update(byId);
					itemService.add(u.getId(), id);
					Activity activity = activityService.getById(id);
					u.getItems().add(activity);
					msg = "报名活动成功，请按时前往指定地点参加活动～";
				}else{
					msg="您的余额不足，请前往个人中心及时充值";
				}
			}
		}
		map.put("msg", msg);
		return "msg";
	}
}
