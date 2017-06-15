package gorm.emf

import gorm.emf.ecore.persistence.IEObjectPersistence
import grails.transaction.Transactional
import org.eclipse.emf.ecore.EObject

@Transactional
class EObjectMongoPersistenceService implements IEObjectPersistence {

    def mongo

    @Override
    def insert(EObject object, String resource) {
        return null
    }

    @Override
    def update(String id, EObject object, String resource) {
        return null
    }

    @Override
    def findById(String id, String resource) {
        return null
    }

    @Override
    def findAll(String resource, int page, int pageSize) {
        return null
    }

    @Override
    long count(String resource) {
        return 0
    }
}
