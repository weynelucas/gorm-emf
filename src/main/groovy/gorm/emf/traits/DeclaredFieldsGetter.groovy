package gorm.emf.traits

/**
 * Created by lucasferreira on 09/06/2017.
 */
trait DeclaredFieldsGetter {
    /**
     * Get all declared fields and constants names of a class
     */
    static getAll() {
        this.declaredFields.findAll { f -> !f.synthetic }.collect { f -> this."$f.name" }
    }
}