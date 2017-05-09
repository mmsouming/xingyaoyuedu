package com.fancyfamily.primarylibrary.commentlibrary.commentbean;

public class ScoreVo {
   private String remark;//备注
   
   private String time;//记录时间
   
   private Long timestamp;//时间戳
   
   private Integer amount;//变化量

    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    
    public Integer getAmount() {
        return amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
   
}
