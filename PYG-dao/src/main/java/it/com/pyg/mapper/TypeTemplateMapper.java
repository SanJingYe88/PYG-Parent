package it.com.pyg.mapper;

import it.com.pyg.entity.TypeTemplate;
import it.com.pyg.entity.TypeTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypeTemplateMapper {
    long countByExample(TypeTemplateExample example);

    int deleteByExample(TypeTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TypeTemplate record);

    int insertSelective(TypeTemplate record);

    List<TypeTemplate> selectByExample(TypeTemplateExample example);

    TypeTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TypeTemplate record, @Param("example") TypeTemplateExample example);

    int updateByExample(@Param("record") TypeTemplate record, @Param("example") TypeTemplateExample example);

    int updateByPrimaryKeySelective(TypeTemplate record);

    int updateByPrimaryKey(TypeTemplate record);
}