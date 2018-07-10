package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.AmConferenceMapper;
import com.systemManage.dao.ext.AmConferenceMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.AmConference;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.AmConferenceService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmConferenceServiceImpl implements AmConferenceService {
    @Autowired
    private AmConferenceMapper amConferenceMapper; // 学术会议
    @Autowired
    private AmConferenceMapperExt amConferenceMapperExt; // 学术会议

    private static final Logger logger = LoggerFactory.getLogger(AmConferenceServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(AmConference amConference, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(amConference!=null){
            String conferenceId=amConference.getConferenceId();
            if (StringUtil.isEmpty(conferenceId)) {
                //设置主键
                amConference.setConferenceId(StringUtil.getUUID());
                //设置新增时间
                amConference.setConferenceCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                amConference.setConferenceDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(conferenceId)) {
                res = this.amConferenceMapper.insertSelective(amConference);
            }else{
                res = this.amConferenceMapper.updateByPrimaryKeySelective(amConference);
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
        int count = this.amConferenceMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public AmConference selectByPrimaryKey(String conferenceId) {
        return this.amConferenceMapper.selectByPrimaryKey(conferenceId);
    }

    public List<AmConference> selectByExample(Criteria example) {
        return this.amConferenceMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String conferenceId) {
        return this.amConferenceMapper.deleteByPrimaryKey(conferenceId);
    }

    public int updateByPrimaryKeySelective(AmConference record) {
        return this.amConferenceMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(AmConference record,String conferenceDel) {
     // conferenceDel=0:恢复;conferenceDel=1:删除;conferenceDel=2:彻底删除
        if(!("2").equals(conferenceDel)){
            //更改数据状态
            record.setConferenceDel(conferenceDel);
            return this.amConferenceMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.amConferenceMapper.deleteByPrimaryKey(record.getConferenceId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.amConferenceMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(AmConference record, Criteria example) {
        return this.amConferenceMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(AmConference record, Criteria example) {
        return this.amConferenceMapper.updateByExample(record, example);
    }

    public int insert(AmConference record) {
        return this.amConferenceMapper.insert(record);
    }

    public int insertSelective(AmConference record) {
        return this.amConferenceMapper.insertSelective(record);
    }
}