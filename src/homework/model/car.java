package homework.model;

import java.sql.Date;
import java.sql.Timestamp;

public class car {

	private Integer id;
	private String name;
	private Integer typeId;
	private String typeName;
	private Integer state; // 状态 1 正常停车 2 已结束停车
	private int fee;
	private Timestamp getin_time;  //入库时间
	private Timestamp getout_time;  //出库时间
	public int getFee() {
		return fee;
	}
	public void setFee(int fees) {
		this.fee = fees;
	}
	public Timestamp getGetin_time() {

		return getin_time;
	}
	public void setGetin_time(Timestamp getin_time) {
		this.getin_time = getin_time;
	}
	public Timestamp getGetout_time() {
		return getout_time;
	}
	public void setGetout_time(Timestamp getout_time) {
		this.getout_time = getout_time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	
	
}
