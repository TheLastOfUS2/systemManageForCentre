package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.PcTeachingMapper;
import com.systemManage.dao.ext.PcTeachingMapperExt;
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.PcTeaching;
import com.systemManage.service.PcTeachingService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PcTeachingServiceImpl implements PcTeachingService {
    @Autowired
    private PcTeachingMapper pcTeachingMapper; // 授课
    @Autowired
    private PcTeachingMapperExt pcTeachingMapperExt; // 授课

    private static final Logger logger = LoggerFactory.getLogger(PcTeachingServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(PcTeaching pcTeaching, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(pcTeaching!=null){
            String teachingId=pcTeaching.getTeachingId();
            if (StringUtil.isEmpty(teachingId)) {
                //设置主键
                pcTeaching.setTeachingId(StringUtil.getUUID());
                //设置新增时间
                pcTeaching.setTeachingCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
                pcTeaching.setTeachingDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(teachingId)) {
                res = this.pcTeachingMapper.insertSelective(pcTeaching);
            }else{
                res = this.pcTeachingMapper.updateByPrimaryKeySelective(pcTeaching);
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
        int count = this.pcTeachingMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public PcTeaching selectByPrimaryKey(String teachingId) {
        return this.pcTeachingMapper.selectByPrimaryKey(teachingId);
    }

    public List<PcTeaching> selectByExample(Criteria example) {
        return this.pcTeachingMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String teachingId) {
        return this.pcTeachingMapper.deleteByPrimaryKey(teachingId);
    }

    public int updateByPrimaryKeySelective(PcTeaching record) {
        return this.pcTeachingMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PcTeaching record,String teachingDel) {
     // teachingDel=0:恢复;teachingDel=1:删除;teachingDel=2:彻底删除
        if(!("2").equals(teachingDel)){
            //更改数据状态
            record.setTeachingDel(teachingDel);
            return this.pcTeachingMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.pcTeachingMapper.deleteByPrimaryKey(record.getTeachingId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.pcTeachingMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(PcTeaching record, Criteria example) {
        return this.pcTeachingMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(PcTeaching record, Criteria example) {
        return this.pcTeachingMapper.updateByExample(record, example);
    }

    public int insert(PcTeaching record) {
        return this.pcTeachingMapper.insert(record);
    }

    public int insertSelective(PcTeaching record) {
        return this.pcTeachingMapper.insertSelective(record);
    }
}