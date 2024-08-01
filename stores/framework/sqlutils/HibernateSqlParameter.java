package com.oim.stores.framework.sqlutils;

public class HibernateSqlParameter {
	private String name;
	private String javaType;
	private String jdbcType;
	private String value;

	/**
	 * HibernateSqlParameter构造函数
	 * @param tag
	 */
	public HibernateSqlParameter(String tag) {
		String prefix = "#{";
		String suffix = "}";
		if (tag.startsWith(prefix) && tag.endsWith(suffix)) {
			String[] ary = tag.substring(prefix.length(),
					tag.length() - suffix.length()).split(",");
			String javaTypePrefix = "javaType=";
			String jdbcTypePrefix = "jdbcType=";
			this.name = ary[0].substring("".length(), ary[0].length());
			this.javaType = "String";
			this.jdbcType = "VARCHAR";
			if (ary.length > 1) {
				for (int i = 1; i < ary.length; i++) {
					if (ary[i].startsWith(javaTypePrefix)) {
						this.javaType = ary[i].substring(javaTypePrefix
								.length(), ary[i].length());
					} else if (ary[i].startsWith(jdbcTypePrefix)) {
						this.jdbcType = ary[i].substring(jdbcTypePrefix
								.length(), ary[i].length());
					}
				}
			}
		}

	}

	/**
	 * 构造函数
	 * @param name
	 * @param javaType
	 * @param jdbcType
	 */
	public HibernateSqlParameter(String name, String javaType, String jdbcType) {
		super();
		this.name = name;
		this.javaType = javaType;
		this.jdbcType = jdbcType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "HibernateSqlParameter [javaType=" + javaType + ", jdbcType="
				+ jdbcType + ", name=" + name + ", value=" + value + "]";
	}

	

}
