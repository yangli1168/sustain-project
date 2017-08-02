package net.xinqushi.cache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.xinqushi.cache.service.ManagementCacheService;
import net.xinqushi.common.entity.ManagementUserElement;
import net.xinqushi.common.exceptions.CommonException;

@CrossOrigin
@RestController
@RequestMapping("/cache/management")
public class ManagementCacheController {
	
	private static Logger logger = LoggerFactory.getLogger(ManagementCacheController.class);

	@Autowired
	private ManagementCacheService managementCacheService;

	@RequestMapping(value = "/token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void putManUserElement(@RequestParam("manageUserElementStr") String manageUserElementStr)
	        throws CommonException {
		try {
			managementCacheService.putUserElement(manageUserElementStr);
		} catch (CommonException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Fail to cache management user element", e);
			throw new CommonException("Fail to put element to cache");
		}
	}

	@RequestMapping(value = "/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ManagementUserElement getManUserElement(@PathVariable("token") String token) throws CommonException {
		return managementCacheService.getElementByToken(token);
	}
	
	@RequestMapping(value = "/{token}/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteManUserElement(@PathVariable("token") String token) throws CommonException {
		managementCacheService.deleteManUserElementByToken(token);
	}
}
