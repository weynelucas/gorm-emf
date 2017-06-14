package gorm.emf.ecore.wrappers;


import gorm.emf.ecore.exceptions.InvalidClassError;

/**
 * Created by Lucas Weyne on 14/05/2017.
 */
public interface IDefaultInstanceGenerator {
    <T> T generateDefaultInstance(Class<T> cls) throws InvalidClassError;
}
