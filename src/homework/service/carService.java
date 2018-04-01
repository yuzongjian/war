package homework.service;

import java.util.List;

import homework.model.car;
import homework.model.PageBean;

public interface carService {

	public List<car> find(PageBean pageBean,car s_equipment);
	
	public int count(car s_equipment);
	
	public void delete(int id);
	
	public void add(car equipment);
	
	public boolean existPlaceByTypeId(car equipment);
	
	public void update(car equipment);
	
	public void giveFee(int id);
	
	public car loadById(int id);
	
	public boolean existEquipmentByTypeId(int typeId);
	
	
	
}
