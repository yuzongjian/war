package homework.model;

public class carPlace {

	private Integer id;
	private String typeName; //����
	private int number;//��λ��
	private int shiyong; //��ʹ�ó�λ��
	private int kongwei; //�ճ�λ
	private int numStatus;//��λ״̬��1Ϊδ����2Ϊ����
	public int getNumber() {
		return number;
	}
	
	public void setKongwei(int kongwei) {
		this.kongwei = kongwei;
	}

	public void setNumStatus(int numStatus) {
		this.numStatus = numStatus;
	}

	public int getKongwei() {
		return kongwei;
	}
	public int getNumStatus() {
		return numStatus;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	public int getShiyong() {
		return shiyong;
	}
	public void setShiyong(int shiyong) {
		this.shiyong = shiyong;
	}
	private String remark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
