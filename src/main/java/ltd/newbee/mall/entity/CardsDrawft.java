package ltd.newbee.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CardsDrawft {
    private Integer drawftId;

    private Integer adminUserId;

    private String cardName;

    private Integer drawftCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public Integer getDrawftId() {
        return drawftId;
    }

    public void setDrawftId(Integer drawftId) {
        this.drawftId = drawftId;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDrawftCount() {
        return drawftCount;
    }

    public void setDrawftCount(Integer drawftCount) {
        this.drawftCount = drawftCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", drawftId=").append(drawftId);
        sb.append(", adminUserId=").append(adminUserId);
        sb.append(", cardName=").append(cardName);
        sb.append(", createTime=").append(createTime);
        sb.append(", drawftCount=").append(drawftCount);
        sb.append("]");
        return sb.toString();
    }
}
