package net.xinqushi.util;

public class RouteMediaTxt {
	long srcSiteId;
	long dstSiteId;
	String srcSiteName;
	String dstSiteName;
	int reservNum;

	public RouteMediaTxt() {

	}

	public RouteMediaTxt(long srcSiteId, long dstSiteId, String srcSiteName, String dstSiteName, int reservNum) {
		this.srcSiteId = srcSiteId;
		this.dstSiteId = dstSiteId;
		this.srcSiteName = srcSiteName;
		this.dstSiteName = dstSiteName;
		this.reservNum = reservNum;
	}

	public long getSrcSiteId() {
		return srcSiteId;
	}

	public void setSrcSiteId(long srcSiteId) {
		this.srcSiteId = srcSiteId;
	}

	public long getDstSiteId() {
		return dstSiteId;
	}

	public void setDstSiteId(long dstSiteId) {
		this.dstSiteId = dstSiteId;
	}

	public String getSrcSiteName() {
		return srcSiteName;
	}

	public void setSrcSiteName(String srcSiteName) {
		this.srcSiteName = srcSiteName;
	}

	public String getDstSiteName() {
		return dstSiteName;
	}

	public void setDstSiteName(String dstSiteName) {
		this.dstSiteName = dstSiteName;
	}

	public int getReservNum() {
		return reservNum;
	}

	public void setReservNum(int reservNum) {
		this.reservNum = reservNum;
	}
}
