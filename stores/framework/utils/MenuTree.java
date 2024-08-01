package com.oim.stores.framework.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.oim.stores.system.domain.SyMenuList;

public class MenuTree {
    /**
     * @author zhuangjf
     * @return 供easyui tree使用的json数组
     * @param menuList
     *            菜单队列
     * @param checkedMap
     *            选中菜单的隐射表,key和value均取值menuid
     * @param parentId
     *            起始父节点id
     * 
     */
    public String getTreeContent(List<SyMenuList> menuList,
	    Map<String, String> checkedMap, Long parentId) {
	Iterator<String> itKey = checkedMap.keySet().iterator();
	StringBuffer sb = new StringBuffer();
	List<SyMenuList> pList = new ArrayList<SyMenuList>();
	// 从菜单列中删除已取得的项目,并装入临时队列
	for (int i = 0; i < menuList.size(); i++) {
	    SyMenuList menu = menuList.get(i);
	    if (menu.getParentId().longValue() == parentId.longValue()) {
		pList.add(menu);
		menuList.remove(i);
		i--;
	    }
	}
	for (SyMenuList menu : pList) {
	    Long menuId = menu.getMenuId();
	    if (!sb.toString().equals(""))
		sb.append(",");
	    sb.append("{\r\n\"id\":" + menu.getMenuId());
	    sb.append(",\"text\":\"" + menu.getMenuName() + "\"");
	    if (checkedMap.containsKey(String.valueOf(menuId))) {
		sb.append(",\"checked\":\"true\"");
	    }
	    // 递归
	    String rContent = getTreeContent(menuList, checkedMap, menuId);
	    if (!rContent.equals("")) {
		sb.append(",\"state\":\"closed\"");
		String attr = menu.getMenuUrl() == null ? "" : menu
			.getMenuUrl();
		sb.append(",\"attributes\":\"" + attr + "\"");
		sb.append(",\"children\":");
		sb.append("[");
		sb.append(rContent);
		sb.append("]");
	    } else {
		sb.append(",\"state\":\"open\"");
	    }
	    sb.append("}");
	}
	return sb.toString();
    }
}
