package it.com.pyg.entity;

import java.io.Serializable;

/*
 * 规格
 */
public class Specification implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private String specName;	//规格名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }
}