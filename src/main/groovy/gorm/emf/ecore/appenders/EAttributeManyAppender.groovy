package gorm.emf.ecore.appenders

import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Created by lucasferreira on 15/06/2017.
 */
class EAttributeManyAppender implements IEObjectAppender {
    @Override
    boolean match(EStructuralFeature feature, appendData) {
        return feature instanceof EAttribute &&
               feature.many &&
               appendData instanceof List
    }

    @Override
    void append(EObject model, EStructuralFeature feature, appendData) {
        def eListData = new BasicEList()
        eListData.add(appendData)

        model."$feature.name" = eListData
    }
}
