package gorm.emf.extensions

import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage

/**
 * Created by Lucas Weyne on 16/06/2017.
 */
class EFactoryExtensions extends DynamicExtension<EFactory> {

    /**
     * Return all classes managed by EMF library factory
     *
     * @return List of EObject classes
     */
    def getAllClasses(EFactory self) {
        self.class.declaredMethods.returnType.findAll {
            it != EObject &&
            !EFactory.isAssignableFrom(it) &&
            !EPackage.isAssignableFrom(it) &&
            EObject.isAssignableFrom(it)
        }
    }
}
