package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pojo.Travels;

@Repository
public interface TravelsMapper {
    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table travels
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer tid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table travels
	 * @mbggenerated
	 */
	int insert(Travels record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table travels
	 * @mbggenerated
	 */
	int insertSelective(Travels record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table travels
	 * @mbggenerated
	 */
	Travels selectByPrimaryKey(Integer tid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table travels
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Travels record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table travels
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Travels record);

	List<Travels> selecAll(Travels travels);

	List<Travels> selectTravelsBySort(String nowDays);
}