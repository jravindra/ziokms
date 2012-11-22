package com.walmartlabs.testdemo.cucumber;

import cucumber.io.MultiLoader;
import cucumber.junit.Cucumber;
import cucumber.runtime.RuntimeOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.walmartlabs.testdemo.cucumber.CucumberUtils.packagePath;

/**
 * Base on cucumber.junit.RuntimeOptionsFactory, with modification
 * <p/>
 * responsible for build Cucumber RuntimeOptions. This will first
 * - parse the clazz for Cucumber.Options annotation
 * - then override with the command line option
 * <p/>
 */
public class DefaultRuntimeOptionsFactory implements RuntimeOptionsFactory {

    @Override
    public RuntimeOptions create(Class clazz) {
        List<String> args = new ArrayList<String>();
        Cucumber.Options options = getOptions(clazz);

        addGlue(options, clazz, args);
        addTags(options, args);
        addFormats(options, args);
        addStrict(options, args);
        addName(options, args);
        addFeatures(options, clazz, args);

        return new RuntimeOptions(System.getProperties(), args.toArray(new String[args.size()]));
    }


    private Cucumber.Options getOptions(Class<?> clazz) {
        return clazz.getAnnotation(Cucumber.Options.class);
    }

    /**
     * add list of location to lookup stepdefs or hooks if the class is annotated.
     * By default, it'll use "classpath:<package of clazz>" to do lookup for all
     * stefdefs and hooks.
     *
     * @param options the annotated cucumber options
     * @param clazz   the annotated clazz with Cucumber options
     * @param args    the existing args list to be append
     */
    private void addGlue(Cucumber.Options options, Class clazz, List<String> args) {
        if (options != null && options.glue().length != 0) {
            for (String glue : options.glue()) {
                args.add("--glue");
                args.add(glue);
            }
        } else {
            args.add("--glue");
            args.add(MultiLoader.CLASSPATH_SCHEME + packagePath(clazz));
        }
    }

    /**
     * add the cucumber tag(s) to the Cucumber.Options. If not "tag" configured, this will tell
     * cucumber to run any feature(s) or scenario(s) that not tagged as "@wip"
     * <p/>
     * you can override this setting at command line by specify
     * <p/>
     * --tag @wip This will tell cucumber tag with @wip.
     * this way any bugs or incomplete feature/scenario(s) to be checkin and fix later
     *
     * @param options the annotated cucumber options
     * @param args    the args list to add
     */
    private void addTags(Cucumber.Options options, List<String> args) {
        if (options != null) {
            if (options.tags().length == 0) {
                args.add("--tags");
                args.add("~@wip");
            } else {
                for (String tags : options.tags()) {
                    args.add("--tags");
                    args.add(tags);
                }
            }
        }
    }

    /**
     * add annotated format in options to args
     *
     * @param options the annotated Cucumber.Options
     * @param args    the list of arguments with "--format" added
     */
    private void addFormats(Cucumber.Options options, List<String> args) {
        if (options != null && options.format().length != 0) {
            for (String format : options.format()) {
                args.add("--format");
                args.add(format);
            }
        } else {
            args.add("--format");
            args.add("null");
        }
    }

    /**
     * add list annotated "features" to run. If no features configured, this method will
     * add "classpath:<package of clazz>" to the args list. The cucumber will auto read all
     * *.feature fule(s) under the specify package
     *
     * @param options the annotated cucumber options
     * @param clazz   the annotated clazz with Cucumber options
     * @param args    the existing args list to be append
     */
    private void addFeatures(Cucumber.Options options, Class clazz, List<String> args) {
        if (options != null && options.features().length != 0) {
            Collections.addAll(args, options.features());
        } else {
            args.add(MultiLoader.CLASSPATH_SCHEME + packagePath(clazz));
        }
    }

    private void addStrict(Cucumber.Options options, List<String> args) {
        if (options != null && options.strict()) {
            args.add("--strict");
        }
    }

    private void addName(Cucumber.Options options, List<String> args) {
        if (options != null) {
            if (options.name().length != 0) {
                for (String name : options.name()) {
                    args.add("--name");
                    args.add(name);
                }
            }
        }
    }
}
