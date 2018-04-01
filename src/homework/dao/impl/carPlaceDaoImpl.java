package homework.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import homework.dao.carPlaceDao;
import homework.model.carPlace;
import homework.model.PageBean;
import homework.util.StringUtil;

@Repository("carPlaceDao")
public class carPlaceDaoImpl implements carPlaceDao{
	@Resource
	private JdbcTemplate jdbcTemplate;	
	@Override
	public List<carPlace> find(PageBean pageBean, carPlace carPlace) {
		StringBuffer sb=new StringBuffer("select * from carPlace");
		if(carPlace!=null){
			if(StringUtil.isNotEmpty(carPlace.getTypeName())){
				sb.append(" and typeName like '%"+carPlace.getTypeName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<carPlace> carPlaceList=new ArrayList<carPlace>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				carPlace carPlace=new carPlace();
				carPlace.setId(rs.getInt("id"));
				carPlace.setTypeName(rs.getString("typename"));
				carPlace.setNumber(rs.getInt("number"));
				carPlace.setShiyong(rs.getInt("shiyong"));
				int kongwei=rs.getInt("number")-rs.getInt("shiyong");
				carPlace.setKongwei(kongwei);
				if(kongwei>0){
					carPlace.setNumStatus(1);
				}
				else{
					carPlace.setNumStatus(2);
				}
				carPlaceList.add(carPlace);
			}
		});
		return carPlaceList;
	}

	@Override
	public int count(carPlace carPlace) {
		StringBuffer sb=new StringBuffer("select count(*) from carPlace");
		if(carPlace!=null){
			if(StringUtil.isNotEmpty(carPlace.getTypeName())){
				sb.append(" and typeName like '%"+carPlace.getTypeName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public void add(carPlace carPlace) {
		String sql="insert into carPlace values(null,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{carPlace.getTypeName(),0,carPlace.getNumber()});
	}

	@Override
	public void update(carPlace carPlace) {
		String sql="update carPlace set typeName=? where id=?";
		jdbcTemplate.update(sql, new Object[]{carPlace.getTypeName(),carPlace.getId()});
	}

	@Override
	public void delete(int id) {
		String sql="delete from carPlace where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public carPlace loadById(int id) {
		String sql="select * from carPlace where id=?";
		final carPlace carPlace=new carPlace();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				carPlace.setId(rs.getInt("id"));
				carPlace.setTypeName(rs.getString("typeName"));
				carPlace.setNumber(rs.getInt("number"));
			}
		});
		return carPlace;
	}

	

}
