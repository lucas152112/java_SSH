package com.oim.stores.system.domain;

/**
 * SyRoleMenuRelationId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyRoleMenuRelationId implements java.io.Serializable {

	// Fields

	private Long relationType;
	private Long userroleId;
	private Long menuId;

	// Constructors

	/** default constructor */
	public SyRoleMenuRelationId() {
	}

	/** full constructor */
	public SyRoleMenuRelationId(Long relationType, Long userroleId,
			Long menuId) {
		this.relationType = relationType;
		this.userroleId = userroleId;
		this.menuId = menuId;
	}

	// Property accessors

	public Long getRelationType() {
		return this.relationType;
	}

	public void setRelationType(Long relationType) {
		this.relationType = relationType;
	}

	public Long getUserroleId() {
		return this.userroleId;
	}

	public void setUserroleId(Long userroleId) {
		this.userroleId = userroleId;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SyRoleMenuRelationId))
			return false;
		SyRoleMenuRelationId castOther = (SyRoleMenuRelationId) other;

		return ((this.getRelationType() == castOther.getRelationType()) || (this
				.getRelationType() != null
				&& castOther.getRelationType() != null && this
				.getRelationType().equals(castOther.getRelationType())))
				&& ((this.getUserroleId() == castOther.getUserroleId()) || (this
						.getUserroleId() != null
						&& castOther.getUserroleId() != null && this
						.getUserroleId().equals(castOther.getUserroleId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this
						.getMenuId() != null
						&& castOther.getMenuId() != null && this.getMenuId()
						.equals(castOther.getMenuId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getRelationType() == null ? 0 : this.getRelationType()
						.hashCode());
		result = 37
				* result
				+ (getUserroleId() == null ? 0 : this.getUserroleId()
						.hashCode());
		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		return result;
	}

}