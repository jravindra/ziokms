package com.walmartlabs.testdemo.cucumber;

public final class CucumberUtils {

    private CucumberUtils() {
    }

    /**
     * return fully qualify package path for the clazz
     *
     * @param clazz the class to parse
     * @return the fully qualify package of the clazz with "/" instead of "."
     */
    public static String packagePath(Class clazz) {
        return transformPackageName(packageName(clazz.getName()));
    }

    /**
     * return package using "/" instead of "."
     *
     * @param packageName the class to parse
     * @return package name with "/" instead of "."
     */
    static String transformPackageName(String packageName) {
        return packageName.replace('.', '/');
    }

    /**
     * return the className from the fully qualify package name without class name
     * <p/>
     * com.example.ClassA => com.example
     *
     * @param className fully qualify package name of the class
     * @return the package name without class name
     */
    public static String packageName(String className) {
        return className.substring(0, Math.max(0, className.lastIndexOf('.')));
    }

}
