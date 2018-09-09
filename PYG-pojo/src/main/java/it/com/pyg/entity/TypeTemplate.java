package it.com.pyg.entity;

import java.io.Serializable;

public class TypeTemplate implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String specIds;

    private String brandIds;

    private String customAttributeItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpecIds() {
        return specIds;
    }

    public void setSpecIds(String specIds) {
        this.specIds = specIds == null ? null : specIds.trim();
    }

    public String getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(String brandIds) {
        this.brandIds = brandIds == null ? null : brandIds.trim();
    }

    public String getCustomAttributeItems() {
        return customAttributeItems;
    }

    public void setCustomAttributeItems(String customAttributeItems) {
        this.customAttributeItems = customAttributeItems == null ? null : customAttributeItems.trim();
    }

	@Override
	public String toString() {
		return "TypeTemplate [id=" + id + ", name=" + name + ", specIds=" + specIds + ", brandIds=" + brandIds
				+ ", customAttributeItems=" + customAttributeItems + "]";
	}
}