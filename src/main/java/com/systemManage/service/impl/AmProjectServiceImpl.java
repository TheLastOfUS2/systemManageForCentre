package com.systemManage.service.impl;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.AmCollaboratorMapper;
import com.systemManage.dao.base.AmProjectMapper;
import com.systemManage.dao.ext.AmProjectMapperExt;
import com.systemManage.pojo.base.AmCollaborator;
import com.systemManage.pojo.base.AmProject;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;
import com.systemManage.pojo.dto.AmProjectDto;
import com.systemManage.service.AmProjectService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmProjectServiceImpl implements AmProjectService {
    @Autowired
    private AmProjectMapper amProjectMapper; //项目
    @Autowired
    private AmProjectMapperExt amProjectMapperExt; //项目扩展
    @Autowired
    private AmCollaboratorMapper amCollaboratorMapper; // 合作者

    private static final Logger logger = LoggerFactory.getLogger(AmProjectServiceImpl.class);
    
    public AmProjectDto selectByPrimaryKeyExt(String projectId){
        return this.amProjectMapperExt.selectByPrimaryKeyExt(projectId);
    }
    
    @Override
    public JSONObject insertSelective(AmProjectDto amProjectDto, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(amProjectDto!=null){
            String projectId=amProjectDto.getProjectId();
            if (StringUtil.isEmpty(projectId)) {
                //设置主键
            	amProjectDto.setProjectId(StringUtil.getUUID());
                //设置新增时间
            	amProjectDto.setProjectCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
            	amProjectDto.setProjectDel("0");
            }
            //保存基本信息
            if (StringUtil.isEmpty(projectId)) {
                res = this.amProjectMapper.insertSelective(amProjectDto);
            }else{
                res = this.amProjectMapper.updateByPrimaryKeySelective(amProjectDto);
            }
            //获取原来的合作者id,如果原来有,本次没有,则删除
            String collaboratorIds=request.getParameter("collaboratorIds");//原数据ids
            //保存教育经历
            if(StringUtil.isNotBlank(collaboratorIds) || (amProjectDto.getAmCollaborators()!=null && amProjectDto.getAmCollaborators().size()>0)){
                saveCollaborators(amProjectDto,request);
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
    
  //保存合作者
    public void saveCollaborators(AmProjectDto amProjectDto,HttpServletRequest request){
        //获取原来的教育经历id,如果原来有,本次没有,则删除
        String collaboratorIds=request.getParameter("collaboratorIds");//原数据ids
        //存放原id
        String [] collaboratorIdsStr=null;
        //存放该次提交id
        List<String> collaboratorIdsNewList=new ArrayList<String>();
        if(StringUtil.isNotBlank(collaboratorIds)){
            collaboratorIdsStr=collaboratorIds.split(",");
        }
        // 判断当jobIds不为空,biBaseInfoDto为空时,则删除该人员下的所有的社会兼职
        if(StringUtil.isNotBlank(collaboratorIds) && (amProjectDto.getAmCollaborators()==null || amProjectDto.getAmCollaborators().size()==0 )){
            //遍历 删除
            for(String str : collaboratorIdsStr) {  
                AmCollaborator amCollaborator=amCollaboratorMapper.selectByPrimaryKey(str);
                if(amCollaborator != null) {
                    amCollaboratorMapper.deleteByPrimaryKey(amCollaborator.getCollaboratorId());
                }
            } 
        }else{
            for(AmCollaborator be:amProjectDto.getAmCollaborators()){
                //判断是新增还是更新
                if(StringUtil.isEmpty(be.getCollaboratorId())){
                    //设置主键
                    be.setCollaboratorId(StringUtil.getUUID());
                    //设置外键id
                   // be.setBaseInfoId(biBaseInfoDto.getBaseInfoId());
                    //类型
                    be.setCollaboratorType(request.getParameter("collaboratorType"));
                    be.setAchievementId(amProjectDto.getProjectId());
                    be.setCollaboratorCreateTime(new Date());
                    this.amCollaboratorMapper.insertSelective(be);
                }else{
                    //将本次提交的id放到list中
                    collaboratorIdsNewList.add(be.getCollaboratorId());
                    this.amCollaboratorMapper.updateByPrimaryKeySelective(be);
                }
            }
            
            //判断当只有原来有数据的时候,才会去判断
            if(collaboratorIdsStr != null && collaboratorIdsStr.length>0){
               List<String> cList= compare(collaboratorIdsStr,collaboratorIdsNewList);
               if(cList!=null && cList.size()>0){
                   //遍历 删除
                   for(String str : cList) {  
                       AmCollaborator amCollaborator=amCollaboratorMapper.selectByPrimaryKey(str);
                       if(amCollaborator != null) {
                           amCollaboratorMapper.deleteByPrimaryKey(amCollaborator.getCollaboratorId());
                       }
                   } 
               }
            }
        }
    }
    
   //比较获取不同的值
    public  List<String> compare(String[] rfid,List<String> rs){
        //将两个数组合并  
        String[] twoArray = new String[rfid.length+rs.size()];  
        System.arraycopy(rfid, 0, twoArray, 0, rfid.length);  
        System.arraycopy(rs.toArray(new String[0]), 0, twoArray, rfid.length, rs.size());  
  
        //得到相同元素  
        rs.retainAll(Arrays.asList(rfid));  
  
        List<String> cList = new ArrayList<String>();  
        cList.addAll(Arrays.asList(twoArray));  
        cList.removeAll(rs);  
        return cList;
    }  
    public int countByExample(Criteria example) {
        int count = this.amProjectMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public AmProject selectByPrimaryKey(String projectId) {
        return this.amProjectMapper.selectByPrimaryKey(projectId);
    }

    public List<AmProjectDto> selectByExample(Criteria example) {
        return this.amProjectMapperExt.selectByExample(example);
    }

    public int deleteByPrimaryKey(String projectId) {
        return this.amProjectMapper.deleteByPrimaryKey(projectId);
    }

    public int updateByPrimaryKeySelective(AmProject record) {
        return this.amProjectMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(AmProject record,String projectDel) {
     // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(projectDel)){
            //更改数据状态
            record.setProjectDel(projectDel);
            return this.amProjectMapper.updateByPrimaryKey(record);
        }else{
            //从数据库中删除
            return this.amProjectMapper.deleteByPrimaryKey(record.getProjectId());
        }
    }

    public int deleteByExample(Criteria example) {
        return this.amProjectMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(AmProject record, Criteria example) {
        return this.amProjectMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(AmProject record, Criteria example) {
        return this.amProjectMapper.updateByExample(record, example);
    }

    public int insert(AmProject record) {
        return this.amProjectMapper.insert(record);
    }

    public int insertSelective(AmProject record) {
        return this.amProjectMapper.insertSelective(record);
    }

    
}