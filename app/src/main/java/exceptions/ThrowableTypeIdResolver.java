package exceptions;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class ThrowableTypeIdResolver extends TypeIdResolverBase {

    private JavaType baseType;

    @Override
    public void init(JavaType baseType) {
        this.baseType = baseType;
    }

    @Override
    public String idFromValue(Object value) {
        return value.getClass().getName();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return value.getClass().getName();
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
        try {
            return context.constructSpecializedType(baseType, Class.forName(id));
        } catch (ClassNotFoundException e) {
            System.out.println("Could not construct exception class for \"" + id + '\"');
            return context.constructSpecializedType(baseType, GenericServerError.class);
        }
    }
}
