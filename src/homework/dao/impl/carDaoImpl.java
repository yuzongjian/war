package homework.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestScope;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import homework.dao.carDao;
import homework.model.car;
import homework.model.carPlace;
import homework.model.PageBean;
import homework.util.StringUtil;

@Repository("carDao")
public class carDaoImpl implements carDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<car> find(PageBean pageBean, car car) {
		StringBuffer sb=new StringBuffer("select * from car t1,carPlace t2 where t1.typeId=t2.id");
		if(car!=null){
			if(StringUtil.isNotEmpty(car.getName())){
				sb.append(" and t1.name like '%"+car.getName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<car> carList=new ArrayList<car>();
		jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				car car=new car();
				car.setId(rs.getInt("id"));
				car.setName(rs.getString("name"));
				car.setTypeId(rs.getInt("typeId"));
				car.setTypeName(rs.getString("typeName"));
				car.setState(rs.getInt("state"));
				car.setGetin_time(rs.getTimestamp("getin_time"));
				car.setGetout_time(rs.getTimestamp("getout_time"));
				Timestamp d = new Timestamp(System.currentTimeMillis()); 
				Timestamp e=car.getGetin_time();
				int hours;
				if(car.getState()==1){
	 hours=getTimeDifference(d,e);
	}
				else{
					 hours=getTimeDifference(car.getGetout_time(),e);
				}
	int fees;
	if(hours<=24){
		fees =hours*7;
	}
	else{
		 fees=(hours-24)*10+7*24;
	}
	
	car.setFee(fees);
	carList.add(car);
			}
		});
		return carList;
	}

	@Override
	public int count(car car) {
		StringBuffer sb=new StringBuffer("select count(*) from car t1,carPlace t2 where t1.typeId=t2.id");
		if(car!=null){
			if(StringUtil.isNotEmpty(car.getName())){
				sb.append(" and t1.name like '%"+car.getName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
	}


	@Override
	public void delete(int id) {
		String sql="delete from car where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}
	@Override
	public boolean existPlaceByTypeId(car car){
		String sql="select * from carPlace  where id=?";
		final carPlace carPlace=new carPlace();
		jdbcTemplate.query(sql, new Object[]{car.getTypeId()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				carPlace.setId(rs.getInt("id"));
				carPlace.setTypeName(rs.getString("typeName"));
				carPlace.setShiyong(rs.getInt("shiyong"));
				carPlace.setNumber(rs.getInt("number"));
			}
		});
		if(carPlace.getShiyong()<carPlace.getNumber()){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public void add(car car) {
		String sql="select * from carPlace  where id=?";
		final carPlace carPlace=new carPlace();
		jdbcTemplate.query(sql, new Object[]{car.getTypeId()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				carPlace.setId(rs.getInt("id"));
				carPlace.setTypeName(rs.getString("typeName"));
				carPlace.setShiyong(rs.getInt("shiyong"));
				carPlace.setNumber(rs.getInt("number"));
			}
		});
		StringBuffer sb=new StringBuffer("insert into car values(null,?,?,?,?,?,?)");
		jdbcTemplate.update(sb.toString(), new Object[]{car.getName(),car.getTypeId(),car.getState(),0, new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis())});
		String sql2="UPDATE carPlace SET shiyong=? WHERE id=?";
		   int shiyong =carPlace.getShiyong()+1;
		jdbcTemplate.update(sql2, new Object[]{shiyong,carPlace.getId()});
	
	}
	    
	@Override
	public void update(car car) {
		String sql="update car set name=?,typeId=?,state=?, where id=?";
		jdbcTemplate.update(sql, new Object[]{car.getName(),car.getTypeId(),car.getState(),car.getId()});
	}

	@Override
	public car loadById(int id) {
		String sql="select * from car t1,carPlace t2 where t1.typeId=t2.id and t1.id=?";
		final car car=new car();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				car.setId(rs.getInt("id"));
				car.setName(rs.getString("name"));
				car.setTypeId(rs.getInt("typeId"));
				car.setTypeName(rs.getString("typeName"));
				car.setState(rs.getInt("state"));
			}
		});
		return car;
	}

	@Override
	public boolean existEquipmentByTypeId(int typeId) {
		String sql="select count(*) from car where typeId=?";
		int result=jdbcTemplate.queryForObject(sql,new Object[]{typeId},Integer.class);
		if(result>0){
			return true;
		}else{
			return false;			
		}
	}
	 /**
	  * 计算两个日期的时间差
	  * @param formatTime1
	  * @param formatTime2
	  * @return
	  */
	 public static int getTimeDifference(Timestamp formatTime1, Timestamp formatTime2) {
	        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
	        long t1 = 0L;
	        long t2 = 0L;
	        try {
	            t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1)).getTime();
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        try {
	            t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2)).getTime();
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        //因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理
	        int hours=(int) ((t1 - t2)/3600000);
	        int minutes=(int) (((t1 - t2)/1000-hours*3600)/60);
	        int second=(int) ((t1 - t2)/1000-hours*3600-minutes*60);
	       if(minutes==0&&second==0){
	    	   return hours;
	       }
	       else
	       {
	    	   return hours+1;
	       }
	    }
	 /**
	  * 格式化时间
	  * Locale是设置语言敏感操作
	  * @param formatTime
	  * @return
	  */
	 public static String getTimeStampNumberFormat(Timestamp formatTime) {
	        SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", new Locale("zh", "cn"));
	        return m_format.format(formatTime);
	    }

	@Override
	public void giveFee(int id) {
		// TODO Auto-generated method stub
		String sql3="UPDATE car SET state=?, getout_time=? where id=?";
		Timestamp a = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(sql3, new Object[]{2,a,id});
		car equipment= loadById(id);
		String sql="select * from carPlace  where id=?";
		final carPlace carPlace=new carPlace();
		jdbcTemplate.query(sql, new Object[]{equipment.getTypeId()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				carPlace.setId(rs.getInt("id"));
				carPlace.setTypeName(rs.getString("typeName"));
				carPlace.setShiyong(rs.getInt("shiyong"));
				carPlace.setNumber(rs.getInt("number"));
			}
		});
		String sql2="UPDATE carPlace SET shiyong=? WHERE id=?";
		   int shiyong =carPlace.getShiyong()-1;
		jdbcTemplate.update(sql2, new Object[]{shiyong,carPlace.getId()});
	
		
		
	}

	

}
