package homework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import homework.dao.carDao;
import homework.model.car;
import homework.model.PageBean;
import homework.service.carService;

@Service("carService")
public class carServiceImpl implements carService{

	@Resource
	private carDao carDao;
	
	
	@Override
	public List<car> find(PageBean pageBean, car car) {
		return carDao.find(pageBean, car);
	}

	@Override
	public int count(car car) {
		return carDao.count(car);
	}

	@Override
	public void delete(int id) {
		carDao.delete(id);
	}

	@Override
	public void add(car car) {
		carDao.add(car);
	}
	
	
	public boolean existPlaceByTypeId(car car){
		return carDao.existPlaceByTypeId(car);
	}

	@Override
	public void update(car car) {
		carDao.update(car);
	}

	@Override
	public car loadById(int id) {
		return carDao.loadById(id);
	}

	@Override
	public boolean existEquipmentByTypeId(int typeId) {
		return carDao.existEquipmentByTypeId(typeId);
	}

	



	@Override
	public void giveFee(int id) {
		// TODO Auto-generated method stub
		carDao.giveFee(id);
	}



}
