package ar.com.saile.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public class JsonViewSerializer extends BeanSerializer {

    protected JsonViewSerializer(BeanSerializerBase src) {

        super( src );
    }

    /**
     * MODIFIED By Gauthier PEEL
     */
    @Override
    protected void serializeFields(Object bean, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        final BeanPropertyWriter[] props;
        if (bean instanceof Page || bean instanceof Pageable || bean instanceof Sort) {
            props = _props;
        } else {
            if (_filteredProps != null && provider.getActiveView() != null) {
                props = _filteredProps;
            } else {
                props = _props;
            }
        }
        int i = 0;
        try {
            for (final int len = props.length; i < len; ++i) {
                BeanPropertyWriter prop = props[i];
                if (prop != null) { // can have nulls in filtered list
                    prop.serializeAsField(bean, gen, provider);
                }
            }
            if (_anyGetterWriter != null) {
                _anyGetterWriter.getAndSerialize(bean, gen, provider);
            }
        } catch (Exception e) {
            String name = (i == props.length) ? "[anySetter]" : props[i].getName();
            wrapAndThrow(provider, e, bean, name);
        } catch (StackOverflowError e) {
            // 04-Sep-2009, tatu: Dealing with this is tricky, since we don't have many
            //   stack frames to spare... just one or two; can't make many calls.

            // 10-Dec-2015, tatu: and due to above, avoid "from" method, call ctor directly:
            //JsonMappingException mapE = JsonMappingException.from(gen, "Infinite recursion (StackOverflowError)", e);
            DatabindException mapE = new JsonMappingException(gen, "Infinite recursion (StackOverflowError)", e);

            String name = (i == props.length) ? "[anySetter]" : props[i].getName();
            mapE.prependPath(bean, name);
            throw mapE;
        }
    }

    // inherited constructor removed for concision
}
