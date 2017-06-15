package gorm.emf.traits

import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Created by lucasferreira on 15/06/2017.
 */
trait EStructuralFeatureTypeGetter {
    def getAttributeType(EStructuralFeature feature) {
        ((EAttribute) feature).EAttributeType.instanceClass
    }
}