package com.eventify.eventify.constants;

public class SecurityConstants {

    public static final String JWT_KEY = "ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoIiwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn00w79tmyO";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";
}
