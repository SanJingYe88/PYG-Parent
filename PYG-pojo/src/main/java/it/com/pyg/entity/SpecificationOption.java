package it.com.pyg.entity;

import java.io.Serializable;

/*
 * 规格选项
 */
public class SpecificationOption implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private String optionName;	//规格选项名称

    private Long specId;	//所属的规格

    private Integer orders;	//排序优先级

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
}