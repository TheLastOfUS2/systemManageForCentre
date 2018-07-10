package com.systemManage.pojo.dto;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.systemManage.pojo.base.BiAbroad;
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.BiEducational;
import com.systemManage.pojo.base.BiJob;
import com.systemManage.pojo.base.BiWork;

/**
 * 基本信息实体类扩展类
 * @author Zhang JinQiu
 * 日     期:2017年5月24日 下午14:28:00
 */
//查询关联关系表需要用到该注解
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BiBaseInfoDto extends BiBaseInfo {
    private static final long serialVersionUID = 1L;
    
    /**
     * 科研人员、行政人员、其他人员绑定的账号
     */
    private String accountName; 
    
    /**
     * 教育经历
     */
    private List<BiEducational> biEducationals;
    
    /**
     * 工作经历
     */
    private List<BiWork> biWorks;
    
    /**
     * 社会兼职
     */
    private List<BiJob> biJobs;
    
    /**
     * 出国经历
     */
    private List<BiAbroad> biAbroads;

    public List<BiEducational> getBiEducationals() {
        return biEducationals;
    }

    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setBiEducationals(List<BiEducational> biEducationals) {
        this.biEducationals = biEducationals;
    }

    public List<BiWork> getBiWorks() {
        return biWorks;
    }

    public void setBiWorks(List<BiWork> biWorks) {
        this.biWorks = biWorks;
    }

    public List<BiJob> getBiJobs() {
        return biJobs;
    }

    public void setBiJobs(List<BiJob> biJobs) {
        this.biJobs = biJobs;
    }

    public List<BiAbroad> getBiAbroads() {
        return biAbroads;
    }

    public void setBiAbroads(List<BiAbroad> biAbroads) {
        this.biAbroads = biAbroads;
    }
}