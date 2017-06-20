package net.xinqushi.orm.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class SuperDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static String getCurrentLookupKey() {
        return (String) contextHolder.get();
    }

    public static void setCurrentLookupKey(String currentLookupKey) {
        contextHolder.set(currentLookupKey);
    }

    public static void resetCurrentLookupKey() {
        contextHolder.remove();
    }
	
	@Override
	protected Object determineCurrentLookupKey() {
		return getCurrentLookupKey();
	}

}
