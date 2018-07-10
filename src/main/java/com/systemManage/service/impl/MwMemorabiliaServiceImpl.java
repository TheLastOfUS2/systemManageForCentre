package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.MwMemorabiliaMapper;
import com.systemManage.dao.ext.MwMemorabiliaMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwMemorabilia;
import com.systemManage.service.MwMemorabiliaService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MwMemorabiliaServiceImpl implements MwMemorabiliaService {
    @Autowired
    private MwMemorabiliaMapper mwMemorabiliaMapper;
    @Autowired
    private MwMemorabiliaMapperExt mwMemorabiliaMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(MwMemorabiliaServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(MwMemorabilia mwMemorabilia, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(mwMemorabilia!=null){
            String activityId=mwMemorabilia.getMemorabiliaId();
            if (StringUtil.isEmpty(activityId)) {
                //设置主键
                mwMemorabilia.setMemorabiliaId(StringUtil.getUUID());
                //设置新增时间
                mwMemorabilia.setMemorabiliaCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                mwMemorabilia.setMemorabiliaDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(activityId)) {
                res = this.mwMemorabiliaMapper.insertSelective(mwMemorabilia);
            }else{
                res = this.mwMemorabiliaMapper.updateByPrimaryKeySelective(mwMemorabilia);
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
        int count = this.mwMemorabiliaMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public MwMemorabilia selectByPrimaryKey(String memorabiliaId) {
        return this.mwMemorabiliaMapper.selectByPrimaryKey(memorabiliaId);
    }

    public List<MwMemorabilia> selectByExample(Criteria example) {
        return this.mwMemorabiliaMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String memorabiliaId) {
        return this.mwMemorabiliaMapper.deleteByPrimaryKey(memorabiliaId);
    }

    public int updateByPrimaryKeySelective(MwMemorabilia record) {
        return this.mwMemorabiliaMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MwMemorabilia record,String memorabiliaDel) {
        // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(memorabiliaDel)){
            //更改数据状态
            record.setMemorabiliaDel(memorabiliaDel);
            return this.mwMemorabiliaMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.mwMemorabiliaMapper.deleteByPrimaryKey(record.getMemorabiliaId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.mwMemorabiliaMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(MwMemorabilia record, Criteria example) {
        return this.mwMemorabiliaMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(MwMemorabilia record, Criteria example) {
        return this.mwMemorabiliaMapper.updateByExample(record, example);
    }

    public int insert(MwMemorabilia record) {
        return this.mwMemorabiliaMapper.insert(record);
    }

    public int insertSelective(MwMemorabilia record) {
        return this.mwMemorabiliaMapper.insertSelective(record);
    }
}