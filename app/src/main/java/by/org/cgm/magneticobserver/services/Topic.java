package by.org.cgm.magneticobserver.services;

/**
 * Author: Anatol Salanevich
 * Date: 13.08.2015
 */
public enum Topic {

    GLOBAL("global");

    private String name;

    Topic(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String[] getTopics() {
        return new String[] {Topic.GLOBAL.name};
    }

}
