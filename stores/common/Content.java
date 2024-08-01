package com.oim.stores.common;

/**
 * 公共全局数据信息
 */
public class Content {
	public static final String LOGIN_USER = "loginUser";
	// 图片服务器地址
	public static final String FILE_SERVER_PATH = Tool.getConf("file_server_path", "config.properties");
	// 上传文件地址
	public static final String UPLOAD_SERVER_PATH = Tool.getConf("upload_server_path", "config.properties");

	// SY_PARAMETER 参数表类型 GROUP_NAME
	public static final String GROUP_NAME_SENDTYPE = "SendType";// 寄送方式
	public static final String GROUP_NAME_PRODUCTSTATUS = "ProductStatus";// 产品状态
	public static final String GROUP_NAME_AUDITSTATUS = "AuditStatus";// 产品状态
	// public static final String DEFAULT_BUSINESS_ADMIN = "默认管理员";// 产品状态
	public static final String DEFAULT_BUSINESS_ADMIN1 = "2";// 默认管理员
	public static final String JT_INTER_ID = "1";// 集团接口上报任务ID
	public static final String FLYDZ_TASK_ID = "5";// 翼支付任务ID

	public static final String LOGIN_STORE = "loginStore";
	/** 商品详细URL */
	public static final String PRODUCT_DETAIL_URL = CommonUtils.getConf("product_detail_url", "config.properties");
	public static final String MODULE_NO_PRODUCTID = "0";

	/** 商户密码加密key */
	public static final String COOM_KEYVALUE = "DF2FBAF816F2F1326EF180D070A87A8638F1C26EC1269D2F";

	/** 退款webService URL */
	public static final String REFUND_URL = CommonUtils.getConf("refund_Url", "config.properties");

	/** 商家推广地址 */
	public static final String STORE_URL = CommonUtils.getConf("store_URL", "config.properties");

	/**
	 * **************************商品配置*****************************
	 */
	// 充值卡商品编号
	public static final String CARD_NUMBER_PRODUCT_ID = CommonUtils.getConf("card_number_product_id", "config.properties");
	// 靓号商品编号
	public static final String RECHARGE_PRODUCT_ID = CommonUtils.getConf("recharge_product_id", "config.properties");

	/**
	 * **************************商品属性配置*****************************
	 */
	// 购买方式新
	public static final String SALE_MODE = CommonUtils.getConf("sale_mode", "config.properties");
	// 套餐属性
	public static final String ATTR_ID_GROUP = CommonUtils.getConf("attr_id_group", "config.properties");
	// 号码属性
	public static final String ATTR_ID_PHONE_NUMBER = CommonUtils.getConf("attr_id_phone_number", "config.properties");
	// 颜色属性
	public static final String ATTR_ID_COLOR = CommonUtils.getConf("attr_id_color", "config.properties");
	// 赠品
	public static final String ATTR_ID_PROMO = CommonUtils.getConf("attr_id_promo", "config.properties");

	/**
	 * **************************订单配置*****************************
	 */
	// 自动关闭订单超时时间(分钟)-----当定单超过24小时未付款时，订单自动关闭并进行天翼号码的解锁
	public static final String ORDER_AUTO_CLOSE_TIMEOUT = CommonUtils.getConf("order_auto_close_timeout", "config.properties");
	// 手动可关闭订单超时时间(分钟)
	public static final String ORDER_MANUAL_CLOSE_TIMEOUT = CommonUtils.getConf("order_manual_close_timeout", "config.properties");

	public static final String IMPORT_SHORDER_INTERVAL = CommonUtils.getConf("import_shOrder_Interval", "config.properties");

	/**
	 * **************************商家模块管理配置**********************
	 */

	/** 商家控制权限 */
	public static final String MODULE_STORE_AUTHORITY = "1";

	/** 商家修改权限 */
	public static final String MODULE_STORE_EDIT = "2";

	/*** 商家模块是否显示 ：是 */
	public static final String MODULE_IS_SHOW_TRUE = "1";

	/*** 商家模块是否显示 ：否 */
	public static final String MODULE_IS_SHOW_FALSE = "2";

	/*** 商家模块是否显示 ：显示全部 */
	public static final String MODULE_IS_SHOW_ALL = "0";

	/** 模块类别: 商品 */
	public static final String MODULE_TYPE_PRODUCT = "1";

	/** 模块类别: 搜索栏右侧广告 */
	public static final String MODULE_TYPE_SEARCH_RIGHT = "2";

	/** 模块类别: 头部广告 */
	public static final String MODULE_TYPE_CONTENT_TOP = "3";

	/** 模块类别: 左侧广告 */
	public static final String MODULE_TYPE_CONTENT_LEFT = "4";

	/** 模块类别: 商家标识 */
	public static final String MODULE_TYPE_SHOP = "5";

	/** 模块类别: 商家标识背景 */
	public static final String MODULE_TYPE_SHOP_BACKGROUND = "6";

	/** 模块类别：分销商品模块 */
	public static final String MODULE_TYPE_SHARE_PRODUCT = "7";

	
	/** 模块类别：商家模块新增时默认头部广告条图片 */
	public static final String MODULE_DEFAULT_TOP_ADVERT = CommonUtils.getConf("module_default_top_advert", "config.properties");

}
