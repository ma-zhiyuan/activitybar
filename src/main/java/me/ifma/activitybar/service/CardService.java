package me.ifma.activitybar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ifma.activitybar.dao.CardDao;
import me.ifma.activitybar.entity.Card;
import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.util.MyUtils;

@Service
public class CardService {

	@Autowired
	CardDao cardDao;
	@Autowired
	UserService userService;
	
	public String recharge(String cardid,User u) {
		String str = "充值成功。";
		//获取会员卡
		Card card =cardDao.getById(cardid);
		
		if(card==null){
			return "会员卡不存在";
		}
		if(card.getIsUsed()==Card.ISUSED_TRUE){
			return "该会卡已失效";
		}
		//充值
		u.setBalance(u.getBalance()+card.getBalance());
		card.setBalance(0);
		card.setTime(new Date());
		card.setIsUsed(Card.ISUSED_TRUE);
		card.setUser(u);
		
		userService.update(u);
		this.update(card);
		return str;
	}

	public void update(Card card) {
		cardDao.update(card);
	}

	public List<Card> add(int num, double balance) {
		List<Card> cards = new ArrayList<Card>();
		Card card = null;
		for(int i=0;i<num;i++){
			String id = MyUtils.md5((int)(Math.random()*1000)+num+balance+new Date().toString());
			card = new Card(id, balance, new Date(), Card.ISUSED_FALSE, null);
			cardDao.add(card);
			cards.add(card);
		}
		return cards;
	}

}
