package iwb.service.helpers;

import javax.inject.Named;

import restx.factory.Component;

@Component @Named("imageHelper")
public class ImageUrlHelper {
	
	private String basePathUrl;
	
	public ImageUrlHelper(@Named("img.location") String url){
		this.basePathUrl = url;
	}
	
	public String addBasePathUrl(String name){
		if(name == null || name.isEmpty()){
			return null;
		}
		return basePathUrl.concat(name);
	}
	
	public String removeBasePathUrl(String name){
		if(name == null || name.isEmpty()){
			return null;
		}
		return name.replace(basePathUrl, "");
	}

}
