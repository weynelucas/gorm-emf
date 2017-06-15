package gorm.emf.ecore.persistence

import org.eclipse.emf.ecore.EObject
import gorm.emf.consts.EObjectConstants.InjectedProperties

/**
 * Created by Lucas Weyne on 14/06/2017.
 */
class EntityRegistry {

    /**
     * Register a collection (or table) for a specific EObject
     *
     * @param entityType Class of entity to register
     * @param collectionName Name of the collection (or table) to register entity (default value
     * is the simple name of entityType class)
     * @param doWithChildren Boolean to tell EntityRegistry to register all children entities too
     */
    static registerEntity(Class<EObject> entityType, String collectionName = '', boolean doWithChildren = true) {

        entityType.metaClass.static."get${InjectedProperties.COLLECTION.capitalize()}" = getCollectionNameClosure(entityType, collectionName)

        if (doWithChildren) {
            entityType.getDerivedClasses().each { derivedType ->
                derivedType.instanceClass.metaClass.static."get${InjectedProperties.COLLECTION.capitalize()}" = getCollectionNameClosure(derivedType, collectionName)
            }
        }
    }

    private static getCollectionNameClosure(entityType, collectionName) {
        return { ->
            return collectionName?.trim() ?: entityType.simpleName.toLowerCase()
        }
    }
}
