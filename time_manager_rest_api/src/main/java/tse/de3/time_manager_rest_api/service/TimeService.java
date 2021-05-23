package tse.de3.time_manager_rest_api.service;

import tse.de3.time_manager_rest_api.domain.Time;

public interface TimeService {

	public Time findTime(Integer id_time);

	public Time createTime(Time time);

	public void deleteTime(Integer id_time);

	public Time saveTime(Time time);
	
}