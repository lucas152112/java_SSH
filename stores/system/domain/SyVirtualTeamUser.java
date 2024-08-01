package com.oim.stores.system.domain;

// default package


public class SyVirtualTeamUser implements java.io.Serializable {

	// Fields

	private SyVirtualTeamUserId id;

	public SyVirtualTeamUser() {
	}

	/** full constructor */
	public SyVirtualTeamUser(SyVirtualTeamUserId id) {
		this.id = id;
	}

	public SyVirtualTeamUserId getId() {
		return this.id;
	}

	public void setId(SyVirtualTeamUserId id) {
		this.id = id;
	}

}