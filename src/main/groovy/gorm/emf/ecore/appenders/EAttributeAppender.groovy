package gorm.emf.ecore.appenders

import org.eclipse.emf.common.util.Enumerator
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Created by lucasferreira on 25/05/2017.
 */
class EAttributeAppender implements IEObjectAppender {

    @Override
    boolean match(EStructuralFeature feature, appendData) {
        return feature instanceof EAttribute
    }

    @Override
    void append(EObject model, EStructuralFeature feature, appendData) {
        def attributeType = ((EAttribute) feature).EAttributeType.instanceClass
        boolean attributeIsEnumerator = Enumerator.class.isAssignableFrom(attributeType)
        boolean appendDataIsEnumerator = appendData instanceof Enumerator

        if (attributeIsEnumerator && !appendDataIsEnumerator) {
            if (appendData instanceof Integer)
                model."$feature.name" = attributeType.get(appendData)
            else if (appendData instanceof String)
                model."$feature.name" = attributeType.getByName(appendData)
        } else {
            model."$feature.name" = appendData
        }
    }
}
