package com.systemManage.service;

import com.systemManage.pojo.base.BiAbroad;
import com.systemManage.pojo.base.Criteria;
import java.util.List;

public interface BiAbroadService {
    int countByExample(Criteria example);

    BiAbroad selectByPrimaryKey(String abroadId);

    List<BiAbroad> selectByExample(Criteria example);

    int deleteByPrimaryKey(String abroadId);

    int updateByPrimaryKeySelective(BiAbroad record);

    int updateByPrimaryKey(BiAbroad record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(BiAbroad record, Criteria example);

    int updateByExample(BiAbroad record, Criteria example);

    int insert(BiAbroad record);

    int insertSelective(BiAbroad record);
}