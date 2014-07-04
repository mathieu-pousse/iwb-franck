package iwb.service.helpers;

import org.assertj.core.util.Strings;

import iwb.bo.City;
import iwb.bo.Item;
import iwb.bo.Link;
import iwb.bo.Trash;
import iwb.bo.WasteCustom;

public class LinkHelper {
	
	public LinkHelper(){
		
	}
	
	public Link createLink(Object obj){
		if(obj == null){
			 return null; 
		}
		
		Link createdLink = new Link(); 
		
		if(obj.getClass().equals(Item.class)){
			createdLink = new Link("alternative", Strings.concat("/items/",((Item) obj).getId()));
			((Item) obj).setLink(createdLink);
		}else if(obj.getClass().equals(WasteCustom.class)){
			createdLink = new Link("alternative", Strings.concat("/wastes/",((WasteCustom) obj).getId()));
			((WasteCustom) obj).setLink(createdLink);
		}else if(obj.getClass().equals(City.class)){
			createdLink = new Link("alternative", Strings.concat("/cities/",((City) obj).getId()));
			((City) obj).setLink(createdLink);
		}else if(obj.getClass().equals(Trash.class)){
			createdLink = new Link("alternative", Strings.concat("/trashes/",((Trash) obj).getId()));
			((Trash) obj).setLink(createdLink);
		}else{
			createdLink = null;
		}
		
		return createdLink;
	}
	
	public void removeLink(Object obj){
		if(obj != null){
			if(obj.getClass().equals(Item.class)){
				((Item) obj).setLink(null);
			}else if(obj.getClass().equals(WasteCustom.class)){
				((WasteCustom) obj).setLink(null);
			}else if(obj.getClass().equals(City.class)){
				((City) obj).setLink(null);
			}else if(obj.getClass().equals(Trash.class)){
				((Trash) obj).setLink(null);
			} 
		}	
	}
	
	
	
}
