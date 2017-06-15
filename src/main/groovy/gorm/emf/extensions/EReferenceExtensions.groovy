package gorm.emf.extensions

import gorm.emf.EObjectService
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

/**
 * Created by Lucas Weyne on 14/06/2017.
 */
class EReferenceExtensions extends DynamicExtension<EReference> {

    EObjectService eObjectService

    /**
     * Load child reference type by name if EReference type is
     * abstract or has child classes
     *
     * @param className Name of child class to load
     * @return Class of respective reference
     */
    Class<? extends EObject> loadReferenceType(EReference self, String className) {
        def referenceType = self.EReferenceType

        if (referenceType.abstract || referenceType.instanceClass.hasDerivedClasses()) {
            def subType = eObjectService.getEClass(className)
            if (referenceType.isSuperTypeOf(subType)) {
                return subType.instanceClass
            }

            return null
        }

        referenceType.instanceClass
    }
}
