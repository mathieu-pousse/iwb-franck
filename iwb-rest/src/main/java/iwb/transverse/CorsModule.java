package iwb.transverse;

import com.google.common.base.Predicates;
import restx.factory.Module;
import restx.factory.Provides;
import restx.security.CORSAuthorizer;
import restx.security.StdCORSAuthorizer;

import static java.util.Arrays.asList;

@Module
public class CorsModule {
	
	@Provides
    public CORSAuthorizer samplestAuthorizer1() {
        return new StdCORSAuthorizer(
                Predicates.<CharSequence>equalTo("http://localhost:8080"),
                Predicates.containsPattern("^/api"), asList("*"));
    }

}
