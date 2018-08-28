package it.com.pyg.group;

import java.io.Serializable;
import java.util.List;

import it.com.pyg.entity.Specification;
import it.com.pyg.entity.SpecificationOption;

/*
 * 规格与规格选项的组合
 */
public class SpecificationGroup implements Serializable{

	private static final long serialVersionUID = 1L;

	private Specification specification;	//规格
	
	private List<SpecificationOption> specificationOptions;	//规格选项

	public SpecificationGroup() {
	}
	
	public SpecificationGroup(Specification specification, List<SpecificationOption> specificationOptions) {
		super();
		this.specification = specification;
		this.specificationOptions = specificationOptions;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public List<SpecificationOption> getSpecificationOptions() {
		return specificationOptions;
	}

	public void setSpecificationOptions(List<SpecificationOption> specificationOptions) {
		this.specificationOptions = specificationOptions;
	}

	@Override
	public String toString() {
		return "SpecificationGroup [specification=" + specification + ", specificationOptions=" + specificationOptions
				+ "]";
	}
	
}
