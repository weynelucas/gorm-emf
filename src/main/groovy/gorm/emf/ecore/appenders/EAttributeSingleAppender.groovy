package gorm.emf.ecore.appenders

import gorm.emf.traits.EStructuralFeatureTypeGetter
import org.eclipse.emf.common.util.Enumerator
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Created by lucasferreira on 25/05/2017.
 */
class EAttributeSingleAppender implements IEObjectAppender, EStructuralFeatureTypeGetter {
    @Override
    boolean match(EStructuralFeature feature, appendData) {
        return feature instanceof EAttribute &&
               !feature.many &&
               !Enumerator.isAssignableFrom(getAttributeType(feature))
    }

    @Override
    void append(EObject model, EStructuralFeature feature, appendData) {
            model."$feature.name" = appendData
    }
}
