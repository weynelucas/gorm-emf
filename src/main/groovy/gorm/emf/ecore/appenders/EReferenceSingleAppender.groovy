package gorm.emf.ecore.appenders

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import static gorm.emf.consts.EObjectConstants.ExternalProperties.TYPE_DISCRIMINATOR


/**
 * Created by lucasferreira on 25/05/2017.
 */
class EReferenceSingleAppender implements IEObjectAppender {

    @Override
    boolean match(EStructuralFeature feature, appendData) {
        boolean isAbstract = ((EReference) feature).EReferenceType.abstract

        return  !feature.many &&
                feature instanceof EReference &&
                appendData instanceof Map &&
                (!isAbstract || (isAbstract && appendData.containsKey(TYPE_DISCRIMINATOR)))
    }

    @Override
    void append(EObject model, EStructuralFeature feature, appendData) {
        def reference = (EReference) feature
        def type = reference.loadReferenceType(appendData.get(TYPE_DISCRIMINATOR, ''))
        model."$reference.name" = type.newInstance(appendData)
    }
}
