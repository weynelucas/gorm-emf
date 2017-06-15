package gorm.emf.ecore.persistence

import org.eclipse.emf.ecore.EObject
import static gorm.emf.consts.EObjectConstants.InjectedProperties.COLLECTION

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
     */
    static registerEntity(Class<EObject> entityType, String collectionName = '') {
        entityType.metaClass.static."$COLLECTION" = collectionName?.trim() ?: entityType.simpleName.toLowerCase()
    }
}
