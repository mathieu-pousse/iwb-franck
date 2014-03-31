package iwb.transverse;

import com.google.common.collect.ImmutableMap;
import restx.ResourcesRoute;
import restx.factory.Component;

@Component
public class ApplicationUIResource extends ResourcesRoute{
	
	public ApplicationUIResource() {
		 super("ApplicationUIResource", "/ui", "iwb/transport", ImmutableMap.of("", "index.html"));
	}
}
