package com.systemManage.service.impl;

import com.systemManage.dao.base.BiEducationalMapper;
import com.systemManage.pojo.base.BiEducational;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.BiEducationalService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiEducationalServiceImpl implements BiEducationalService {
    @Autowired
    private BiEducationalMapper biEducationalMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiEducationalServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.biEducationalMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public BiEducational selectByPrimaryKey(String educationalId) {
        return this.biEducationalMapper.selectByPrimaryKey(educationalId);
    }

    public List<BiEducational> selectByExample(Criteria example) {
        return this.biEducationalMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String educationalId) {
        return this.biEducationalMapper.deleteByPrimaryKey(educationalId);
    }

    public int updateByPrimaryKeySelective(BiEducational record) {
        return this.biEducationalMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BiEducational record) {
        return this.biEducationalMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.biEducationalMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BiEducational record, Criteria example) {
        return this.biEducationalMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BiEducational record, Criteria example) {
        return this.biEducationalMapper.updateByExample(record, example);
    }

    public int insert(BiEducational record) {
        return this.biEducationalMapper.insert(record);
    }

    public int insertSelective(BiEducational record) {
        return this.biEducationalMapper.insertSelective(record);
    }
}