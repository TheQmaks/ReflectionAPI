package qmaks;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

/**
 *
 * @author Qmaks
 */
public class Reflection {

    public static <T> T callConstructor(Class clazz, Class[] needargs, Object... args) {
        try {
            Constructor c = clazz.getDeclaredConstructor(needargs);
            c.setAccessible(true);
            return (T) c.newInstance(args);
        } catch (Exception ex) {
        }
        return null;
    }

    public static <T> T callMethod(Class clazz, Object instance, String methodname, Class[] needargs, Object... args) {
        try {
            Method m = clazz.getDeclaredMethod(methodname, needargs);
            m.setAccessible(true);
            return (T) m.invoke(instance, args);
        } catch (Exception ex) {
        }
        return null;
    }
    
    public static void setField(Class clazz, Object instance, String fieldname, Object target) {
        try {
            Field f = clazz.getDeclaredField(fieldname);
            f.setAccessible(true);
            if (Modifier.isFinal(f.getModifiers())) {
                Field modifiers = Field.class.getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            }
            f.set(instance, target);
        } catch (Exception ex) {
        }
    }

    public static <T> T getField(Class clazz, Object instance, String fieldname) {
        try {
            Field f = clazz.getDeclaredField(fieldname);
            f.setAccessible(true);
            return (T) f.get(instance);
        } catch (Exception ex) {
        }
        return null;
    }
}
