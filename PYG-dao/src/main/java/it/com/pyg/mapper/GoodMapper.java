package it.com.pyg.mapper;

import it.com.pyg.entity.Good;
import it.com.pyg.entity.GoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodMapper {
    long countByExample(GoodExample example);

    int deleteByExample(GoodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Good record);

    int insertSelective(Good record);

    List<Good> selectByExample(GoodExample example);

    Good selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Good record, @Param("example") GoodExample example);

    int updateByExample(@Param("record") Good record, @Param("example") GoodExample example);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);
}