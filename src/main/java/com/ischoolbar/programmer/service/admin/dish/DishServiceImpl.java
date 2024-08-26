package com.ischoolbar.programmer.service.admin.dish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.dish.DishMapper;
import com.ischoolbar.programmer.entity.admin.Dish;

@Service
public class DishServiceImpl implements DishService {
	@Autowired
	private DishMapper dishMapper;
	@Override
	public List<Dish> getDish() {
		return dishMapper.getDish();
	}

	@Override
	public Dish getDishById(String id) {
		
		return dishMapper.getDishById(id);
	}

	@Override
	public boolean addDish(Dish dish) {
		if(dishMapper.addDish(dish)==0){
			return  false;
		}
		return true;
	}

	@Override
	public boolean isExist(String dishName) {
		if(dishMapper.isExist(dishName)==0){
			return  false;
		}
		return true;
	}

	@Override
	public boolean delDishById(String id) {
		if(dishMapper.delDishById(id)==0){
			return  false;
		}
		return true;
	}
}
