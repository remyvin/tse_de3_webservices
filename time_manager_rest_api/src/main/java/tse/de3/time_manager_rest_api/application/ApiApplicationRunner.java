package tse.de3.time_manager_rest_api.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tse.de3.time_manager_rest_api.dao.TimeRepository;
import tse.de3.time_manager_rest_api.domain.Time;

@Component
public class ApiApplicationRunner implements ApplicationRunner {
		
	@Autowired
	private TimeRepository timeRepository; 

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Time time1 = new Time(2, 1, 1, 1, 1, 2021);
		Time time2 = new Time(2, 1, 4, 2, 1, 2021);
		Time time3 = new Time(2, 1, 5, 4, 1, 2021);
		Time time4 = new Time(0, 0, 6, 1, 1, 2021);
		Time time5 = new Time(0, 0, 2, 2, 1, 2021);
		Time time6 = new Time(0, 0, 1, 2, 1, 2021);
		Time time7 = new Time(1, 1, 3, 1, 1, 2021);
		Time time8 = new Time(1, 0, 1, 2, 1, 2021);
		Time time9 = new Time(1, 1, 1, 3, 1, 2021);
			
		this.timeRepository.save(time1);
		this.timeRepository.save(time2);
		this.timeRepository.save(time3);
		this.timeRepository.save(time4);
		this.timeRepository.save(time5);
		this.timeRepository.save(time6);
		this.timeRepository.save(time7);
		this.timeRepository.save(time8);
		this.timeRepository.save(time9);
	}
}