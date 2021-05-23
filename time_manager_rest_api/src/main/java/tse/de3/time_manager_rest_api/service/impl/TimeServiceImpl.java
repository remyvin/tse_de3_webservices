package tse.de3.time_manager_rest_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tse.de3.time_manager_rest_api.dao.TimeRepository;
import tse.de3.time_manager_rest_api.domain.Time;
import tse.de3.time_manager_rest_api.service.TimeService;

@Service
@Transactional
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeRepository timeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Time findTime(Integer id_time) {

		return this.timeRepository.findById(id_time).orElse(null);
	}

	@Override
	public Time createTime(Time time) {
		
		return this.timeRepository.save(time);
	}
	
	@Override
	public void deleteTime(Integer id_time) {
		
		this.timeRepository.deleteById(id_time);
	}
	
	@Override
	public Time saveTime(Time time) {
		
		return this.timeRepository.save(time);
	}
}