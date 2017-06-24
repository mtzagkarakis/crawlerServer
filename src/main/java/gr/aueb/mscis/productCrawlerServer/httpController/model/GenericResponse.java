package gr.aueb.mscis.productCrawlerServer.httpController.model;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

public class GenericResponse<T> implements Serializable {
	private static transient final long serialVersionUID = 91680746350156560L;
	
	protected final int status;
	protected final int total;
	protected final ArrayList<T> data;
	protected final HashMap<String, Object> attributes;
	
	public GenericResponse(List<T> data, int status, Map<String, Object> attributes) {
		this.data = new ArrayList<>();
		if (data != null)
			this.data.addAll(data);
		
		this.attributes = new HashMap<>();
		if (attributes != null)
			attributes.forEach(this.attributes::put);
		
		this.status = status;
		total = this.data.size();
		
	}
	public String serializeToJson(){
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().create();
		return gson.toJson(this);
	}
	public String serializeToJson(Type type, JsonSerializer<T> serializer){
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapter(type, serializer).setPrettyPrinting().disableHtmlEscaping().create();
		return gson.toJson(this.data);
	}

}
