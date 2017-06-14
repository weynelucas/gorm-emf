package gorm.emf.extensions

import gorm.emf.traits.ClassResourceGetter

/**
 * Created by lucasferreira on 14/06/2017.
 */
abstract class DynamicExtension<T> implements ClassResourceGetter {

    void registerDynamicMethods() {
        def context = this
        def resource = getResourceType()

        context.metaClass.methods.findAll { method -> method.declaringClass.theClass == context.class }.each { method ->
            def closure = { ... args ->
                context.&"$method.name".call(delegate, *args)
            }

            if(method.static)
                resource.metaClass."static"."$method.name" = closure
            else
                resource.metaClass."$method.name" = closure
        }
    }
}
