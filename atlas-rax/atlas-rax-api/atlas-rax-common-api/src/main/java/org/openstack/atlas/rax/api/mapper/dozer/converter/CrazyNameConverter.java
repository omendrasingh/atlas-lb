package org.openstack.atlas.rax.api.mapper.dozer.converter;

import org.dozer.CustomConverter;
import org.openstack.atlas.service.domain.exception.NoMappableConstantException;

import javax.xml.namespace.QName;
import java.util.*;

public class CrazyNameConverter implements CustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return null;
        }

        if(destinationClass == Map.class) {
            Map<QName, String> otherAttributes = (Map<QName, String>)(existingDestinationFieldValue);
            if (otherAttributes == null) otherAttributes = new HashMap<QName, String>();

            otherAttributes.put((new QName("http://docs.openstack.org/atlas/api/v1.1/extensions/rax", "crazyName", "rax")), (String) sourceFieldValue);

            return otherAttributes;
        }

        if(destinationClass == String.class) {
            return AnyObjectMapper.<String>getOtherAttribute((Map<QName,String>) sourceFieldValue, "crazyName");
        }

        throw new NoMappableConstantException("Cannot map source type: " + sourceClass.getName());
    }
}
