package com.oneframe.plugin.swagger;

public final class Swagger {

    public String swagger;
    public Info info;

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
}

