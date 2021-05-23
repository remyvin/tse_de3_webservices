package tse.de3.time_manager_rest_api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Time {
	
	@Id
	@GeneratedValue
	private Integer id_time;
	private Integer id_user;
	private Integer id_project;
	private Integer nb_time;
	private Integer week;
	private Integer month;
	private Integer year;
		
	public Time(Integer id_user, Integer id_project, Integer nb_time, Integer week, Integer month, Integer year) {
		this.id_user = id_user;
		this.id_project = id_project;
		this.nb_time = nb_time;
		this.week = week;
		this.month = month;
		this.year = year;
	}

	public Time() {
	}

	public Integer getId() {
		return id_time;
	}

	public void setId(Integer id_time) {
		this.id_time = id_time;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public Integer getId_project() {
		return id_project;
	}

	public void setId_project(Integer id_project) {
		this.id_project = id_project;
	}

	public Integer getNb_time() {
		return nb_time;
	}

	public void setNb_time(Integer nb_time) {
		this.nb_time = nb_time;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
}