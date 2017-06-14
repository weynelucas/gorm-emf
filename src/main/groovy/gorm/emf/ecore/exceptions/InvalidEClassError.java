package gorm.emf.ecore.exceptions;

import gorm.emf.consts.Messages;

/**
 * Created by Lucas Weyne on 13/05/2017.
 */
public class InvalidEClassError extends InvalidClassError {
    public InvalidEClassError(String className, String ePackageClassName) {
        super(Messages.getINVALID_ECLASS_FORMAT(), className, ePackageClassName);
    }
}
