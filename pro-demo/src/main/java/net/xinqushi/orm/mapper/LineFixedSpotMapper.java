package net.xinqushi.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.xinqushi.orm.entity.LineFixedSpot;

public interface LineFixedSpotMapper extends SqlMapper{
	
	int deleteByPrimaryKey(Integer id);

    int insert(LineFixedSpot record);

    int insertSelective(LineFixedSpot record);

    LineFixedSpot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LineFixedSpot record);

    int updateByPrimaryKey(LineFixedSpot record);

	List<LineFixedSpot> list(@Param("id")int id,@Param("ruleType")String ruleType);
	
	void del(LineFixedSpot fixedSpot);
	
	void add(LineFixedSpot fixedSpot);
	
	List<LineFixedSpot> serachByCoordinate(@Param("longitude") Double longitude,@Param("latitude") Double latitude);
	
	List<LineFixedSpot> getSpotsByLine(@Param("lineId") int lineId);
	
	void invalidateExpiredFixedSpot(@Param("lineId") int lineId, @Param("currentTime") long time);
}

