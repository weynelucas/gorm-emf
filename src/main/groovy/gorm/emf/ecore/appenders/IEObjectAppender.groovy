package gorm.emf.ecore.appenders

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Created by lucasferreira on 25/05/2017.
 */
interface IEObjectAppender {
    boolean match(EStructuralFeature feature, appendData)
    void append(EObject model, EStructuralFeature feature, appendData)
}