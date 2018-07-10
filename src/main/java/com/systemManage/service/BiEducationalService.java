package com.systemManage.service;

import com.systemManage.pojo.base.BiEducational;
import com.systemManage.pojo.base.Criteria;
import java.util.List;

public interface BiEducationalService {
    int countByExample(Criteria example);

    BiEducational selectByPrimaryKey(String educationalId);

    List<BiEducational> selectByExample(Criteria example);

    int deleteByPrimaryKey(String educationalId);

    int updateByPrimaryKeySelective(BiEducational record);

    int updateByPrimaryKey(BiEducational record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(BiEducational record, Criteria example);

    int updateByExample(BiEducational record, Criteria example);

    int insert(BiEducational record);

    int insertSelective(BiEducational record);
}