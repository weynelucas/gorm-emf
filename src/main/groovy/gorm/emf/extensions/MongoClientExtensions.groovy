package gorm.emf.extensions

import com.mongodb.MongoClient
import grails.util.Holders

/**
 * Created by Lucas Weyne on 16/06/2017.
 */
class MongoClientExtensions extends DynamicExtension<MongoClient> {
    def getDatabase(MongoClient self) {
        self.getDatabase(Holders.grailsApplication.config.getProperty("grails.mongodb.databaseName"))
    }
}
