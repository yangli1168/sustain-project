import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.xinqushi.orm.entity.City;
import net.xinqushi.orm.mapper.CityMapper;

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
	public void endEnv(){
		if (null != session) {
			session.close();
		}
	}
	
	@Test
	public void testCityMapper(){
		CityMapper mapper = session.getMapper(CityMapper.class);
		List<City> cityList = mapper.getCityList(null, null, null);
		for (City city : cityList) {
			System.out.println(city.getId() + city.getName());
		}
	}
}
