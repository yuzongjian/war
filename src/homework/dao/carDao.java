package homework.dao;

import java.util.List;

import homework.model.car;
import homework.model.PageBean;

public interface carDao {

	public List<car> find(PageBean pageBean,car car);
	
	public int count(car car);
	
	public void delete(int id);
	
	public void giveFee(int id);
	
	public void add(car car);
	
	public void update(car car);
	
	public car loadById(int id);
	
	public boolean existEquipmentByTypeId(int typeId);
	
	public boolean existPlaceByTypeId(car car);
	
}
