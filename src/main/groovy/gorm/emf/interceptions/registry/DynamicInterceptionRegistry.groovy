package gorm.emf.interceptions.registry

import gorm.emf.interceptions.DynamicInterception
import grails.util.Holders

/**
 * Created by Lucas Weyne on 16/06/2017.
 */
class DynamicInterceptionRegistry {

    static void enableGlobally() {
        Holders.grailsApplication.mainContext.getBeansOfType(DynamicInterception).each { beanName, bean ->
            bean.registerDynamicInterceptionMethods()
        }
    }
}
