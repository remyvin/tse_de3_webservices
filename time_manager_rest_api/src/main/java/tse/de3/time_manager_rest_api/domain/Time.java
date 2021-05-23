package tse.de3.time_manager_rest_api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Time {
	
	@Id
	@GeneratedValue
	private Integer id_time;
	private String user_name;
	private String project_name;
	private Integer nb_time;
	private Integer week;
	private Integer month;
	private Integer year;
		
	public Time(Integer id_time, String user_name, String project_name, Integer nb_time, Integer week, Integer month, Integer year) {
		this.id_time = id_time;
		this.user_name = user_name;
		this.project_name = project_name;
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
	public String getName() {
		return user_name;
	}
	public void setName(String user_name) {
		this.user_name = user_name;
	}
	public String getProject() {
		return project_name;
	}
	public void setProject(String project_name) {
		this.project_name = project_name;
	}
	public Integer getNbTime() {
		return nb_time;
	}
	public void setNbTime(Integer nb_time) {
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