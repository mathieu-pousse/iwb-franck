package iwb.transverse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.CaseFormat;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import restx.*;
import restx.factory.Component;
import restx.factory.Factory;
import restx.factory.NamedComponent;
import restx.jackson.FrontObjectMapperFactory;
import restx.jackson.StdJsonProducerEntityRoute;

import javax.inject.Inject;
import javax.inject.Named;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
