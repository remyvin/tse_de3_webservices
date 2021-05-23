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
		Time time1 = new Time(3, 1, 1, 2, 2, 2021);
		Time time2 = new Time(3, 1, 1, 2, 2, 2021);
			
		this.timeRepository.save(time1);
		this.timeRepository.save(time2);
	}
}