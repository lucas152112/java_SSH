package com.oim.stores.user.domain;

import java.sql.Timestamp;

/**
 * ViewStoreUserInfoId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ViewStoreUserInfoId implements java.io.Serializable {

	// Fields

	private Long storeId;
	private String storeName;
	private Long storeStatus;
	private Long roleId;
	private String roleName;
	private Long roleType;
	private Long userId;
	private String userMail;
	private String userName;
	private String userRealname;
	private String userPwd;
	private String pwdQuestion;
	private String pwdAnswer;
	private String mobile;
	private String telephone;
	private String province;
	private String city;
	private String county;
	private Timestamp regDate;
	private Long userFrom;
	private String payPwd;
	private Long userSex;
	private Timestamp birthday;
	private Long mobileFlag;
	private Long mailFlag;
	private String cardId;
	private Long userStatus;
	private Timestamp updateDate;
	private Long updateUser;

	// Constructors

	/** default constructor */
	public ViewStoreUserInfoId() {
	}

	/** minimal constructor */
	public ViewStoreUserInfoId(Long storeId, String storeName,
			Long storeStatus, Long roleId, String roleName,
			Long userId, String userMail, String userName,
			String userPwd, Long userStatus, Timestamp updateDate,
			Long updateUser) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeStatus = storeStatus;
		this.roleId = roleId;
		this.roleName = roleName;
		this.userId = userId;
		this.userMail = userMail;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userStatus = userStatus;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	/** full constructor */
	public ViewStoreUserInfoId(Long storeId, String storeName,
			Long storeStatus, Long roleId, String roleName,
			Long roleType, Long userId, String userMail,
			String userName, String userRealname, String userPwd,
			String pwdQuestion, String pwdAnswer, String mobile,
			String telephone, String province, String city, String county,
			Timestamp regDate, Long userFrom, String payPwd,
			Long userSex, Timestamp birthday, Long mobileFlag,
			Long mailFlag, String cardId, Long userStatus,
			Timestamp updateDate, Long updateUser) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeStatus = storeStatus;
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleType = roleType;
		this.userId = userId;
		this.userMail = userMail;
		this.userName = userName;
		this.userRealname = userRealname;
		this.userPwd = userPwd;
		this.pwdQuestion = pwdQuestion;
		this.pwdAnswer = pwdAnswer;
		this.mobile = mobile;
		this.telephone = telephone;
		this.province = province;
		this.city = city;
		this.county = county;
		this.regDate = regDate;
		this.userFrom = userFrom;
		this.payPwd = payPwd;
		this.userSex = userSex;
		this.birthday = birthday;
		this.mobileFlag = mobileFlag;
		this.mailFlag = mailFlag;
		this.cardId = cardId;
		this.userStatus = userStatus;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getStoreStatus() {
		return this.storeStatus;
	}

	public void setStoreStatus(Long storeStatus) {
		this.storeStatus = storeStatus;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleType() {
		return this.roleType;
	}

	public void setRoleType(Long roleType) {
		this.roleType = roleType;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserMail() {
		return this.userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealname() {
		return this.userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getPwdQuestion() {
		return this.pwdQuestion;
	}

	public void setPwdQuestion(String pwdQuestion) {
		this.pwdQuestion = pwdQuestion;
	}

	public String getPwdAnswer() {
		return this.pwdAnswer;
	}

	public void setPwdAnswer(String pwdAnswer) {
		this.pwdAnswer = pwdAnswer;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Timestamp getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Long getUserFrom() {
		return this.userFrom;
	}

	public void setUserFrom(Long userFrom) {
		this.userFrom = userFrom;
	}

	public String getPayPwd() {
		return this.payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public Long getUserSex() {
		return this.userSex;
	}

	public void setUserSex(Long userSex) {
		this.userSex = userSex;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Long getMobileFlag() {
		return this.mobileFlag;
	}

	public void setMobileFlag(Long mobileFlag) {
		this.mobileFlag = mobileFlag;
	}

	public Long getMailFlag() {
		return this.mailFlag;
	}

	public void setMailFlag(Long mailFlag) {
		this.mailFlag = mailFlag;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Long getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewStoreUserInfoId))
			return false;
		ViewStoreUserInfoId castOther = (ViewStoreUserInfoId) other;

		return ((this.getStoreId() == castOther.getStoreId()) || (this
				.getStoreId() != null
				&& castOther.getStoreId() != null && this.getStoreId().equals(
				castOther.getStoreId())))
				&& ((this.getStoreName() == castOther.getStoreName()) || (this
						.getStoreName() != null
						&& castOther.getStoreName() != null && this
						.getStoreName().equals(castOther.getStoreName())))
				&& ((this.getStoreStatus() == castOther.getStoreStatus()) || (this
						.getStoreStatus() != null
						&& castOther.getStoreStatus() != null && this
						.getStoreStatus().equals(castOther.getStoreStatus())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null
						&& castOther.getRoleId() != null && this.getRoleId()
						.equals(castOther.getRoleId())))
				&& ((this.getRoleName() == castOther.getRoleName()) || (this
						.getRoleName() != null
						&& castOther.getRoleName() != null && this
						.getRoleName().equals(castOther.getRoleName())))
				&& ((this.getRoleType() == castOther.getRoleType()) || (this
						.getRoleType() != null
						&& castOther.getRoleType() != null && this
						.getRoleType().equals(castOther.getRoleType())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())))
				&& ((this.getUserMail() == castOther.getUserMail()) || (this
						.getUserMail() != null
						&& castOther.getUserMail() != null && this
						.getUserMail().equals(castOther.getUserMail())))
				&& ((this.getUserName() == castOther.getUserName()) || (this
						.getUserName() != null
						&& castOther.getUserName() != null && this
						.getUserName().equals(castOther.getUserName())))
				&& ((this.getUserRealname() == castOther.getUserRealname()) || (this
						.getUserRealname() != null
						&& castOther.getUserRealname() != null && this
						.getUserRealname().equals(castOther.getUserRealname())))
				&& ((this.getUserPwd() == castOther.getUserPwd()) || (this
						.getUserPwd() != null
						&& castOther.getUserPwd() != null && this.getUserPwd()
						.equals(castOther.getUserPwd())))
				&& ((this.getPwdQuestion() == castOther.getPwdQuestion()) || (this
						.getPwdQuestion() != null
						&& castOther.getPwdQuestion() != null && this
						.getPwdQuestion().equals(castOther.getPwdQuestion())))
				&& ((this.getPwdAnswer() == castOther.getPwdAnswer()) || (this
						.getPwdAnswer() != null
						&& castOther.getPwdAnswer() != null && this
						.getPwdAnswer().equals(castOther.getPwdAnswer())))
				&& ((this.getMobile() == castOther.getMobile()) || (this
						.getMobile() != null
						&& castOther.getMobile() != null && this.getMobile()
						.equals(castOther.getMobile())))
				&& ((this.getTelephone() == castOther.getTelephone()) || (this
						.getTelephone() != null
						&& castOther.getTelephone() != null && this
						.getTelephone().equals(castOther.getTelephone())))
				&& ((this.getProvince() == castOther.getProvince()) || (this
						.getProvince() != null
						&& castOther.getProvince() != null && this
						.getProvince().equals(castOther.getProvince())))
				&& ((this.getCity() == castOther.getCity()) || (this.getCity() != null
						&& castOther.getCity() != null && this.getCity()
						.equals(castOther.getCity())))
				&& ((this.getCounty() == castOther.getCounty()) || (this
						.getCounty() != null
						&& castOther.getCounty() != null && this.getCounty()
						.equals(castOther.getCounty())))
				&& ((this.getRegDate() == castOther.getRegDate()) || (this
						.getRegDate() != null
						&& castOther.getRegDate() != null && this.getRegDate()
						.equals(castOther.getRegDate())))
				&& ((this.getUserFrom() == castOther.getUserFrom()) || (this
						.getUserFrom() != null
						&& castOther.getUserFrom() != null && this
						.getUserFrom().equals(castOther.getUserFrom())))
				&& ((this.getPayPwd() == castOther.getPayPwd()) || (this
						.getPayPwd() != null
						&& castOther.getPayPwd() != null && this.getPayPwd()
						.equals(castOther.getPayPwd())))
				&& ((this.getUserSex() == castOther.getUserSex()) || (this
						.getUserSex() != null
						&& castOther.getUserSex() != null && this.getUserSex()
						.equals(castOther.getUserSex())))
				&& ((this.getBirthday() == castOther.getBirthday()) || (this
						.getBirthday() != null
						&& castOther.getBirthday() != null && this
						.getBirthday().equals(castOther.getBirthday())))
				&& ((this.getMobileFlag() == castOther.getMobileFlag()) || (this
						.getMobileFlag() != null
						&& castOther.getMobileFlag() != null && this
						.getMobileFlag().equals(castOther.getMobileFlag())))
				&& ((this.getMailFlag() == castOther.getMailFlag()) || (this
						.getMailFlag() != null
						&& castOther.getMailFlag() != null && this
						.getMailFlag().equals(castOther.getMailFlag())))
				&& ((this.getCardId() == castOther.getCardId()) || (this
						.getCardId() != null
						&& castOther.getCardId() != null && this.getCardId()
						.equals(castOther.getCardId())))
				&& ((this.getUserStatus() == castOther.getUserStatus()) || (this
						.getUserStatus() != null
						&& castOther.getUserStatus() != null && this
						.getUserStatus().equals(castOther.getUserStatus())))
				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this
						.getUpdateDate() != null
						&& castOther.getUpdateDate() != null && this
						.getUpdateDate().equals(castOther.getUpdateDate())))
				&& ((this.getUpdateUser() == castOther.getUpdateUser()) || (this
						.getUpdateUser() != null
						&& castOther.getUpdateUser() != null && this
						.getUpdateUser().equals(castOther.getUpdateUser())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
		result = 37 * result
				+ (getStoreName() == null ? 0 : this.getStoreName().hashCode());
		result = 37
				* result
				+ (getStoreStatus() == null ? 0 : this.getStoreStatus()
						.hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getRoleName() == null ? 0 : this.getRoleName().hashCode());
		result = 37 * result
				+ (getRoleType() == null ? 0 : this.getRoleType().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getUserMail() == null ? 0 : this.getUserMail().hashCode());
		result = 37 * result
				+ (getUserName() == null ? 0 : this.getUserName().hashCode());
		result = 37
				* result
				+ (getUserRealname() == null ? 0 : this.getUserRealname()
						.hashCode());
		result = 37 * result
				+ (getUserPwd() == null ? 0 : this.getUserPwd().hashCode());
		result = 37
				* result
				+ (getPwdQuestion() == null ? 0 : this.getPwdQuestion()
						.hashCode());
		result = 37 * result
				+ (getPwdAnswer() == null ? 0 : this.getPwdAnswer().hashCode());
		result = 37 * result
				+ (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result
				+ (getTelephone() == null ? 0 : this.getTelephone().hashCode());
		result = 37 * result
				+ (getProvince() == null ? 0 : this.getProvince().hashCode());
		result = 37 * result
				+ (getCity() == null ? 0 : this.getCity().hashCode());
		result = 37 * result
				+ (getCounty() == null ? 0 : this.getCounty().hashCode());
		result = 37 * result
				+ (getRegDate() == null ? 0 : this.getRegDate().hashCode());
		result = 37 * result
				+ (getUserFrom() == null ? 0 : this.getUserFrom().hashCode());
		result = 37 * result
				+ (getPayPwd() == null ? 0 : this.getPayPwd().hashCode());
		result = 37 * result
				+ (getUserSex() == null ? 0 : this.getUserSex().hashCode());
		result = 37 * result
				+ (getBirthday() == null ? 0 : this.getBirthday().hashCode());
		result = 37
				* result
				+ (getMobileFlag() == null ? 0 : this.getMobileFlag()
						.hashCode());
		result = 37 * result
				+ (getMailFlag() == null ? 0 : this.getMailFlag().hashCode());
		result = 37 * result
				+ (getCardId() == null ? 0 : this.getCardId().hashCode());
		result = 37
				* result
				+ (getUserStatus() == null ? 0 : this.getUserStatus()
						.hashCode());
		result = 37
				* result
				+ (getUpdateDate() == null ? 0 : this.getUpdateDate()
						.hashCode());
		result = 37
				* result
				+ (getUpdateUser() == null ? 0 : this.getUpdateUser()
						.hashCode());
		return result;
	}

}