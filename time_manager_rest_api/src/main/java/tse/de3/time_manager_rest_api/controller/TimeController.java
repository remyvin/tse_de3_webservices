package tse.de3.time_manager_rest_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tse.de3.time_manager_rest_api.domain.Time;
import tse.de3.time_manager_rest_api.service.TimeService;
//@RequestMapping("/api")
@RestController
public class TimeController {
	
	
	@Autowired
	private TimeService timeService;
	
	@GetMapping("/time/{id_time}")
	public Time findTime(@PathVariable Integer id_time) {
		return this.timeService.findTime(id_time);
	}
	
	@PostMapping("/timebyuwy")
	public List<Time> findUWYTime(@RequestBody RequestTimeByUWY requestuwy) {
		List<Time> times = this.timeService.findAllTime();
		List<Time> timesWanted = new ArrayList<Time>(10);
		for(Time time : times) {
			if(time.getId_user().equals(requestuwy.getId_user()) && time.getWeek().equals(requestuwy.getWeek()) && time.getYear().equals(requestuwy.getYear())) {
				timesWanted.add(time);
			}
		}
		return timesWanted;
	}
	
	@PostMapping("/timebyumy")
	public List<Time> findUMYTime(@RequestBody RequestTimeByUMY requestumy) {
		List<Time> times = this.timeService.findAllTime();
		List<Time> timesWanted = new ArrayList<Time>(10);
		for(Time time : times) {
			if(time.getId_user().equals(requestumy.getId_user()) && time.getMonth().equals(requestumy.getMonth()) && time.getYear().equals(requestumy.getYear())) {
				timesWanted.add(time);
			}
		}
		return timesWanted;
	}
	
	@PostMapping("/time")
	public Time createTime(@RequestBody Time time) {
		
		return this.timeService.createTime(time);
	}
	
	@DeleteMapping("/time/{id_time}")
	public void deleteTime(@PathVariable Integer id_time) {
		
		this.timeService.deleteTime(id_time);
	}
	
	@PutMapping("/time/{id_time}")
	Time replaceTime(@RequestBody Time time, @PathVariable Integer id_time) {

		Time timeExistant = this.timeService.findTime(id_time);
		
		if (time != null) {
			timeExistant.setId_user(time.getId_user());
			timeExistant.setId_project(time.getId_project());
			timeExistant.setNb_time(time.getNb_time());
			timeExistant.setWeek(time.getWeek());
			timeExistant.setMonth(time.getMonth());
			timeExistant.setYear(time.getYear());
			timeExistant = this.timeService.saveTime(timeExistant);
		}
		
		return timeExistant;
	}
}