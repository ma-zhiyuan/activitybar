package me.ifma.activitybar.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import me.ifma.activitybar.bean.Page;
import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.entity.Item;
import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.util.MyUtils;
import me.ifma.activitybar.weixin.entity.Article;
import me.ifma.activitybar.weixin.entity.NewsMessage;
import me.ifma.activitybar.weixin.entity.TextMessage;
import me.ifma.activitybar.weixin.utils.WxUtils;
@Service
public class WxService {
	@Autowired
	ActivityService activityService;
	@Autowired
	UserService userService;
	@Autowired
	ItemService itemService;
	public String getResponseMsg(Map<String, String> msgMap) {
		//获取消息的类型
		String msgType = msgMap.get("MsgType");
		String toUserName = msgMap.get("ToUserName");
		String fromUserName  = msgMap.get("FromUserName");
		String createTime = System.currentTimeMillis()/1000+"";
		
		//这是一个文本消息
		if(msgType.equals("text")){
			//获取用户输入的内容
			String content = msgMap.get("Content");
			if(WxUtils.mode.get(fromUserName)!=null&&WxUtils.mode.get(fromUserName)==WxUtils.MODE_BIND){
				if(content.equals("e")){
					WxUtils.mode.remove(fromUserName);
					TextMessage tm = new TextMessage();
					String respMsg="您已退出用户绑定模式。";
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}
				if(!content.contains("#")){
					TextMessage tm = new TextMessage();
					String respMsg="格式不正确，请重新输入：用户名#密码，退出回复e";
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}
				String name = content.substring(0, content.indexOf("#"));
				String pwd = content.substring(content.indexOf("#")+1);
				//调用User的业务方法来绑定账号
				int i = userService.toBind(name,MyUtils.md5(pwd),fromUserName);
				TextMessage tm = new TextMessage();
				String respMsg=null;
				switch(i){
					case 0:
						respMsg="绑定成功。";
						WxUtils.mode.remove(fromUserName);
						break;
					case 1:
						respMsg="用户名不存在，请重新输入，回复e退出。";
						break;
					case 2:
						respMsg="密码不正确，请重新输入，回复e退出。";
						break;
					case 3:
						respMsg="您的微信号已绑定其它账号，回复e退出。";
						break;
					case 4:
						respMsg="您输入的账号已被其它微信号绑定，回复e退出。";
						break;
					default:
						respMsg="网络异常，请重试。";
				}
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}
			System.out.println(activityService);
			System.out.println(userService);
			System.out.println(itemService);
			Page<Activity> page = activityService.getWeiXinBook(content);
			List<Activity> books = page.getItems();
			//创建个Articles的List
			if(books.size()>0){
				//创建一个图文消息
				NewsMessage nm = new NewsMessage();
				nm.setCreateTime(createTime);
				nm.setFromUserName(toUserName);
				nm.setToUserName(fromUserName);
				List<Article> articles = new ArrayList<Article>();
				//使用for循环来为图文消息设置内容,一本书是一个Article对象，
				for(Activity b:books){
					Article a = new Article();
					a.setDescription(b.getDetail());
					a.setTitle(b.getName());
					a.setPicUrl(WxUtils.getBasePath(b.getSurface()));
					System.out.println(WxUtils.getSnsapiBaseUrl(WxUtils.getUrl(b)));
					a.setUrl(WxUtils.getSnsapiBaseUrl(WxUtils.getUrl(b)));
					articles.add(a);
				}
				nm.setArticles(articles);
				String respXml = WxUtils.convertToXml(nm);
				return respXml;
			}else{
				//没书，就直接回复文本消息就可以了。
				TextMessage tm = new TextMessage();
				String respMsg="查无此活动";
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}
		}else if(msgType.equals("location")){
			//调用其它业务方法获取返回的消息
			String respMsg = "您当前在："+msgMap.get("Label")+"\n"+
					"坐标：x "+msgMap.get("Location_X")+",y "+msgMap.get("Location_Y");
			//回复的消息
			TextMessage tm = new TextMessage();
			tm.setContent(respMsg);
			tm.setCreateTime(createTime);
			tm.setFromUserName(toUserName);
			tm.setToUserName(fromUserName);
			String respXml = WxUtils.convertToXml(tm);
			return respXml;
		}else if(msgType.equals("event")){
			String event = msgMap.get("Event");
			if(event.equals("subscribe")){
				String respMsg = "欢迎关注我的测试号。。。";
				TextMessage tm = new TextMessage();
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}else if(event.equals("unsubscribe")){
				//取消关注时回复的消息
			}else if(event.equals("LOCATION")){
				String respMsg = "位置信息已收到";
				TextMessage tm = new TextMessage();
				tm.setContent(respMsg);
				tm.setCreateTime(createTime);
				tm.setFromUserName(toUserName);
				tm.setToUserName(fromUserName);
				String respXml = WxUtils.convertToXml(tm);
				return respXml;
			}else if(event.equals("CLICK")){
				String eventKey = msgMap.get("EventKey");
				//用户绑定账号
				if(eventKey.equals("11")){
					WxUtils.mode.put(fromUserName, WxUtils.MODE_BIND);
					String respMsg = "请回复用户名和密码，格式：用户名#密码,退出绑定回复e。";
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}else if(eventKey.equals("12")){
					//调用业务方法获取余额
					User u = userService.getByOpenId(fromUserName);
					System.out.println(fromUserName);
					System.out.println(u+"----------");
					String respMsg = null;
					if(u==null){
						respMsg="您还没有绑定账号，请先绑定账号。";
					}else{
						respMsg = "您的余额："+u.getBalance()+"元";
					}
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}else if(eventKey.equals("13")){
					User u = userService.getByOpenId(fromUserName);
					String respMsg = null;
					StringBuffer name = new StringBuffer();
					if(u==null){
						respMsg="您还没有绑定账号，请先绑定账号。";
					}else{
						List<Item> items =itemService.getByUserid(u.getId());
						for (Item item : items) {
							Activity activity = activityService.getById(item.getActivityid());
							String format = new SimpleDateFormat("yyyy-MM-dd").format(activity.getDate());
							name.append("    "+format+"|"+activity.getName()+"\n");
						}
						respMsg = "您参加的活动有：\n"+name;
					}
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}else if(eventKey.equals("23")){
					String respMsg = "感谢您的认可！";
					TextMessage tm = new TextMessage();
					tm.setContent(respMsg);
					tm.setCreateTime(createTime);
					tm.setFromUserName(toUserName);
					tm.setToUserName(fromUserName);
					String respXml = WxUtils.convertToXml(tm);
					return respXml;
				}
			}
		}
		return null;
	}

}
