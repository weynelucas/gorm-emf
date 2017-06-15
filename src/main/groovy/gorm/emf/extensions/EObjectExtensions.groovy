package gorm.emf.extensions

import com.sun.corba.se.spi.ior.ObjectId
import gorm.emf.EObjectService
import gorm.emf.ecore.appenders.EObjectAppendersFactory
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

import gorm.emf.consts.EObjectConstants.InjectedProperties
import gorm.emf.consts.EObjectConstants.ExternalProperties

/**
 * Created by lucasferreira on 14/06/2017.
 */
class EObjectExtensions extends DynamicExtension<EObject> {


    static EObjectService eObjectService


   /**
    * Convert an EObject instance to a LinkedHaspMap
    *
    * @return A LinkedHaspMap with all attributes and references
    * of the given EObject
    */
    def toMap(EObject self) {
        def outMap = [:]
        def objProps = self.properties
        def attributes = self.eClass().EAllAttributes
        def references = self.eClass().EAllReferences


        if (objProps.containsKey(InjectedProperties.ID)){
            outMap[InjectedProperties.REFERENCE] = objProps.get(InjectedProperties.ID).toString()
        }

        outMap[ExternalProperties.TYPE_DISCRIMINATOR] = self.eClass().name

        attributes.each { EAttribute attr ->
            if (objProps.containsKey(attr.name)) {
                if(objProps.get(attr.name)) {
                    outMap[attr.name] = objProps[attr.name]
                }
            }
        }

        references.each { EReference reference ->
            if (objProps.containsKey(reference.name)) {
                if (objProps.get(reference.name)) {
                    if (reference.many) {
                        outMap[reference.name] = []
                        objProps[reference.name].each { it ->
                            outMap[reference.name].add(it.toMap())
                        }
                    } else {
                        outMap[reference.name] = objProps[reference.name].toMap()
                    }
                }
            }
        }

        outMap
    }

    /**
     * Append a pair (property, value) to an EObject instance
     *
     * @param property The name of property to append value
     * @param value The value to add for the given property of EObject
     * @return An EObject instance with the pair already added
     */
    def append(EObject self, String property, Object value) {
        if (property in InjectedProperties.all) {
            self.metaClass."$property" = (value instanceof ObjectId) ? value.toString() : value
        }

        EStructuralFeature feature = ((EObject) self).eClass().EAllStructuralFeatures.find { ft -> ft.name == property }
        if (feature) {
            EObjectAppendersFactory.execute(self, feature, value)
        }

        self
    }

    /**
     * Append an entire Map with properties and values to an
     * EObject instance
     *
     * @param map The LinkedHashMap with properties and values to append on instance
     * @param handleReferences Boolean to enable/disable EReference appends
     * @return An EObject instance with the map already appended
     */
    def append(EObject self, Map map, boolean handleReferences = true) {
        if (map == null) {
            return null
        }

        if (!handleReferences) {
            def attributes = ((EObject) self).eClass().EAllAttributes.name
            map = map.findAll { String key, Object val -> key in  attributes || key in InjectedProperties.all}
        }

        map.each { String key, Object value ->
            self.append(key, value)
        }

        self
    }

    /**
     * Get a EClass of the given EObject
     *
     * @return EClass corresponding to the given EObject
     */
    static getEClass(Class<EObject> selfType) {
        eObjectService.getEClass(selfType.metaClass.theClass)
    }

    /**
     * Get all children classes of the given EObject
     *
     * @return List with all children classes of the given EObject
     */
    static getDerivedClasses (Class<EObject> selfType) {
        def eClass = eObjectService.getEClass(selfType.metaClass.theClass)
        eObjectService.findDerivedClasses(eClass)
    }

    /**
     * Check if EObject has childrens (derived classes)
     *
     * @return Boolean checking if the given EObject has derived classes
     */
    static boolean hasDerivedClasses (Class<EObject> selfType) {
        def eClass = eObjectService.getEClass(selfType.metaClass.theClass)
        eObjectService.hasDerivedClasses(eClass)
    }

    /**
     * Create a default EObject instance
     *
     * @return EObject instance with default values
     */
    static  newInstance (Class<EObject> selfType) {
        eObjectService.generateDefaultInstance(selfType.metaClass.theClass)
    }

    /**
     * Create an EObject instance based on a given map with properties and
     * values
     *
     * @param map LinkedHashMap with properties and values to create the instance
     * @return EObject instance with default values
     */
    static newInstance (Class<EObject> selfType, Map map) {
        selfType.newInstance(map, true)
    }

    /**
     * Create an EObject instance based on a given map with properties and
     * values
     *
     * @param map LinkedHashMap with properties and values to create the instance
     * @param handleReferences Boolean to enable/disable EReference appends
     * @return
     */
    static  newInstance (Class<EObject> selfType, Map map, boolean handleReferences)  {
        def defaultInstance = selfType.newInstance()
        defaultInstance.append(map, handleReferences)
    }
}
