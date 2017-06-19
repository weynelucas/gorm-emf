package gorm.emf.consts

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage

/**
 * Created by lucasferreira on 14/06/2017.
 */
class Messages {

    /**
     * Exception messages
     */
    static final String INVALID_ECLASS_FORMAT = "Class '%1s' not found for '${EPackage.class.simpleName}' '%2s' or is not an instance of '${EClass.class.simpleName}'"
}
