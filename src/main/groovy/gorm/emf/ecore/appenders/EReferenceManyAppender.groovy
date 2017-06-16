package gorm.emf.ecore.appenders

import org.eclipse.emf.common.util.BasicEList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import static gorm.emf.consts.EObjectConstants.ExternalProperties.TYPE_DISCRIMINATOR

/**
 * Created by lucasferreira on 25/05/2017.
 */
class EReferenceManyAppender implements IEObjectAppender {

    @Override
    boolean match(EStructuralFeature feature, appendData) {
        boolean isAbstract = ((EReference) feature).EReferenceType.abstract

        return  feature.many &&
                feature instanceof EReference &&
                appendData instanceof List<Map> &&
                (!isAbstract || (isAbstract && appendData.every { data -> data.containsKey(TYPE_DISCRIMINATOR) }))
    }

    @Override
    void append(EObject model, EStructuralFeature feature, appendData) {
        def reference = (EReference) feature
        def eListData = new BasicEList<EObject>()

        appendData.each { data ->
            def type = reference.loadReferenceType(data.get(TYPE_DISCRIMINATOR, ''))
            eListData.add(type.newInstance(data))
        }

        model."$reference.name" = eListData
    }
}
