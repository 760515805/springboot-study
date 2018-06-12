package com.montnets.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @Title:  DataPage.java   
 * @Description: 分页工具类
 * @author: chenhongjie     
 * @date:   2018年3月23日 上午10:02:40   
 * @version V1.0
 */
public class DataPage<T>implements Serializable{
  private static final long serialVersionUID = -7021480900278637440L;
  private int pageNo = 1;//当前页
  private int pageSize = 20;//每页数据
  private boolean needData = true;
  private boolean needTotalCount = true;
  private boolean needPaging = true;//是否需要分页
  private List<T> dataList = null;//响应数据集
  private long totalCount = -1L;
    
  private String sortField;
  
  private String order;
  
  private  String indexName;
  
  	/**
  	 * @param field 需要排序的字段(排序字段只能为一个)
  	 * @param order 排序的方法
  	 */
	 public DataPage<T> addSort(String field, String order){
		 this.sortField=field;
		 this.order=order;
		 return this;
	 }

	 
	 
  public boolean isNeedPaging() {
		return needPaging;
	}



	public void setNeedPaging(boolean needPaging) {
		this.needPaging = needPaging;
	}



public String getSortField() {
		return sortField;
	}
	public String getOrder() {
		return order;
	}



public DataPage()
  {
  }
   
  public String getIndexName() {
	return indexName;
}

/**
 * 设置ES索引名
 */
public void setIndexName(String indexName) {
	this.indexName = indexName;
}


public DataPage(int pageSize) {
    this.pageSize = pageSize;
  }

  public DataPage(int pageNo, int pageSize) {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }

  public DataPage(boolean needData, boolean needTotalCount) {
    this.needData = needData;
    this.needTotalCount = needTotalCount;
  }

  public int getPageNo()
  {
    return this.pageNo;
  }

  public void setPageNo(int pageNo)
  {
    this.pageNo = pageNo;

    if (pageNo < 1)
      this.pageNo = 1;
  }

  public DataPage<T> pageNo(int thePageNo)
  {
    setPageNo(thePageNo);
    return this;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public DataPage<T> pageSize(int thePageSize)
  {
    setPageSize(thePageSize);
    return this;
  }

  public int getFirst()
  {
    return (this.pageNo - 1) * this.pageSize;
  }

  public boolean isNeedTotalCount()
  {
    return this.needTotalCount;
  }

  public boolean isNeedData() {
    return this.needData;
  }

  public void setNeedData(boolean needData) {
    this.needData = needData;
  }

  public void setNeedTotalCount(boolean autoCount)
  {
    this.needTotalCount = autoCount;
  }

  public DataPage<T> needTotalCount(boolean theAutoCount)
  {
    setNeedTotalCount(theAutoCount);
    return this;
  }

  public List<T> getDataList()
  {
    return this.dataList;
  }

  public void setDataList(List<T> result)
  {
    this.dataList = result;
  }

  public long getTotalCount()
  {
    return this.totalCount;
  }

  public void setTotalCount(long totalCount)
  {
    this.totalCount = totalCount;
  }

  public long getTotalPages()
  {
    if (this.totalCount < 0L) {
      return -1L;
    }

    long count = this.totalCount / this.pageSize;
    if (this.totalCount % this.pageSize > 0L) {
      count += 1L;
    }
    return count;
  }

  public boolean isHasNext()
  {
    return this.pageNo + 1 <= getTotalPages();
  }

  public int getNextPage()
  {
    if (isHasNext()) {
      return this.pageNo + 1;
    }
    return this.pageNo;
  }

  public int getEndIndex() {
    return this.pageNo * this.pageSize;
  }

  public int getStartIndex() {
    return getFirst();
  }

  public boolean isHasPrev()
  {
    return this.pageNo - 1 >= 1;
  }

  public int getPrevPage()
  {
    if (isHasPrev()) {
      return this.pageNo - 1;
    }
    return this.pageNo;
  }
}