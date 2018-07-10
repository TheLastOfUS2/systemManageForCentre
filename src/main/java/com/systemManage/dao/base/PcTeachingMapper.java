package com.systemManage.dao.base;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.PcTeaching;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PcTeachingMapper {
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
    int deleteByPrimaryKey(String teachingId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(PcTeaching record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(PcTeaching record);

    /**
     * 根据条件查询记录集
     */
    List<PcTeaching> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    PcTeaching selectByPrimaryKey(String teachingId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") PcTeaching record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") PcTeaching record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(PcTeaching record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(PcTeaching record);
}