package ar.com.saile.component;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;


public class JsonViewModule extends SimpleModule {

    @Override
    public void setupModule(SetupContext context) {

        context.addBeanSerializerModifier(new BeanSerializerModifier() {

            @Override
            public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc,
                                                      JsonSerializer<?> serializer) {
                if (serializer instanceof BeanSerializerBase) {
                    return new JsonViewSerializer((BeanSerializerBase) serializer);
                }
                return serializer;

            }
        });
    }
}
