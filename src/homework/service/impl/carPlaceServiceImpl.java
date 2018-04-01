package homework.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import homework.dao.carPlaceDao;
import homework.model.carPlace;
import homework.model.PageBean;
import homework.service.carPlaceService;

@Service("carPlaceService")
public class carPlaceServiceImpl implements carPlaceService{

	@Resource
	private carPlaceDao carPlaceDao;
	
	@Override
	public List<carPlace> find(PageBean pageBean, carPlace carPlace) {
		return carPlaceDao.find(pageBean, carPlace);
	}

	@Override
	public int count(carPlace carPlace) {
		return carPlaceDao.count(carPlace);
	}

	@Override
	public void add(carPlace carPlace) {
		carPlaceDao.add(carPlace);
	}

	@Override
	public void update(carPlace carPlace) {
		carPlaceDao.update(carPlace);
	}

	@Override
	public void delete(int id) {
		carPlaceDao.delete(id);
	}

	@Override
	public carPlace loadById(int id) {
		return carPlaceDao.loadById(id);
	}

}
