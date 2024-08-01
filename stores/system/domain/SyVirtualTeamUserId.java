package com.oim.stores.system.domain;


public class SyVirtualTeamUserId implements java.io.Serializable {

	// Fields

	private Long teamId;
	private Long userId;

	// Constructors

	/** default constructor */
	public SyVirtualTeamUserId() {
	}

	/** full constructor */
	public SyVirtualTeamUserId(Long teamId, Long userId) {
		this.teamId = teamId;
		this.userId = userId;
	}

	// Property accessors

	public Long getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SyVirtualTeamUserId))
			return false;
		SyVirtualTeamUserId castOther = (SyVirtualTeamUserId) other;

		return ((this.getTeamId() == castOther.getTeamId()) || (this.getTeamId() != null && castOther.getTeamId() != null && this.getTeamId().equals(castOther.getTeamId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null && castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTeamId() == null ? 0 : this.getTeamId().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}