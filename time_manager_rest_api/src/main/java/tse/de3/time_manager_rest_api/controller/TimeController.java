package tse.de3.time_manager_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tse.de3.time_manager_rest_api.domain.Time;
import tse.de3.time_manager_rest_api.service.TimeService;

@RestController
public class TimeController {
	
	
	@Autowired
	private TimeService timeService;
	
	@GetMapping("/time/{id_time}")
	public Time oneTime(@PathVariable Integer id_time) {
				
		return this.timeService.findTime(id_time);
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

		Time timeExistante = this.timeService.findTime(id_time);
		
		if (time != null) {
			
			timeExistante.setName(time.getName());
			timeExistante = this.timeService.saveTime(timeExistante);
		}
		
		return timeExistante;
	}
}