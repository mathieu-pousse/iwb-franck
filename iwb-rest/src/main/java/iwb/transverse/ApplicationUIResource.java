package iwb.transverse;

import restx.ResourcesRoute;
import restx.factory.Component;

import com.google.common.collect.ImmutableMap;

@Component
public class ApplicationUIResource extends ResourcesRoute{
	
	public ApplicationUIResource() {
		 super("ApplicationUIResource", "/ui", "ui", ImmutableMap.of("", "index.html"));
	}
}
