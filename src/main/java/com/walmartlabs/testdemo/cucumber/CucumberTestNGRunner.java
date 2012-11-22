package com.walmartlabs.testdemo.cucumber;

import cucumber.io.MultiLoader;
import cucumber.io.ResourceLoader;
import cucumber.runtime.RuntimeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Allow Cucumber to be run with TestNG
 */
public abstract class CucumberTestNGRunner {

    private static final Logger logger = LoggerFactory.getLogger(CucumberTestNGRunner.class);

    private RuntimeOptionsFactory optionsFactory;

    public RuntimeOptionsFactory getOptionsFactory() {
        if (optionsFactory == null) {
            optionsFactory = new DefaultRuntimeOptionsFactory();
        }
        return optionsFactory;
    }

    public void setOptionsFactory(RuntimeOptionsFactory optionsFactory) {
        this.optionsFactory = optionsFactory;
    }

    public void run(Class clazz) {

        RuntimeOptions runtimeOptions = createCucumberRuntimeOptions(clazz);

        if (CollectionUtils.isEmpty(runtimeOptions.featurePaths)) {
            runtimeOptions.featurePaths = getFeaturePaths(clazz);
        }

        if (CollectionUtils.isEmpty(runtimeOptions.glue)) {
            runtimeOptions.glue = runtimeOptions.featurePaths;
        }

        logger.info("runtimeOptions featurePaths {}", runtimeOptions.featurePaths);
        logger.info("runtimeOptions glue {}", runtimeOptions.glue);

        ClassLoader classLoader = clazz.getClassLoader();
        ResourceLoader resourceLoader = new MultiLoader(classLoader);
        cucumber.runtime.Runtime runtime = new cucumber.runtime.Runtime(resourceLoader, classLoader, runtimeOptions);

        runtime.run();
    }

    RuntimeOptions createCucumberRuntimeOptions(Class clazz) {
        RuntimeOptionsFactory runtimeOptionsFactory = getOptionsFactory();
        return runtimeOptionsFactory.create(clazz);
    }

    List<String> getFeaturePaths(Class clazz) {
        List<String> featurePaths = new ArrayList<String>();
        featurePaths.add(MultiLoader.CLASSPATH_SCHEME + CucumberUtils.packagePath(clazz));
        return featurePaths;
    }

    public void run() {
        this.run(getCucumberClass());
    }

    /**
     * allow runtime class to extends and tell CucumberTestNGRunner which class to
     * parse for cucumber options annotations
     *
     * @return the runtime Class
     */
    public abstract Class getCucumberClass();
}
