package me.ifma.activitybar.mapper;

import me.ifma.activitybar.entity.Card;

public interface CardMapper {

	void add(Card card);

	Card getById(String cardid);

	void update(Card card);

}
