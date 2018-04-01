package homework.service;

import java.util.List;

import homework.model.PageBean;
import homework.model.User;

public interface UserService {

	public User login(User user);
	
	public List<User> find(PageBean pageBean,User s_user);
	
	public int count(User s_user);
	
	public void delete(int id);
	
	public void add(User user);
	
	public void update(User user);
	
	public User loadById(int id);
	
}
