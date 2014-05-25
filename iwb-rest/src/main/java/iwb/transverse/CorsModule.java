package iwb.transverse;

import static java.util.Arrays.asList;
import restx.factory.Module;
import restx.factory.Provides;
import restx.security.CORSAuthorizer;
import restx.security.StdCORSAuthorizer;

import com.google.common.base.Predicates;

@Module
public class CorsModule {
	
	@Provides
    public CORSAuthorizer samplestAuthorizer1() {
        return new StdCORSAuthorizer(
                Predicates.<CharSequence>equalTo("http://localhost:8080"),
                Predicates.containsPattern("^/api"), asList("*"));
    }

}
