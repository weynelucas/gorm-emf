package gorm.emf

import com.mongodb.BasicDBObject
import gorm.emf.ecore.persistence.IEObjectPersistence
import grails.transaction.Transactional
import grails.util.Holders
import org.bson.types.ObjectId
import org.eclipse.emf.ecore.EObject
import static gorm.emf.consts.EObjectConstants.InjectedProperties.ID

@Transactional
class EObjectMongoPersistenceService implements IEObjectPersistence {

    def mongo
    def grailsApplication = Holders.grailsApplication

    @Override
    def insert(EObject object, String collection) {
        return null
    }

    @Override
    def update(String id, EObject object, String collection) {
        return null
    }

    @Override
    def findById(String id, String collection) {
        if(id && collection) {
            def query = new BasicDBObject(ID, new ObjectId(id))
            return mongo
                    .getDatabase(grailsApplication.config.getProperty('grails.mongodb.databaseName'))
                    .getCollection(collection)
                    .findOne(query)
        }

        return null
    }

    @Override
    def findAll(String collection, int page, int pageSize) {
        return null
    }

    @Override
    long count(String collection) {
        return 0
    }
}
