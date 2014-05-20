package iwb.transverse;

import java.io.IOException;

import javax.inject.Inject;

import restx.RestxContext;
import restx.RestxRequest;
import restx.RestxRequestMatch;
import restx.RestxResponse;
import restx.StdRestxRequestMatcher;
import restx.StdRoute;
import restx.factory.Factory;

public class ApplicationUIResourceFactory extends StdRoute{
	private Factory factory;
	
	@Inject
    public ApplicationUIResourceFactory(Factory factory) {
    super("ApplicationUIResourceFactory", new StdRestxRequestMatcher("GET", "/ui"));
    this.factory = factory;
}

	@Override
	public void handle(RestxRequestMatch match, RestxRequest req,
			RestxResponse resp, RestxContext ctx) throws IOException {
		resp.setContentType("text/plain");
        resp.getWriter().println(factory.dump());
	}
}
