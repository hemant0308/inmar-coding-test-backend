package com.inmar.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inmar.api.config.Mappings;
import com.inmar.api.model.Location;
import com.inmar.api.service.LocationService;

@RestController
public class LocationController {

	@Autowired
	LocationService locationService;

	static Logger log = LogManager.getLogger(LocationController.class);

	@RequestMapping(value = Mappings.GET_LOCATIONS, method = RequestMethod.GET)
	public ResponseEntity<Object> getLocatioins(HttpServletRequest request) {

		List<Location> locations = locationService.getLocations();

		return new ResponseEntity<Object>(locations, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.GET_LOCATIONS, method = RequestMethod.POST)
	public ResponseEntity<Object> saveLocation(@RequestBody Map<String, Object> payload, HttpServletRequest request) {

		Location location = locationService.saveLocation(payload);

		return new ResponseEntity<Object>(location, HttpStatus.OK);
	}

}
