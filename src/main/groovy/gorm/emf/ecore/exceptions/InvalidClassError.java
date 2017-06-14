package gorm.emf.ecore.exceptions;

/**
 * Created by Lucas Weyne on 13/05/2017.
 */
public abstract class InvalidClassError extends Exception {
    public InvalidClassError(String messageFormat, String... args) {
        super(String.format(messageFormat, (Object[]) args));
    }
}
