package tse.de3.time_manager_rest_api.controller;

public class RequestTimeByUMY {
	private Integer id_user;
	private Integer month;
	private Integer year;
		
	public RequestTimeByUMY(Integer id_user, Integer month, Integer year) {
		super();
		this.id_user = id_user;
		this.month = month;
		this.year = year;
	}
	public RequestTimeByUMY() {
	}
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public Integer getMonth() {
		return month;
	}
	public void setWeek(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
}