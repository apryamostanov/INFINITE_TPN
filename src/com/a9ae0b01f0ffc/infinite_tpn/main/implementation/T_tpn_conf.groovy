package implementation

import other.T_common_conf

class T_tpn_conf extends T_common_conf {

    String GC_TPN_CLASSES_CONF
    String GC_BLACK_BOX_CONFIG
    String GC_THREAD_CONFIG_FILE_NAME
    String GC_REQUEST_METHOD
    String GC_USER_AGENT
    String GC_ACCEPT_LANGUAGE
    String GC_CONTENT_TYPE
    String GC_CYCLE_INTERVAL_MILLISECONDS
    String GC_RESEND_INTERVAL_SECONDS
    String GC_MYSQL_CONNECTION_STRING
    String GC_MYSQL_USERNAME
    String GC_MYSQL_PASSWORD
    String GC_MYSQL_DRIVER
    String GC_MODE
    String GC_MAX_RETRY_COUNT
    String GC_POLL_LIMIT
    String GC_PAYLOAD_TYPE
    String GC_CHECK_DUPLICATES
    String GC_USE_CONVERSION_TEMPLATES
    String GC_SQL_SESSION_REFRESH_INTERVAL_MILLISECONDS
    String GC_TEMPLATE_NAME_SCHEME
    String GC_TEMPLATE_NAME_BANK
    String GC_TPN_EXTERNAL_USERNAME
    String GC_TPN_EXTERNAL_PASSWORD
    String GC_TPN_SERVICE_NAME
    String GC_TPN_MAX_MESSAGE_AGE

    T_tpn_conf(String i_conf_file_name) {
        super(i_conf_file_name)
        GC_TPN_CLASSES_CONF = get_conf().tpn_classes_conf
        GC_BLACK_BOX_CONFIG = get_conf().black_box_config
        GC_THREAD_CONFIG_FILE_NAME = get_conf().thread_config_file_name
        GC_REQUEST_METHOD = get_conf().request_method
        GC_USER_AGENT = get_conf().user_agent
        GC_ACCEPT_LANGUAGE = get_conf().accept_language
        GC_CONTENT_TYPE = get_conf().content_type
        GC_CYCLE_INTERVAL_MILLISECONDS = get_conf().cycle_interval_milliseconds
        GC_RESEND_INTERVAL_SECONDS = get_conf().resend_interval_seconds
        GC_MYSQL_CONNECTION_STRING = get_conf().mysql_connection_string
        GC_MYSQL_USERNAME = get_conf().mysql_username
        GC_MYSQL_PASSWORD = get_conf().mysql_password
        GC_MYSQL_DRIVER = get_conf().mysql_driver
        GC_MODE = get_conf().mode
        GC_POLL_LIMIT = get_conf().poll_limit
        GC_PAYLOAD_TYPE = get_conf().payload_type
        GC_CHECK_DUPLICATES = get_conf().check_duplicates
        GC_MAX_RETRY_COUNT = get_conf().max_retry_count
        GC_USE_CONVERSION_TEMPLATES = get_conf().use_conversion_templates
        GC_SQL_SESSION_REFRESH_INTERVAL_MILLISECONDS = get_conf().sql_session_refresh_interval_milliseconds
        GC_TEMPLATE_NAME_SCHEME = get_conf().template_name_scheme
        GC_TEMPLATE_NAME_BANK = get_conf().template_name_bank
        GC_TPN_EXTERNAL_USERNAME = get_conf().tpn_external_username
        GC_TPN_EXTERNAL_PASSWORD = get_conf().tpn_external_password
        GC_TPN_SERVICE_NAME = get_conf().tpn_service_name
        GC_TPN_MAX_MESSAGE_AGE = get_conf().tpn_max_message_age
    }

}
