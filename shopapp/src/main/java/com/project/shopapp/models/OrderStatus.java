//package com.project.shopapp.models;
//
//
//public class OrderStatus {
//
//    public static final String PENDING = "pending";// ddang chowf xuwr lys
//
//    public static final String PROCESSING = "processing";// xuwr lys
//
////    public static final String SHIPPED = "shipped";// ship
//
////    public static final String DELIVERED ="delivered";//ddax giao
//
//    public static final String CANCELLED ="cancelled";// ddax huy
//}
package com.project.shopapp.models;

public enum OrderStatus {
    PENDING,
    PAID,
    CANCELLED
}
