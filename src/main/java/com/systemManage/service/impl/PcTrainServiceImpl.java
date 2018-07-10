package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.PcTrainMapper;
import com.systemManage.dao.ext.PcTrainMapperExt;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.PcTeaching;
import com.systemManage.pojo.base.PcTrain;
import com.systemManage.service.PcTrainService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PcTrainServiceImpl implements PcTrainService {
    @Autowired
    private PcTrainMapper pcTrainMapper; // 培训深造
    @Autowired
    private PcTrainMapperExt pcTrainMapperExt; // 培训深造

    private static final Logger logger = LoggerFactory.getLogger(PcTrainServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(PcTrain pcTrain, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(pcTrain!=null){
            String trainId=pcTrain.getTrainId();
            if (StringUtil.isEmpty(trainId)) {
                //设置主键
                pcTrain.setTrainId(StringUtil.getUUID());
                //设置新增时间
                pcTrain.setTrainCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                pcTrain.setTrainDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(trainId)) {
                res = this.pcTrainMapper.insertSelective(pcTrain);
            }else{
                res = this.pcTrainMapper.updateByPrimaryKeySelective(pcTrain);
            }
        }
        if(res > 0){
            result = JsonUtils.setSuccess();    
            result.put("text", "操作成功");
        }else{
            result = JsonUtils.setError();
            result.put("text", "操作失败");
        }
        return result;
    }
    
    public int countByExample(Criteria example) {
        int count = this.pcTrainMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public PcTrain selectByPrimaryKey(String trainId) {
        return this.pcTrainMapper.selectByPrimaryKey(trainId);
    }

    public List<PcTrain> selectByExample(Criteria example) {
        return this.pcTrainMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String trainId) {
        return this.pcTrainMapper.deleteByPrimaryKey(trainId);
    }

    public int updateByPrimaryKeySelective(PcTrain record) {
        return this.pcTrainMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PcTrain record,String trainDel) {
        // trainDel=0:恢复;trainDel=1:删除;trainDel=2:彻底删除
        if(!("2").equals(trainDel)){
            //更改数据状态
            record.setTrainDel(trainDel);
            return this.pcTrainMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.pcTrainMapper.deleteByPrimaryKey(record.getTrainId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.pcTrainMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(PcTrain record, Criteria example) {
        return this.pcTrainMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(PcTrain record, Criteria example) {
        return this.pcTrainMapper.updateByExample(record, example);
    }

    public int insert(PcTrain record) {
        return this.pcTrainMapper.insert(record);
    }

    public int insertSelective(PcTrain record) {
        return this.pcTrainMapper.insertSelective(record);
    }
}