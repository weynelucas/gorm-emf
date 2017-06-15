package gorm.emf.ecore.appenders

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Created by lucasferreira on 25/05/2017.
 */
class EObjectAppendersFactory {

    private static List<IEObjectAppender> eModelAppenders

    static getAppenders() {
        if (eModelAppenders == null) {
            eModelAppenders = [
                    new EAttributeSingleAppender(),
                    new EAttributeManyAppender(),
                    new EAttributeEnumeratorAppender(),
                    new EReferenceSingleAppender(),
                    new EReferenceManyAppender()
            ]
        }
        eModelAppenders
    }

    static execute(EObject model, EStructuralFeature feature, appendData) {
        def appenders = getAppenders()

        appenders.any { it ->
            IEObjectAppender appender = (IEObjectAppender) it

            if (appender.match(feature, appendData)) {
                appender.append(model, feature, appendData)
                return true
            }
        }
    }
}
