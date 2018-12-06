package com.oneframe.plugin.swagger;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public final class Swagger {
    public String swagger;
    public Info info;
    public LinkedHashMap<String, Path> paths;
    public LinkedHashMap<String, Definition> definitions;

    public static class Info {
        public String version;
        public String title;
        public String description;
        public String termsOfService;
        public Contact contact;
        public License license;
    }

    public static class Contact {
        public String name;
        public String url;
        public String email;
    }

    public static class License {
        public String name;
        public String url;
    }

    public static class Path {
        public Post post;
        public Get get;
        public Update update;
        public Delete delete;
    }

    static abstract class Method {
        public ArrayList<String> tags;
        public String operationId;
        public ArrayList<Parameter> parameters;
        public ArrayList<String> consumes;
        public ArrayList<String> produces;
        public LinkedHashMap<String, Response> responses;
    }

    public static class Post extends Method {

    }

    public static class Get extends Method {

    }

    public static class Update extends Method {

    }

    public static class Delete extends Method {

    }

    public static class Parameter {
        public String name;
        public String in;
        public String integer;
        public boolean required;
        public Schema schema;
    }

    public static class Schema {
        public String $ref;
    }

    public static class Response {
        public String description;
        public Schema schema;
    }

    public static class Definition {
        public String type;
        public LinkedHashMap<String, Property> properties;
    }

    public static class Property {
        public String type;
        public String format;
    }
}

