package gorm.emf.extensions.registry

import gorm.emf.extensions.DynamicExtension
import grails.util.Holders

/**
 * Created by lucasferreira on 14/06/2017.
 */
class DynamicExtensionRegistry {

    static enableGlobally() {
        Holders.grailsApplication.mainContext.getBeansOfType(DynamicExtension).each { beanName, bean ->
            bean.registerDynamicMethods()
        }
    }
}
