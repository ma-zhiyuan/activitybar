package me.ifma.activitybar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.ifma.activitybar.entity.Card;
import me.ifma.activitybar.mapper.CardMapper;
@Repository
public class CardDao {
	@Autowired
	CardMapper cardMapper;
	
	public void add(Card card){
		cardMapper.add(card);
	}

	public Card getById(String cardid) {
		Card card  = cardMapper.getById(cardid);
		return card;
	}

	public void update(Card card) {
		cardMapper.update(card);
	}
	
}
