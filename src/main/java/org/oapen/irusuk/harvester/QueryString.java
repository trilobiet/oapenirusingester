package org.oapen.irusuk.harvester;

import java.time.YearMonth;

import org.springframework.util.StringUtils;

public class QueryString {

	private YearMonth
		beginDate, endDate;

	private String 
		countryCode, funder, itemId, itemType, metricType, publisher,
		attributesToShow, granularity, contentType, pretty;
	
	private QueryString() {}
	
	public static class Builder {
		
		private YearMonth
			beginDate, endDate;

		private String 
			countryCode, funder, itemId, itemType, metricType, publisher,
			attributesToShow, granularity, contentType, pretty;
		
		public Builder(YearMonth begin, YearMonth end) {
			this.beginDate = begin;
			this.endDate = end;
		}
		
		public Builder setCountryCode(String countryCode) {
			this.countryCode = countryCode;
			return this;
		}

		public Builder setFunder(String funder) {
			this.funder = funder;
			return this;
		}

		public Builder setItemId(String itemId) {
			this.itemId = itemId;
			return this;
		}

		public Builder setItemType(String itemType) {
			this.itemType = itemType;
			return this;
		}

		public Builder setMetricType(String metricType) {
			this.metricType = metricType;
			return this;
		}

		public Builder setPublisher(String publisher) {
			this.publisher = publisher;
			return this;
		}

		public Builder setAttributesToShow(String attributesToShow) {
			this.attributesToShow = attributesToShow;
			return this;
		}

		public Builder setGranularity(String granularity) {
			this.granularity = granularity;
			return this;
		}

		public Builder setContentType(String contentType) {
			this.contentType = contentType;
			return this;
		}

		public Builder setPretty(String pretty) {
			this.pretty = pretty;
			return this;
		}		
		
		public QueryString build() {
			
			QueryString qs = new QueryString();
			
			qs.beginDate = this.beginDate;
			qs.endDate = this.endDate;
			
			qs.attributesToShow = this.attributesToShow;
			qs.contentType = this.contentType;
			qs.countryCode = this.countryCode;
			qs.funder = this.funder;
			qs.granularity = this.granularity;
			qs.itemId = this.itemId;
			qs.itemType = this.itemType;
			qs.metricType = this.metricType;
			qs.pretty = this.pretty;
			qs.publisher = this.publisher;
			
			return qs;
		}
	}

	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		
		b.append("begin_date=").append(beginDate);
		b.append("&end_date=").append(endDate);

		if(StringUtils.hasText(countryCode))
			b.append("&country_code=").append(countryCode);
		if(StringUtils.hasText(funder))
			b.append("&funder=").append(funder);
		if(StringUtils.hasText(itemId))
			b.append("&item_id=").append(itemId);
		if(StringUtils.hasText(itemType))
			b.append("&item_type=").append(itemType);
		if(StringUtils.hasText(metricType))
			b.append("&metric_type=").append(metricType);
		if(StringUtils.hasText(publisher))
			b.append("&publisher=").append(publisher);
		if(StringUtils.hasText(attributesToShow))
			b.append("&attributes_to_show=").append(attributesToShow);
		if(StringUtils.hasText(granularity))
			b.append("&granularity=").append(granularity);
		if(StringUtils.hasText(contentType))
			b.append("&content_type=").append(contentType);
		if(StringUtils.hasText(pretty))
			b.append("&pretty=").append(pretty);

		return b.toString();
	}
	
	/**
	 * @return the beginDate
	 */
	public YearMonth getBeginDate() {
		return beginDate;
	}

	/**
	 * @return the endDate
	 */
	public YearMonth getEndDate() {
		return endDate;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @return the funder
	 */
	public String getFunder() {
		return funder;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @return the metricType
	 */
	public String getMetricType() {
		return metricType;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @return the attributesToShow
	 */
	public String getAttributesToShow() {
		return attributesToShow;
	}

	/**
	 * @return the granularity
	 */
	public String getGranularity() {
		return granularity;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @return the pretty
	 */
	public String getPretty() {
		return pretty;
	}
}
