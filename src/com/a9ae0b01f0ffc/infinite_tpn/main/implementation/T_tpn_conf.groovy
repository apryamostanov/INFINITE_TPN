package implementation

import other.T_common_conf
import static base.T_logging_base_4_const.*

class T_tpn_conf extends T_common_conf {

    /*
    TODO: Headers config, multiple channels, SMS Global fix, Duplicate OTP check (by source); use groovy scripts instead of templates; escaping; archiving
     */

    String GC_TPN_CLASSES_CONF
    String GC_BLACK_BOX_CONFIG
    String GC_THREAD_CONFIG_FILE_NAME
    String GC_REQUEST_METHOD
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
    String GC_ACCEPT
    String GC_ACCEPT_LANGUAGE
    String GC_CONNECTION
    String GC_CONTENT_TYPE
    String GC_HOST
    String GC_SOAPACTION
    String GC_USER_AGENT
    String GC_SOURCE_MESSAGE_FORMAT
    String GC_TPN_EXTERNAL_FROM
    String GC_OTP_TEMPLATES_PATH
    String GC_OTP_DEFAULT_LANGUAGE

    T_tpn_conf(String i_conf_file_name) {
        super(i_conf_file_name)
    }

    @Override
    void refresh_config() {
        GC_TPN_CLASSES_CONF = nvl_empty_map(get_conf().tpn_classes_conf, GC_TPN_CLASSES_CONF)
        GC_BLACK_BOX_CONFIG = nvl_empty_map(get_conf().black_box_config, GC_BLACK_BOX_CONFIG)
        GC_THREAD_CONFIG_FILE_NAME = nvl_empty_map(get_conf().thread_config_file_name, GC_THREAD_CONFIG_FILE_NAME)
        GC_REQUEST_METHOD = nvl_empty_map(get_conf().request_method, GC_REQUEST_METHOD)
        GC_USER_AGENT = nvl_empty_map(get_conf().user_agent, GC_USER_AGENT)
        GC_ACCEPT_LANGUAGE = nvl_empty_map(get_conf().accept_language, GC_ACCEPT_LANGUAGE)
        GC_CONTENT_TYPE = nvl_empty_map(get_conf().content_type, GC_CONTENT_TYPE)
        GC_CYCLE_INTERVAL_MILLISECONDS = nvl_empty_map(get_conf().cycle_interval_milliseconds, GC_CYCLE_INTERVAL_MILLISECONDS)
        GC_RESEND_INTERVAL_SECONDS = nvl_empty_map(get_conf().resend_interval_seconds, GC_RESEND_INTERVAL_SECONDS)
        GC_MYSQL_CONNECTION_STRING = nvl_empty_map(get_conf().mysql_connection_string, GC_MYSQL_CONNECTION_STRING)
        GC_MYSQL_USERNAME = nvl_empty_map(get_conf().mysql_username, GC_MYSQL_USERNAME)
        GC_MYSQL_PASSWORD = nvl_empty_map(get_conf().mysql_password, GC_MYSQL_PASSWORD)
        GC_MYSQL_DRIVER = nvl_empty_map(get_conf().mysql_driver, GC_MYSQL_DRIVER)
        GC_MODE = nvl_empty_map(get_conf().mode, GC_MODE)
        GC_POLL_LIMIT = nvl_empty_map(get_conf().poll_limit, GC_POLL_LIMIT)
        GC_PAYLOAD_TYPE = nvl_empty_map(get_conf().payload_type, GC_PAYLOAD_TYPE)
        GC_CHECK_DUPLICATES = nvl_empty_map(get_conf().check_duplicates, GC_CHECK_DUPLICATES)
        GC_MAX_RETRY_COUNT = nvl_empty_map(get_conf().max_retry_count, GC_MAX_RETRY_COUNT)
        GC_USE_CONVERSION_TEMPLATES = nvl_empty_map(get_conf().use_conversion_templates, GC_USE_CONVERSION_TEMPLATES)
        GC_SQL_SESSION_REFRESH_INTERVAL_MILLISECONDS = nvl_empty_map(get_conf().sql_session_refresh_interval_milliseconds, GC_SQL_SESSION_REFRESH_INTERVAL_MILLISECONDS)
        GC_TEMPLATE_NAME_SCHEME = nvl_empty_map(get_conf().template_name_scheme, GC_TEMPLATE_NAME_SCHEME)
        GC_TEMPLATE_NAME_BANK = nvl_empty_map(get_conf().template_name_bank, GC_TEMPLATE_NAME_BANK)
        GC_TPN_EXTERNAL_USERNAME = nvl_empty_map(get_conf().tpn_external_username, GC_TPN_EXTERNAL_USERNAME)
        GC_TPN_EXTERNAL_PASSWORD = nvl_empty_map(get_conf().tpn_external_password, GC_TPN_EXTERNAL_PASSWORD)
        GC_TPN_SERVICE_NAME = nvl_empty_map(get_conf().tpn_service_name, GC_TPN_SERVICE_NAME)
        GC_TPN_MAX_MESSAGE_AGE = nvl_empty_map(get_conf().tpn_max_message_age, GC_TPN_MAX_MESSAGE_AGE)
        GC_ACCEPT = nvl_empty_map(get_conf().accept, GC_ACCEPT)
        GC_CONNECTION = nvl_empty_map(get_conf().connection, GC_CONNECTION)
        GC_HOST = nvl_empty_map(get_conf().host, GC_HOST)
        GC_SOAPACTION = nvl_empty_map(get_conf().soapaction, GC_SOAPACTION)
        GC_SOURCE_MESSAGE_FORMAT = nvl_empty_map(get_conf().source_message_format, GC_SOURCE_MESSAGE_FORMAT)
        GC_TPN_EXTERNAL_FROM = nvl_empty_map(get_conf().tpn_external_from, GC_TPN_EXTERNAL_FROM)
        GC_OTP_TEMPLATES_PATH = nvl_empty_map(get_conf().otp_templates_path, GC_OTP_TEMPLATES_PATH)
        GC_OTP_DEFAULT_LANGUAGE = nvl_empty_map(get_conf().otp_default_language, GC_OTP_DEFAULT_LANGUAGE)
    }
}
