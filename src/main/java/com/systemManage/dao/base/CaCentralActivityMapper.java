package com.systemManage.dao.base;

import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaCentralActivityMapper {
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
    int deleteByPrimaryKey(String activityId);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(CaCentralActivity record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CaCentralActivity record);

    /**
     * 根据条件查询记录集
     */
    List<CaCentralActivity> selectByExample(Criteria example);

    /**
     * 根据主键查询记录
     */
    CaCentralActivity selectByPrimaryKey(String activityId);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByExampleSelective(@Param("record") CaCentralActivity record, @Param("example") Criteria example);

    /**
     * 根据条件更新记录
     */
    int updateByExample(@Param("record") CaCentralActivity record, @Param("example") Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CaCentralActivity record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(CaCentralActivity record);
}