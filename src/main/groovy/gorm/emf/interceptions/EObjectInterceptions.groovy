package gorm.emf.interceptions

import gorm.emf.ecore.wrappers.EObjectWrapper
import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

/**
 * Created by Lucas Weyne on 16/06/2017.
 */
class EObjectInterceptions implements DynamicInterception {

    static EObjectWrapper eObjectWrapper

    /**
     * Closure to override getProperty and load properties with references from
     * external collections
     */
    private static  loadExternalReferencesClosure = { String propName ->
        EObject self = (EObject) delegate
        def meta = self.metaClass.getMetaProperty(propName)
        if (meta) {
            def referenceType = self.eClass().EAllReferences.find { it.name == propName }?.EReferenceType?.instanceClass
            def result = meta.getProperty(self)

            if(referenceType?.collection) {
                if(result instanceof EList<EObject>) {
                    def eList = new BasicEList()
                    eList.addAllUnique(result.collect { EObject eObj -> eObj.ref ? eObj.eClass().instanceClass.find(eObj.ref) : eObj})
                    result = eList
                } else if (result instanceof EObject) {
                    result = result.ref ? result.eClass().instanceClass.find(result.ref) : result
                }

                try {
                    self."$propName" = result
                } catch (Exception ex) {
                    ex.printStackTrace()
                }
            }
            return result
        }
    }

    @Override
    void registerDynamicInterceptionMethods() {
        eObjectWrapper.ecoreFactory.getAllClasses().each { eObjectType ->
            def implType = eObjectType.newInstance().class

            implType.metaClass.getProperty = loadExternalReferencesClosure
        }
    }
}
