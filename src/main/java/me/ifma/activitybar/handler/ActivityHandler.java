package me.ifma.activitybar.handler;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.ifma.activitybar.bean.Cretiera;
import me.ifma.activitybar.bean.Page;
import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.service.ActivityService;
import me.ifma.activitybar.service.ItemService;
import me.ifma.activitybar.service.UserService;

@RequestMapping("/activity")
@Controller
public class ActivityHandler {
	@Autowired
	ActivityService activityService;
	@Autowired
	UserService userService;
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/adds")
	public void adds(){
		List l = new ArrayList();
		Activity a = new Activity("asd");
		Activity b = new Activity("asd");
		Activity c = new Activity("asd");
		l.add(a);
		l.add(b);
		l.add(c);
		activityService.adds(l);
		System.out.println(l);
	}
	
	@RequestMapping("/getAll")
	public String getAll(HttpServletRequest request) throws UnsupportedEncodingException{
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		String minprice = request.getParameter("minprice") == null ? "0" : request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice") == null ? "" + 999999 : request.getParameter("maxprice");
		String orderby = request.getParameter("orderby") == null ? "1" : request.getParameter("orderby");
		String pageNo = request.getParameter("pageNo") == null ? "1" : request.getParameter("pageNo");
		minprice = minprice.equals("") ? "0" : minprice;
		maxprice = maxprice.equals("") ? "999999" : maxprice;
		pageNo = pageNo.equals("") ? "1" : pageNo;
		Cretiera c = new Cretiera(keyword, Double.parseDouble(minprice), Double.parseDouble(maxprice),
				Integer.parseInt(pageNo),9,Integer.parseInt(orderby));
		Page<Activity> activitys = activityService.getAll(c);
		request.setAttribute("activitys", activitys);
		return "view/listAll";
	}
	
	@RequestMapping("/detail")
	public String detail(int id,Map<String,Object> map){
		Activity activity = activityService.getById(id);
		List users = activityService.getUsersByActivityId(id);
		map.put("users", users);
		map.put("activity",activity);
		return "view/detail";
	}
	
	@RequestMapping("/delete")
	public String delete(int id,Map<String,Object> map){
		itemService.delete(id);
		activityService.delete(id);
		List<Activity> activitys = activityService.getAdminAll();
		map.put("activitys", activitys);
		return "view/admin_listAll";
	}
	
	@RequestMapping("/to_update")
	public String to_update(int id,Map<String,Object> map){
		Activity activity = activityService.getById(id);
		User creator = userService.getById(activity.getCreator());
		map.put("creator",creator);
		map.put("activity", activity);
		return "view/addActivity";
	}
	
	@RequestMapping("/addActivity")
	public String addActivity(HttpServletRequest request, HttpServletResponse response,Map<String,Object> map) throws Exception{
		request.setCharacterEncoding("utf-8");
		ServletInputStream is = request.getInputStream();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			String dir = null;
			String fileName = null;
			String id = null;
			String name = null;
			String creator = null;
			int creatorInt = 1;
			String dateStr = null;
			String surface = null;
			String place = null;
			String personnum = null;
			String cost = null;
			String contact = null;
			String detail = null;
			String path = null;
			// 遍历FileItem的集合
			for (FileItem fi : items) {
				// 判断是一个普通的表单还是一个文件
				// 是一个普通的表单
				if (fi.isFormField()) {
					String fieldName = fi.getFieldName();
					if (fieldName.equals("name")) {
						name = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("creator")) {
						creator = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
						creatorInt  = userService.getIdByName(creator);
					} else if (fieldName.equals("date")) {
						dateStr = fi.getString();
					} else if (fieldName.equals("place")) {
						place = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("personnum")) {
						personnum = fi.getString();
					} else if (fieldName.equals("cost")) {
						cost = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("contact")) {
						contact = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("id")) {
						id = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					}else if (fieldName.equals("detail")) {
						detail = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					}
					// 是一个文件
				} else {
					// 获取输入流
					InputStream is2 = fi.getInputStream();
					// 获取文件名
					fileName = fi.getName();
					// 目标文件夹
					dir = "pic";
					// 找到当前项目的webRoot文件夹
					String rep = request.getServletContext().getRealPath("/" + dir);
					// 解决文件名冲突的问题
					fileName = System.currentTimeMillis() + (int) (Math.random() * 1000) + fileName;
					// 输出流的全路径，包括文件名。
					path = rep + "/" + fileName;
					// 输出流
					OutputStream os = new FileOutputStream(path);
					byte[] b = new byte[1024 * 1024 * 10];
					int len = 0;
					while ((len = is2.read(b)) != -1) {
						os.write(b, 0, len);
					}
					os.close();
					is2.close();
				}
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			if("".equals(dateStr)){
				date= new Date();
			}else{
				date = format.parse(dateStr);
			}
			// 新增
			if (id == null) {
				Activity activity = new Activity(null,name,date,dir + "/" + fileName,place,Integer.parseInt(personnum),Double.parseDouble(cost),contact,detail,creatorInt);
				// 往数据库中插入数据
				activityService.add(activity);
				// 更新
			} else {
				Activity activity = new Activity(Integer.parseInt(id),name,date,dir + "/" + fileName,place,Integer.parseInt(personnum),Double.parseDouble(cost),contact,detail,creatorInt);
				activityService.update(activity);
			}
			List<Activity> activitys = activityService.getAdminAll();
			map.put("activitys", activitys);
			return "view/admin_listAll";
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		is.close();
		return "view/admin_listAll";
	}
}	
