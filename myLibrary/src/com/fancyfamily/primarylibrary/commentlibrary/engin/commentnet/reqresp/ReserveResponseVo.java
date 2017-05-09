package com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.reqresp;


import com.fancyfamily.primarylibrary.commentlibrary.engin.commentnet.BaseResponseVo;

/**
 * 预借图书
 * */
public class ReserveResponseVo extends BaseResponseVo {

    private Integer stockNo;//库存数
    private Integer reserveNo;//己预借数



	public Integer getStockNo() {
		return stockNo;
	}

	public void setStockNo(Integer stockNo) {
		this.stockNo = stockNo;
	}

	public Integer getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(Integer reserveNo) {
		this.reserveNo = reserveNo;
	}

    

}
