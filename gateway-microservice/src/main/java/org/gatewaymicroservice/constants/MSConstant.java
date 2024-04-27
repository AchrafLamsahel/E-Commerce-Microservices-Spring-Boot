package org.gatewaymicroservice.constants;

public class MSConstant {
    public static final String CATALOGUE_MICROSERVICE = "catalogue-microservice";
    public static final String CONFIG_MICROSERVICE  = "config-microservice";
    public static final String AUTH_MICROSERVICE  = "auth-microservice";
    public static final String ORDER_MICROSERVICE  = "order-microservice";
    public static final String PAYMENT_MICROSERVICE  = "payment-microservice";
    public static final String USER_MICROSERVICE  = "user-microservice";

    public static final String CATALOGUE_MICROSERVICE_URI = "lb://catalogue-microservice";
    public static final String CONFIG_MICROSERVICE_URI  = "lb://config-microservice";
    public static final String AUTH_MICROSERVICE_URI  = "lb://auth-microservice";
    public static final String ORDER_MICROSERVICE_URI = "lb://order-microservice";
    public static final String PAYMENT_MICROSERVICE_URI  = "lb://payment-microservice";
    public static final String USER_MICROSERVICE_URI  = "lb://user-microservice";

    public static final String CATALOGUE_PATH = "";
    public static final String CONFIG_PATH  = "";
    public static final String AUTH_PATH  = "/auth/**";
    public static final String ORDER_PATH = "/order/**";
    public static final String PAYMENT_PATH  = "/payment/**";
    public static final String USER_PATH  = "/users/**";
}
