package com.adi3000.charivariperm.api.models;

import java.util.List;

public class Family {

	private Long id;
	private String label;
	private List<Child> children;
	private List<Adult> parents;
	private List<Scheduling> slots;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the children
	 */
	public List<Child> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<Child> children) {
		this.children = children;
	}
	/**
	 * @return the parents
	 */
	public List<Adult> getParents() {
		return parents;
	}
	/**
	 * @param parents the parents to set
	 */
	public void setParents(List<Adult> parents) {
		this.parents = parents;
	}
	/**
	 * @return the slots
	 */
	public List<Scheduling> getSlots() {
		return slots;
	}
	/**
	 * @param slots the slots to set
	 */
	public void setSlots(List<Scheduling> slots) {
		this.slots = slots;
	}
	
}
