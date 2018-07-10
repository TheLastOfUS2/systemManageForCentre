package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.CsOtherEntrustMapper;
import com.systemManage.dao.ext.CsOtherEntrustMapperExt;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.CsOtherEntrust;
import com.systemManage.pojo.base.PcTeaching;
import com.systemManage.service.CsOtherEntrustService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsOtherEntrustServiceImpl implements CsOtherEntrustService {
    @Autowired
    private CsOtherEntrustMapper csOtherEntrustMapper;
    @Autowired
    private CsOtherEntrustMapperExt csOtherEntrustMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(CsOtherEntrustServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(CsOtherEntrust csOtherEntrust, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(csOtherEntrust!=null){
            String entrustId=csOtherEntrust.getEntrustId();
            if (StringUtil.isEmpty(entrustId)) {
                //设置主键
                csOtherEntrust.setEntrustId(StringUtil.getUUID());
                //设置新增时间
                csOtherEntrust.setEntrustCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                csOtherEntrust.setEntrustDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(entrustId)) {
                res = this.csOtherEntrustMapper.insertSelective(csOtherEntrust);
            }else{
                res = this.csOtherEntrustMapper.updateByPrimaryKeySelective(csOtherEntrust);
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
        int count = this.csOtherEntrustMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CsOtherEntrust selectByPrimaryKey(String entrustId) {
        return this.csOtherEntrustMapper.selectByPrimaryKey(entrustId);
    }

    public List<CsOtherEntrust> selectByExample(Criteria example) {
        return this.csOtherEntrustMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String entrustId) {
        return this.csOtherEntrustMapper.deleteByPrimaryKey(entrustId);
    }

    public int updateByPrimaryKeySelective(CsOtherEntrust record) {
        return this.csOtherEntrustMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CsOtherEntrust record,String entrustDel) {
        // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(entrustDel)){
            //更改数据状态
            record.setEntrustDel(entrustDel);
            return this.csOtherEntrustMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.csOtherEntrustMapper.deleteByPrimaryKey(record.getEntrustId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.csOtherEntrustMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CsOtherEntrust record, Criteria example) {
        return this.csOtherEntrustMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CsOtherEntrust record, Criteria example) {
        return this.csOtherEntrustMapper.updateByExample(record, example);
    }

    public int insert(CsOtherEntrust record) {
        return this.csOtherEntrustMapper.insert(record);
    }

    public int insertSelective(CsOtherEntrust record) {
        return this.csOtherEntrustMapper.insertSelective(record);
    }
}