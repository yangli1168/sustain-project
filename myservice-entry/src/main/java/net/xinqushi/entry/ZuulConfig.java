package net.xinqushi.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.ZuulConfiguration;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.xinqushi.entry.vo.SpecialUrlMapping;

@Configuration
public class ZuulConfig extends ZuulConfiguration {

	@Autowired
	private SpecialUrlMapping specialUrlMapping;

	@Bean
	public RouteLocator routeLocator() {
		return new SimpleRouteLocator(this.server.getServletPrefix(), this.zuulProperties) {
			@Override
			public Route getMatchingRoute(String path) {
				path = adjustPath(path);
				return super.getMatchingRoute(path);
			}

			/**
			 * 在zuul路由前处理path映射
			 * 
			 * @param path
			 * @return
			 */
			private String adjustPath(String path) {
				if (specialUrlMapping != null) {
					if (specialUrlMapping.getUrlMapping().containsKey(path)) {
						return specialUrlMapping.getUrlMapping().get(path);
					}
				}
				return path;
			}
		};
	}
}
