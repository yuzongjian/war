package homework.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import homework.dao.UserDao;
import homework.model.PageBean;
import homework.model.User;
import homework.util.StringUtil;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User login(User user) {
		String sql="select * from t_user where userName=? and password=?";
		final User resultUser=new User();
		jdbcTemplate.query(sql, new Object[]{user.getUserName(),user.getPassword()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultUser.setId(rs.getInt("id"));
				resultUser.setUserName(rs.getString("userName"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setRoleName(rs.getString("roleName"));
			}
		});
		return resultUser;
	}

	@Override
	public List<User> find(PageBean pageBean, User s_user) {
		StringBuffer sb=new StringBuffer("select * from t_user ");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				sb.append(" where userName like '%"+s_user.getUserName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<User> userList=new ArrayList<User>();
		jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user=new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setTrueName(rs.getString("trueName"));
				user.setRoleName(rs.getString("roleName"));
				userList.add(user);
			}
		});
		return userList;
	}

	@Override
	public int count(User s_user) {
		StringBuffer sb=new StringBuffer("select count(*) from t_user");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				sb.append(" where userName like '%"+s_user.getUserName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
	}

	@Override
	public void delete(int id) {
		String sql="delete from t_user where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public void add(User user) {
		String sql="insert into t_user values(null,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),user.getTrueName(),user.getRoleName()});
	}

	@Override
	public void update(User user) {
		String sql="update t_user set userName=?,password=?,trueName=?,roleName=? where id=?";
		jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),user.getTrueName(),user.getRoleName(),user.getId()});
	}

	@Override
	public User loadById(int id) {
		String sql="select * from t_user where id=?";
		
		final User user=new User();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setTrueName(rs.getString("trueName"));
				user.setRoleName(rs.getString("roleName"));
			}
		});
		return user;
	}

	

}
