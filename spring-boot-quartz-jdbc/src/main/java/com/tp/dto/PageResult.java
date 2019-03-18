package com.tp.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PageUtil
 * @Description: TODO(Easyui鍒嗛〉鍩烘湰绫�)
 * @author vane(zhaoxiong1003@126.com)
 * @date 2014骞�8鏈�1鏃� 涓嬪崍3:57:07
 */
public class PageResult<T> implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;

	/**褰撳墠椤�*/
	private Integer page 		= 1;
	/**姣忛〉鏄剧ず澶氬皯鏉★紝榛樿鏄�10鏉�*/
	private Integer rows 		= 10;
	/**鎬婚〉鏁�*/
	private Integer totalPage 	= 0;
	/**鎬昏褰曟暟*/
	private Integer totalRow 	= 0; 
	/**鎺掑簭瀛楁*/
	private String  sort;
	/**ASC DESC*/
	private String  order;
	/**鏌ヨ鍙傛暟*/
	private Map<String, Object> params;
	/** 存放数据的结果集*/
	private List<T> list;
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**鍒嗛〉*/
	public void setTotalRows(Integer totalRow) {
		this.totalRow 	= totalRow;
		this.page 		= (page - 1) * rows;
		this.totalPage 	= ((totalRow - 1) / rows) + 1;
	}
	
	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	// 寰楀埌杩斿洖Easyui鐨勬暟鎹�
	public Map<String, Object> getResultMap(List<?> content) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", 	totalRow);
		resultMap.put("rows",	content);
		return resultMap;
	}
	
	// 寰楀埌杩斿洖Easyui鐨勬暟鎹�
	public Map<String, Object> getResultMap(Integer totalRow, List<?> content) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", 	totalRow);
		resultMap.put("rows",	content);
		return resultMap;
	}
	
	// 寰楀埌杩斿洖APP鐨勬暟鎹�
	public Map<String, Object> getAppResultMap(PageResult pageUtil, List<?> content) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pages", 	pageUtil);
		resultMap.put("result",	content);
		return resultMap;
	}
}
