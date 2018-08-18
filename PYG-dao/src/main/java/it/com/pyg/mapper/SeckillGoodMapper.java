package it.com.pyg.mapper;

import it.com.pyg.entity.SeckillGood;
import it.com.pyg.entity.SeckillGoodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeckillGoodMapper {
    long countByExample(SeckillGoodExample example);

    int deleteByExample(SeckillGoodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SeckillGood record);

    int insertSelective(SeckillGood record);

    List<SeckillGood> selectByExample(SeckillGoodExample example);

    SeckillGood selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SeckillGood record, @Param("example") SeckillGoodExample example);

    int updateByExample(@Param("record") SeckillGood record, @Param("example") SeckillGoodExample example);

    int updateByPrimaryKeySelective(SeckillGood record);

    int updateByPrimaryKey(SeckillGood record);
}