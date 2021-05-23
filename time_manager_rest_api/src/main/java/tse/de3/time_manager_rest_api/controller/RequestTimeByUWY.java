package tse.de3.time_manager_rest_api.controller;

public class RequestTimeByUWY {
	private Integer id_user;
	private Integer week;
	private Integer year;
		
	public RequestTimeByUWY(Integer id_user, Integer week, Integer year) {
		super();
		this.id_user = id_user;
		this.week = week;
		this.year = year;
	}
	public RequestTimeByUWY() {
	}
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
}