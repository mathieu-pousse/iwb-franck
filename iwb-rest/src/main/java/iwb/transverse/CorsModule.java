package iwb.transverse;

import restx.factory.Module;
import restx.factory.Provides;
import restx.security.CORSAuthorizer;
import restx.security.StdCORSAuthorizer;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;

@Module
public class CorsModule {
	
	@Provides
    public CORSAuthorizer samplestAuthorizer() {
		return StdCORSAuthorizer.builder().setOriginMatcher(
                Predicates.<CharSequence>equalTo("http://localhost:9000"))
                .setPathMatcher(Predicates.containsPattern("^/cors/1"))
                .setAllowedMethods(ImmutableList.of("GET", "POST"))
                .build();
        
    }

}
