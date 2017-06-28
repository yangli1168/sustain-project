package net.xinqushi.api.cache.fallback;

import org.springframework.stereotype.Component;

import net.xinqushi.api.cache.ManagementCacheManager;
import net.xinqushi.common.entity.ManagementUserElement;
import net.xinqushi.common.exceptions.CommonException;


@Component
public class ManagementCacheManagerFallback implements ManagementCacheManager {

	@Override
	public void putManUserElement(String manageUserElementStr) throws CommonException {
		// TODO Auto-generated method stub

	}

	@Override
	public ManagementUserElement getManUserElement(String token) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteManUserElement(String token) throws CommonException {
		// TODO Auto-generated method stub
		
	}

}
