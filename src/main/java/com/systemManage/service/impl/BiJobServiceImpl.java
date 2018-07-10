package com.systemManage.service.impl;

import com.systemManage.dao.base.BiJobMapper;
import com.systemManage.pojo.base.BiJob;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.BiJobService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiJobServiceImpl implements BiJobService {
    @Autowired
    private BiJobMapper biJobMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiJobServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.biJobMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public BiJob selectByPrimaryKey(String jobId) {
        return this.biJobMapper.selectByPrimaryKey(jobId);
    }

    public List<BiJob> selectByExample(Criteria example) {
        return this.biJobMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String jobId) {
        return this.biJobMapper.deleteByPrimaryKey(jobId);
    }

    public int updateByPrimaryKeySelective(BiJob record) {
        return this.biJobMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BiJob record) {
        return this.biJobMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.biJobMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BiJob record, Criteria example) {
        return this.biJobMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BiJob record, Criteria example) {
        return this.biJobMapper.updateByExample(record, example);
    }

    public int insert(BiJob record) {
        return this.biJobMapper.insert(record);
    }

    public int insertSelective(BiJob record) {
        return this.biJobMapper.insertSelective(record);
    }
}