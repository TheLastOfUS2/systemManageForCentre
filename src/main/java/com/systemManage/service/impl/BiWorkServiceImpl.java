package com.systemManage.service.impl;

import com.systemManage.dao.base.BiWorkMapper;
import com.systemManage.pojo.base.BiWork;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.BiWorkService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiWorkServiceImpl implements BiWorkService {
    @Autowired
    private BiWorkMapper biWorkMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiWorkServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.biWorkMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public BiWork selectByPrimaryKey(String workId) {
        return this.biWorkMapper.selectByPrimaryKey(workId);
    }

    public List<BiWork> selectByExample(Criteria example) {
        return this.biWorkMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String workId) {
        return this.biWorkMapper.deleteByPrimaryKey(workId);
    }

    public int updateByPrimaryKeySelective(BiWork record) {
        return this.biWorkMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BiWork record) {
        return this.biWorkMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.biWorkMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BiWork record, Criteria example) {
        return this.biWorkMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BiWork record, Criteria example) {
        return this.biWorkMapper.updateByExample(record, example);
    }

    public int insert(BiWork record) {
        return this.biWorkMapper.insert(record);
    }

    public int insertSelective(BiWork record) {
        return this.biWorkMapper.insertSelective(record);
    }
}