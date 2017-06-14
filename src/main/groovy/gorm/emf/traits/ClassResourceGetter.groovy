package gorm.emf.traits

import java.lang.reflect.ParameterizedType

/**
 * Created by lucasferreira on 14/06/2017.
 */
trait ClassResourceGetter {

    def getResourceType() {
        return ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0]
    }
}
