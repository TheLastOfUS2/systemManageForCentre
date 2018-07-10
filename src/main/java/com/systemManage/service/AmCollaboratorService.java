package com.systemManage.service;

import com.systemManage.pojo.base.AmCollaborator;
import com.systemManage.pojo.base.Criteria;
import java.util.List;

public interface AmCollaboratorService {
    int countByExample(Criteria example);

    AmCollaborator selectByPrimaryKey(String collaboratorId);

    List<AmCollaborator> selectByExample(Criteria example);

    int deleteByPrimaryKey(String collaboratorId);

    int updateByPrimaryKeySelective(AmCollaborator record);

    int updateByPrimaryKey(AmCollaborator record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmCollaborator record, Criteria example);

    int updateByExample(AmCollaborator record, Criteria example);

    int insert(AmCollaborator record);

    int insertSelective(AmCollaborator record);
}