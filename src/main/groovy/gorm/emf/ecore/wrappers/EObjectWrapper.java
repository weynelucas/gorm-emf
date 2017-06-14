package gorm.emf.ecore.wrappers;

import gorm.emf.ecore.exceptions.InvalidClassError;
import gorm.emf.ecore.exceptions.InvalidEClassError;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;

/**
 * Created by Lucas Weyne on 14/05/2017.
 */
public class EObjectWrapper implements IDefaultInstanceGenerator {

    private EFactory ecoreFactory;
    private EPackage ecorePackage;

    public EFactory getEcoreFactory() {
        return ecoreFactory;
    }

    public void setEcoreFactory(EFactory ecoreFactory) {
        this.ecoreFactory = ecoreFactory;
    }

    public EPackage getEcorePackage() {
        return ecorePackage;
    }

    public void setEcorePackage(EPackage ecorePackage) {
        this.ecorePackage = ecorePackage;
    }

    public EClassifier getEClassifier(String className) {
        return getEcorePackage().getEClassifier(className);
    }

    public <T> EClassifier getEClassifier(Class<T> cls) {
        return getEcorePackage().getEClassifier(cls.getSimpleName());
    }

    public EClass getEClass(String className) throws InvalidClassError {
        EClassifier eClassifier = getEClassifier(className);
        if (eClassifier != null && eClassifier instanceof EClass) {
            return (EClass) eClassifier;
        }
        throw new InvalidEClassError(className, getEcorePackage().getName());
    }

    public <T> EClass getEClass(Class<T> cls) throws InvalidClassError {
        return getEClass(cls.getSimpleName());
    }

    @Override
    public <T> T generateDefaultInstance(Class<T> cls) throws InvalidClassError {
        return cls.cast(getEcoreFactory().create(getEClass(cls)));
    }
}
