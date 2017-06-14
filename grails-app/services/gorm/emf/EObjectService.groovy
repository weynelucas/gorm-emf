package gorm.emf

import gorm.emf.ecore.wrappers.EObjectWrapper
import grails.transaction.Transactional
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject

@Transactional
class EObjectService {

    EObjectWrapper eObjectWrapper

    def getEClass(modelType) {
        eObjectWrapper.getEClass(modelType)
    }

    def findDerivedClasses(EClass eClass) {
        return eObjectWrapper.ecorePackage.eContents().findAll { EObject eObj ->
            def eObjCls
            def eObjClf = eObjectWrapper.getEClassifier(eObj.name)

            try {
                eObjCls = eObjectWrapper.getEClass(eObj.name)
            } catch (ignored) {
                eObjCls = null
            }

            return (eObjClf instanceof EClass) && eObjCls != eClass && eClass.isSuperTypeOf(eObjCls)
        }
    }

    def generateDefaultInstance(modelType) {
        eObjectWrapper.generateDefaultInstance(modelType)
    }
}
