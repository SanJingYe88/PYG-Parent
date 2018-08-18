package it.com.pyg.entity;

import java.io.Serializable;

/*
 * 品牌实体类
 */
public class Brand implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;		//编号

    private String name;	//品牌名

    private String firstChar;	//首字母

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

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar == null ? null : firstChar.trim();
    }

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", firstChar=" + firstChar + "]";
	}
}