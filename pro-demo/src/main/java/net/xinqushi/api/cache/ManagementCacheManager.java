package net.xinqushi.api.cache;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.xinqushi.api.cache.fallback.ManagementCacheManagerFallback;
import net.xinqushi.common.entity.ManagementUserElement;
import net.xinqushi.common.exceptions.CommonException;

@FeignClient(value = "cache-service", fallback = ManagementCacheManagerFallback.class, path = "/cache/management")
public interface ManagementCacheManager {

	@RequestMapping(value = "/token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void putManUserElement(@RequestParam("manageUserElementStr") String manageUserElementStr)
			throws CommonException;

	@RequestMapping(value = "/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ManagementUserElement getManUserElement(@PathVariable("token") String token) throws CommonException;

	@RequestMapping(value = "/{token}/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteManUserElement(@PathVariable("token") String token) throws CommonException;
}
