package it.com.pyg.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页结果
 */
@SuppressWarnings("rawtypes")
public class PageResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private long total;// 总记录数
	private List rows;// 当前页记录

	public PageResult(long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	@SuppressWarnings("unchecked")
	public List<Object>  getRows() {
		return rows;
	}

	public void setRows(List<Object>  rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageResult [total=" + total + ", rows=" + rows + "]";
	}
}
