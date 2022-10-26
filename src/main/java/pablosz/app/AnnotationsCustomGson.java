package pablosz.app;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import pablosz.ann.NotPersistable;
import pablosz.ann.Persistable;

public class AnnotationsCustomGson implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {

        return fieldAttributes.getAnnotation(NotPersistable.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false ;
    }
}
