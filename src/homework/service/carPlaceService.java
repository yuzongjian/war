package homework.service;

import java.util.List;

import homework.model.carPlace;
import homework.model.PageBean;

public interface carPlaceService {

	public List<carPlace> find(PageBean pageBean,carPlace carPlace);
	
	public int count(carPlace carPlace);
	
	public void add(carPlace carPlace);
	
	public void update(carPlace carPlace);
	
	public void delete(int id);
	
	public carPlace loadById(int id);
}
