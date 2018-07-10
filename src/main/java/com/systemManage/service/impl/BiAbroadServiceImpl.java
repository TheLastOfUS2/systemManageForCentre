package com.systemManage.service.impl;

import com.systemManage.dao.base.BiAbroadMapper;
import com.systemManage.pojo.base.BiAbroad;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.BiAbroadService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiAbroadServiceImpl implements BiAbroadService {
    @Autowired
    private BiAbroadMapper biAbroadMapper;

    private static final Logger logger = LoggerFactory.getLogger(BiAbroadServiceImpl.class);

    public int countByExample(Criteria example) {
        int count = this.biAbroadMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public BiAbroad selectByPrimaryKey(String abroadId) {
        return this.biAbroadMapper.selectByPrimaryKey(abroadId);
    }

    public List<BiAbroad> selectByExample(Criteria example) {
        return this.biAbroadMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String abroadId) {
        return this.biAbroadMapper.deleteByPrimaryKey(abroadId);
    }

    public int updateByPrimaryKeySelective(BiAbroad record) {
        return this.biAbroadMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BiAbroad record) {
        return this.biAbroadMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.biAbroadMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(BiAbroad record, Criteria example) {
        return this.biAbroadMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(BiAbroad record, Criteria example) {
        return this.biAbroadMapper.updateByExample(record, example);
    }

    public int insert(BiAbroad record) {
        return this.biAbroadMapper.insert(record);
    }

    public int insertSelective(BiAbroad record) {
        return this.biAbroadMapper.insertSelective(record);
    }
}