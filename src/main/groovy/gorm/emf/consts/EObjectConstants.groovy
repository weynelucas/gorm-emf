package gorm.emf.consts

import gorm.emf.traits.DeclaredFieldsGetter


/**
 * Created by lucasferreira on 09/06/2017.
 */
class EObjectConstants {
    /**
     * All names of injected properties in EObjects
     */
    class InjectedProperties implements DeclaredFieldsGetter {
        static String ID = "_id"
        static String REFERENCE = "ref"
        static String COLLECTION = "collection"
    }

    /**
     * All names of external representation properties (for
     * load and display EObjects)
     */
    class ExternalProperties implements DeclaredFieldsGetter {
        static String TYPE_DISCRIMINATOR = "classe"
    }
}
