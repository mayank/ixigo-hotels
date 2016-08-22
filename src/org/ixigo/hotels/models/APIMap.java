package org.ixigo.hotels.models;

import org.json.simple.JSONObject;

public class APIMap {

	private String apiUrl;
	private JSONObject mapping;

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public JSONObject getMapping() {
		return mapping;
	}

	public void setMapping(JSONObject mapping) {
		this.mapping = mapping;
	}
	
	
}
