package iwb;

import com.google.common.base.Charsets;
import restx.mongo.MongoModule;
import restx.security.SignatureKey;
import restx.factory.Module;
import restx.factory.Provides;

import javax.inject.Named;

@Module
public class AppModule {
    @Provides
    public SignatureKey signatureKey() {
        return new SignatureKey("iwb fd66f4c9-2b76-4495-9d57-d0f92d3b02f6 -8512238729272782174 iwb".getBytes(Charsets.UTF_8));
    }
    @Provides
    @Named("restx.admin.password")
    public String restxAdminPassword() {
        return "2136";
    }

    @Provides
    @Named("app.name")
    public String appName(){
        return "iwb";
    }

    @Provides @Named(MongoModule.MONGO_DB_NAME)
    public String dbName() {
        return "restx-hellomongo";
    }

}
