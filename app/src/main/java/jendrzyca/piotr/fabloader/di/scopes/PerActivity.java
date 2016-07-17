package jendrzyca.piotr.fabloader.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by huddy on 7/13/16.
 * Custom scopes for Dagger modules
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
