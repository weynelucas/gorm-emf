package gorm.emf.ecore.appenders

import gorm.emf.traits.EStructuralFeatureTypeGetter
import org.eclipse.emf.common.util.Enumerator
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Created by lucasferreira on 15/06/2017.
 */
class EAttributeEnumeratorAppender implements IEObjectAppender, EStructuralFeatureTypeGetter {
    @Override
    boolean match(EStructuralFeature feature, appendData) {

        return feature instanceof EAttribute &&
               Enumerator.isAssignableFrom(getAttributeType(feature)) &&
               (appendData instanceof Enumerator || appendData instanceof String || appendData instanceof Integer)

    }

    @Override
    void append(EObject model, EStructuralFeature feature, appendData) {
        def value
        def attributeType = getAttributeType(feature)

        if(appendData instanceof Enumerator) {
            value = appendData
        } else {
            value = (appendData instanceof String) ? attributeType.getByName(appendData) : attributeType.get(appendData)
        }

        model."$feature.name" = value
    }
}
