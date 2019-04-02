package com.inmar.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Map<String, Object>> getLocatioins(HttpServletRequest request) {

		log.debug("Request received for :" + Mappings.GET_LOCATIONS);

		List<Location> locations = locationService.getLocations();

		log.debug("Sending locations to response");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("locations", locations);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_LOCATION, method = RequestMethod.POST)
	public ResponseEntity<Object> saveLocation(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		log.debug("Request received for :" + Mappings.SAVE_LOCATION);
		int userId = (int) request.getAttribute("userId");

		Location location = locationService.saveLocation(payload, userId);

		log.debug("Location saved with identity : " + location.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("location", location);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.DELETE_LOCATION, method = RequestMethod.POST)
	public ResponseEntity<Object> deleteLocation(@PathVariable int locationId, HttpServletRequest request) {
		log.debug("Request received for :" + Mappings.DELETE_LOCATION);

		Map<String, Object> response = new HashMap<String, Object>();
		try {
			Location location = locationService.deleteLocation(locationId);
			response.put("location", location);
			log.debug("Delete location with id : " + location.getId());
		} catch (Exception e) {
			response.put("message", e.getMessage());
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
