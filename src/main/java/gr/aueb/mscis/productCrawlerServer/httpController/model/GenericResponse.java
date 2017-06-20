package gr.aueb.mscis.productCrawlerServer.httpController.model;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

public class GenericResponse<T> implements Serializable {
	private static transient final long serialVersionUID = 91680746350156560L;
	
	protected final int status;
	protected final int total;
	protected final List<T> data;
	
	public GenericResponse(List<T> data) {
		this.data = data;
		this.status = 200;
		total = data.size();
	}
	public GenericResponse(List<T> data, int status) {
		this.data = data;
		this.status = status;
		total = data.size();
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
