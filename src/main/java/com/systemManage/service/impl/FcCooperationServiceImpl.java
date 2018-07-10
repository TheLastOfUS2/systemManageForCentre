package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.FcCooperationMapper;
import com.systemManage.dao.ext.FcCooperationMapperExt;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.CsTrainConsult;
import com.systemManage.pojo.base.FcCooperation;
import com.systemManage.service.FcCooperationService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FcCooperationServiceImpl implements FcCooperationService {
    @Autowired
    private FcCooperationMapper fcCooperationMapper; // 对外合作
    @Autowired
    private FcCooperationMapperExt fcCooperationMapperExt; // 对外合作

    private static final Logger logger = LoggerFactory.getLogger(FcCooperationServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(FcCooperation fcCooperation, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(fcCooperation!=null){
            String cooperationId=fcCooperation.getCooperationId();
            if (StringUtil.isEmpty(cooperationId)) {
                //设置主键
                fcCooperation.setCooperationId(StringUtil.getUUID());
                //设置新增时间
                fcCooperation.setCooperationCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                fcCooperation.setCooperationDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(cooperationId)) {
                res = this.fcCooperationMapper.insertSelective(fcCooperation);
            }else{
                res = this.fcCooperationMapper.updateByPrimaryKeySelective(fcCooperation);
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
        int count = this.fcCooperationMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public FcCooperation selectByPrimaryKey(String cooperationId) {
        return this.fcCooperationMapper.selectByPrimaryKey(cooperationId);
    }

    public List<FcCooperation> selectByExample(Criteria example) {
        return this.fcCooperationMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String cooperationId) {
        return this.fcCooperationMapper.deleteByPrimaryKey(cooperationId);
    }

    public int updateByPrimaryKeySelective(FcCooperation record) {
        return this.fcCooperationMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(FcCooperation record,String cooperationDel) {
        // cooperationDel=0:恢复;cooperationDel=1:删除;cooperationDel=2:彻底删除
        if(!("2").equals(cooperationDel)){
            //更改数据状态
            record.setCooperationDel(cooperationDel);
            return this.fcCooperationMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.fcCooperationMapper.deleteByPrimaryKey(record.getCooperationId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.fcCooperationMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(FcCooperation record, Criteria example) {
        return this.fcCooperationMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(FcCooperation record, Criteria example) {
        return this.fcCooperationMapper.updateByExample(record, example);
    }

    public int insert(FcCooperation record) {
        return this.fcCooperationMapper.insert(record);
    }

    public int insertSelective(FcCooperation record) {
        return this.fcCooperationMapper.insertSelective(record);
    }
}