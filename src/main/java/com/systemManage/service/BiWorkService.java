package com.systemManage.service;

import com.systemManage.pojo.base.BiWork;
import com.systemManage.pojo.base.Criteria;
import java.util.List;

public interface BiWorkService {
    int countByExample(Criteria example);

    BiWork selectByPrimaryKey(String workId);

    List<BiWork> selectByExample(Criteria example);

    int deleteByPrimaryKey(String workId);

    int updateByPrimaryKeySelective(BiWork record);

    int updateByPrimaryKey(BiWork record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(BiWork record, Criteria example);

    int updateByExample(BiWork record, Criteria example);

    int insert(BiWork record);

    int insertSelective(BiWork record);
}