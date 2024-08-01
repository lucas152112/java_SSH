package com.oim.stores.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.oim.stores.common.DateUtil;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRight;

@SuppressWarnings("serial")
public class SyUserInfo  implements java.io.Serializable {

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
     private Date regDate;
     private Long userFrom;
     private String payPwd;
     private Long userSex;
     private Date birthday;
     private Long userStatus;
     private Long mobileFlag;
     private Long mailFlag;
     private Date updateDate;
     private String cardId;
     
     private String birthdayFormat;
     //private List<RoleMenuBean> menus= new ArrayList<RoleMenuBean>();
     private List<SyMenuList> menus= new ArrayList<SyMenuList>();
     private List<SyRight> rights=new ArrayList<SyRight>();
     private String roleName;


    /** default constructor */
    public SyUserInfo() {
    }

	/** minimal constructor */
    public SyUserInfo(String userName, String userRealname, String userPwd, Long userSex, Long userStatus, Date updateDate) {
        this.userName = userName;
        this.userRealname = userRealname;
        this.userPwd = userPwd;
        this.userSex = userSex;
        this.userStatus = userStatus;
        this.updateDate = updateDate;
    }
    
    /** full constructor */
    public SyUserInfo(String userMail, String userName, String userRealname, String userPwd, String pwdQuestion, String pwdAnswer, String mobile, String telephone, String province, String city, String county, Date regDate, Long userFrom, String payPwd, Long userSex, Date birthday, Long userStatus, Long mobileFlag, Long mailFlag, Date updateDate) {
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
        this.userStatus = userStatus;
        this.mobileFlag = mobileFlag;
        this.mailFlag = mailFlag;
        this.updateDate = updateDate;
    }

    @SuppressWarnings("unchecked")
	public String getUserPrivilage(){
		JSONArray array = new JSONArray();
		for(int i=0;i<menus.size();i++){
			if(menus.get(i).getMenuUrl()!=null&&!"".equals(menus.get(i).getMenuUrl())){
				Map map = new HashMap();
				map.put("menuID", menus.get(i).getMenuId());
				map.put("menuUrl", menus.get(i).getMenuUrl());
				array.put(map);
			}
		}
		return array.toString();
	}
   
    // Property accessors

    public Long getUserId() {
        return this.userId;
    }
    
//    public List<RoleMenuBean> getMenus() {
//		return menus;
//	}
//
//	public void setMenus(List<RoleMenuBean> menus) {
//		this.menus = menus;
//	}

	public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SyMenuList> getMenus() {
		return menus;
	}

	public void setMenus(List<SyMenuList> menus) {
		this.menus = menus;
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

    public Date getRegDate() {
        return this.regDate;
    }
    
    public void setRegDate(Date regDate) {
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

    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getUserStatus() {
        return this.userStatus;
    }
    
    public void setUserStatus(Long userStatus) {
        this.userStatus = userStatus;
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

    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public String getBirthdayFormat() {
		if(birthday != null && !birthday.equals("")){
			birthdayFormat = DateUtil.getStringDateFormat(birthday, "yyyy-MM-dd");
		}
		return birthdayFormat;
	}

	public void setBirthdayFormat(String birthdayFormat) {
		this.birthdayFormat = birthdayFormat;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the rights
	 */
	public List<SyRight> getRights() {
	    return rights;
	}

	/**
	 * @param rights the rights to set
	 */
	public void setRights(List<SyRight> rights) {
	    this.rights = rights;
	}
	
	public String getUserRights(){
		JSONArray array = new JSONArray();
		for(int i=0;i<rights.size();i++){
			if(rights.get(i).getRightValue()!=null&&!"".equals(rights.get(i).getRightValue())){
				Map map = new HashMap();
				map.put("menuID", rights.get(i).getRightId());
				map.put("menuUrl", rights.get(i).getRightValue());
				array.put(map);
			}
		}
		return array.toString();
	}
}