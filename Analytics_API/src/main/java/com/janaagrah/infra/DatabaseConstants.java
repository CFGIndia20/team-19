package com.janaagrah.infra;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that maintains static constants concerning different collection names
 * Any collection will be accessed using CollectionConstants.VARIABLE_NAME
 * 
 * @author jimil
 */
public class DatabaseConstants {
    public final static String COMPLAINTS_COLLECTION_NAME = "complaints";
    public final static String COMPLAINTS_USERNAME_FIELD_NAME = "username";
    public final static String COMPLAINTS_DATE_FIELD_NAME = "date";
    public final static String COMPLAINTS_STATE_FIELD_NAME = "state";
    public final static String COMPLAINTS_STATUS_FIELD_NAME = "status";
}
