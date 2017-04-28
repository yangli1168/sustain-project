import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import net.xinqushi.common.constants.CacheConstants;
import net.xinqushi.common.exceptions.CommonException;
import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.entity.LineFixedSpot;
import net.xinqushi.orm.entity.LineSpecialPrice;
import net.xinqushi.orm.mapper.CityMapper;
import net.xinqushi.orm.mapper.LineFixedSpotMapper;
import net.xinqushi.orm.mapper.LineSpecialPriceMapper;
import net.xinqushi.util.JedisPoolUtil;
import net.xinqushi.util.Pair;
import net.xinqushi.vo.LineFixedSpots;
import net.xinqushi.vo.Spot;
import redis.clients.jedis.Jedis;

public class MapperTest {
	SqlSession session = null;

	@Before
	public void initEnv() throws IOException {
		String resource = "mybatis2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sessionFactory.openSession();

	}

	@After
	public void endEnv() {
		if (null != session) {
			session.close();
		}
	}

	@Test
	public void testCityMapper() {
		CityMapper mapper = session.getMapper(CityMapper.class);
		List<City> cityList = mapper.getCityList(null, null, null);
		for (City city : cityList) {
			System.out.println(city.getId() + city.getName());
		}
	}
	
	@Test
	public void testLineSpecialPriceMapper(){
		LineSpecialPriceMapper mapper = session.getMapper(LineSpecialPriceMapper.class);
		List<LineSpecialPrice> list = mapper.getAllLinePricingRules(System.currentTimeMillis());
		for (LineSpecialPrice lineSpecialPrice : list) {
			System.out.println(lineSpecialPrice.getId() + " -> " + lineSpecialPrice.getCityName());
		}
		System.out.println("num = " + list.size());
	}
	
	public static void main(String[] args) throws CommonException {
		Jedis jedis = JedisPoolUtil.getJedis();
		
		String str = jedis.hget("line.param.updated", "LINE_FIXED_SPOTS_UPDATED");
		System.out.println("定点更新标志str = " + str);
		
		str = jedis.hget("line.param.updated", "LINE_DYNAMIC_PRICING_UPDATED");
		System.out.println("调价规则更新标志str = " + str);
		
		
		System.out.println("当前时间戳: " + System.currentTimeMillis());
	}

	@Test
	public void testCacheFixedSpot() throws CommonException {
		Jedis jedis = JedisPoolUtil.getJedis();
		String str = jedis.hget("line.param.updated", "LINE_FIXED_SPOTS_UPDATED");
		System.out.println("str =" + str);
		jedis.hdel("line.param.updated", "LINE_FIXED_SPOTS_UPDATED");
		LineFixedSpotMapper lineFixedSpotMapper = session.getMapper(LineFixedSpotMapper.class);

		if (str != null) {
			String[] array = str.split(",");
			if (array != null) {
				for (String ele : array) {
					int id = Integer.parseInt(ele);

					try {
//						lineFixedSpotMapper.invalidateExpiredFixedSpot(id, System.currentTimeMillis());

						List<LineFixedSpot> tspots = lineFixedSpotMapper.getSpotsByLine(id);
						LineFixedSpots lineSpots = new LineFixedSpots();
						lineSpots.setLineId(id);
						if (tspots != null) {
							for (LineFixedSpot tspot : tspots) {
								lineSpots.setSrcCityId(tspot.getSrcCityId());
								lineSpots.setDstCityId(tspot.getDstCityId());

								Spot spot = new Spot();
								spot.setId(tspot.getCityId());
								spot.setName(tspot.getName());
								spot.setLat(tspot.getLatitude());
								spot.setLon(tspot.getLongitude());

								// TODO 确定枚举值
								if ("LINE".equals(tspot.getRuleType())
										&& (tspot.getStartTime() == null || tspot.getStartTime() == 0)) {
									if (tspot.getCityId().equals(tspot.getSrcCityId())) {
										lineSpots.getSrcSpots().add(spot);
									} else {
										lineSpots.getDstSpots().add(spot);
									}
								} else {
									List<Pair<Long, Long>> list = null;
									if (tspot.getCityId().equals(tspot.getSrcCityId())) {
										list = lineSpots.getTimedSrcSpots().get(spot);
										if (list == null) {
											list = new ArrayList<Pair<Long, Long>>();
											lineSpots.getTimedSrcSpots().put(spot, list);
										}
									} else {
										list = lineSpots.getTimedDstcSpots().get(spot);
										if (list == null) {
											list = new ArrayList<Pair<Long, Long>>();
											lineSpots.getTimedDstcSpots().put(spot, list);
										}
									}

									Pair<Long, Long> pair = new Pair<Long, Long>();
									pair.setValue1(tspot.getStartTime());
									pair.setValue2(tspot.getEndTime());
									list.add(pair);
								}
							}
						}

						jedis.hset(CacheConstants.LINE_FIXED_SPOT_KEY, id + "",
								JSON.toJSONString(lineSpots));
					} catch (Exception e) {
						throw new CommonException("Fail to cache line fixed spots");
					}
				}
			}
		}

	}

}
