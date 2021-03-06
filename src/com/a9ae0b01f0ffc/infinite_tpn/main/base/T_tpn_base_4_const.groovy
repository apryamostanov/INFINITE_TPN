package base

import base.T_common_base_3_utils

class T_tpn_base_4_const extends T_common_base_3_utils {

    public final static String GC_TPN_VERSION = "1.1.5"
    public final static String GC_MODE_NORMAL = "normal"
    public final static String GC_MODE_RETRY = "retry"
    public final static String GC_STATUS_NEW = "new"
    public final static String GC_STATUS_DELIVERED = "delivered"
    public final static String GC_STATUS_FAILED_NO_CONNECTION = "no_connection"
    public final static String GC_STATUS_FAILED_INVALID_RESPONSE = "invalid_response"
    public final static String GC_STATUS_FAILED_INVALID_REQUEST = "invalid_request"
    public final static String GC_STATUS_FAILED_RESPONSE = "failed_response"
    public final static String GC_STATUS_EXCEPTION = "error"
    public final static String GC_STATUS_WAITING_FOR_PROCESSING = "waiting"
    public final static String GC_STATUS_DUPLICATE = "duplicate"
    public final static String GC_STATUS_RENEWED = "renewed"
    public final static String GC_STATUS_SENDING = "sending"
    public final static String GC_STATUS_OBSOLETE = "obsolete"
    public final static Integer GC_RESPONSE_CODE_INVALID_RESPONSE = -1
    public final static Integer GC_RESPONSE_CODE_INVALID_REQUEST = -2
    public final static Integer GC_RESPONSE_CODE_CONNECTION_REFUSED = -3
    public final static String GC_MAIN_TPN_THREAD_NAME = "TPN_MAIN"
    public final static String GC_MASTER_TPN_THREAD_NAME_PREFIX = "TPN_MASTER_"
    public final static String GC_WORKER_TPN_THREAD_NAME_PREFIX = "TPN_WORKER_"
    public final static Integer GC_MYSQL_VARCHAR_LIMIT = 3999
    public final static String GC_UNIQUE_ID_FLAG_RETRY = "1"
    public final static String GC_UNIQUE_ID_FLAG_NORMAL = "0"
    public final static Long GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS = 60000
    public final static String GC_ACCEPT = "Accept"
    public final static String GC_ACCEPT_LANGUAGE = "Accept-Language"
    public final static String GC_CONNECTION = "Connection"
    public final static String GC_CONTENT_TYPE = "Content-Type"
    public final static String GC_HOST = "Host"
    public final static String GC_SOAPACTION =  "SOAPAction"
    public final static String GC_USER_AGENT = "User-Agent"
    public final static String GC_SOURCE_MESSAGE_FORMAT_WDIP = "WDIP"
    public final static String GC_SOURCE_MESSAGE_FORMAT_OTP = "OTP"

}
