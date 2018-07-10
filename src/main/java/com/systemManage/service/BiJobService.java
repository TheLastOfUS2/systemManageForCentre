package com.systemManage.service;

import com.systemManage.pojo.base.BiJob;
import com.systemManage.pojo.base.Criteria;
import java.util.List;

public interface BiJobService {
    int countByExample(Criteria example);

    BiJob selectByPrimaryKey(String jobId);

    List<BiJob> selectByExample(Criteria example);

    int deleteByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(BiJob record);

    int updateByPrimaryKey(BiJob record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(BiJob record, Criteria example);

    int updateByExample(BiJob record, Criteria example);

    int insert(BiJob record);

    int insertSelective(BiJob record);
}