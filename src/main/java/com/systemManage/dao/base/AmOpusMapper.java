package com.systemManage.dao.base;

import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmOpusMapper {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByExample(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String opusId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(AmOpus record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(AmOpus record);

    /**
     * 根据条件查询记录集
     */
    List<AmOpus> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    AmOpus selectByPrimaryKey(String opusId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") AmOpus record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") AmOpus record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(AmOpus record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(AmOpus record);
}