package gorm.emf.ecore.persistence

import org.eclipse.emf.ecore.EObject

/**
 * Created by lucasferreira on 14/06/2017.
 */
interface IEObjectPersistence {
    def insert(EObject object, String resource)
    def update(String id, EObject object, String resource)
    def findById(String id, String resource)
    def findAll(String resource, int page, int pageSize)
    long count(String resource)
}