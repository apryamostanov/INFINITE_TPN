package implementation

import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.transform.ToString

import static base.T_tpn_base_4_const.GC_ACCEPT
import static base.T_tpn_base_4_const.GC_ACCEPT_LANGUAGE
import static base.T_tpn_base_4_const.GC_CONNECTION
import static base.T_tpn_base_4_const.GC_CONTENT_TYPE
import static base.T_tpn_base_4_const.GC_HOST
import static base.T_tpn_base_4_const.GC_SOAPACTION
import static base.T_tpn_base_4_const.GC_USER_AGENT
import static base.T_tpn_base_5_context.c

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = true)
class T_tpn_http_message extends T_http_message {

    static final String PC_HEADER_NAME_USER_AGENT = "User-Agent"
    static final String PC_HEADER_NAME_ACCEPT_LANGUAGE = "Accept-Language"
    static final String PC_HEADER_NAME_ACCEPT_CONTENT_TYPE = "Content-Type"
    String p_trxn_id = GC_EMPTY_STRING
    String p_source = GC_EMPTY_STRING
    String p_channel_name = GC_EMPTY_STRING
    Integer p_retry_count = GC_ZERO
    Date p_post_date = GC_NULL_OBJ_REF as Date
    Integer p_tpn_internal_unique_id = GC_NULL_OBJ_REF as Integer
    String p_state = GC_EMPTY_STRING
    T_tpn_std_message_format p_tpn_std_message_format = GC_NULL_OBJ_REF as T_tpn_std_message_format
    T_tpn_otp_message_format p_tpn_otp_message_format = GC_NULL_OBJ_REF as T_tpn_otp_message_format

    @I_black_box("error")
    T_tpn_std_message_format get_tpn_standard_message_format() {
        return p_tpn_std_message_format
    }

    @I_black_box("error")
    void set_tpn_std_message_format(T_tpn_std_message_format i_tpn_standard_message_format) {
        p_tpn_std_message_format = i_tpn_standard_message_format
    }

    @I_black_box("error")
    T_tpn_otp_message_format get_otp_otp_message_format() {
        return p_tpn_otp_message_format
    }

    @I_black_box("error")
    void set_tpn_otp_message_format(T_tpn_otp_message_format i_tpn_otp_message_format) {
        p_tpn_otp_message_format = i_tpn_otp_message_format
    }

    @I_black_box("error")
    T_tpn_http_message() {

    }

    @I_black_box("error")
    T_tpn_http_message(Object i_row, String i_url) {
        set_trxn_id(Integer.parseInt(i_row.txn_id)) //this is serialize_for_db in mysql currently
        set_source(i_row.source)
        set_payload(i_row.payload)
        set_channel_name(i_row.endpoint)
        set_uri(i_url)
        set_retry_count(i_row.retry_count)
        set_state(i_row.status)
        set_tpn_internal_unique_id(i_row.tpn_internal_unique_id)
        set_method(c().GC_REQUEST_METHOD)
        set_headers()
    }

    @I_black_box("error")
    T_tpn_http_message(String i_xml) {
        p_payload = i_xml
    }

    @I_black_box("error")
    String get_state() {
        return p_state
    }

    @I_black_box("error")
    void set_state(String i_state) {
        p_state = i_state
    }

    @I_black_box("error")
    String get_trxn_id() {
        return p_trxn_id
    }

    @I_black_box("error")
    void set_trxn_id(Integer i_trxn_id) {
        p_trxn_id = i_trxn_id
    }

    @I_black_box("error")
    String get_source() {
        return p_source
    }

    @I_black_box("error")
    void set_source(String i_source) {
        p_source = i_source
    }

    @I_black_box("error")
    String get_channel_name() {
        return p_channel_name
    }

    @I_black_box("error")
    void set_channel_name(String i_channel_name) {
        p_channel_name = i_channel_name
    }

    @I_black_box("error")
    Integer get_retry_count() {
        return p_retry_count
    }

    @I_black_box("error")
    Integer set_retry_count(Integer i_retry_count) {
        p_retry_count = i_retry_count
    }

    @I_black_box("error")
    Date get_post_date() {
        return p_post_date
    }

    @I_black_box("error")
    void set_post_date(Date i_post_date) {
        p_post_date = i_post_date
    }

    @I_black_box("error")
    Integer get_tpn_internal_unique_id() {
        return p_tpn_internal_unique_id
    }

    @I_black_box("error")
    void set_tpn_internal_unique_id(Integer i_tpn_internal_unique_id) {
        p_tpn_internal_unique_id = i_tpn_internal_unique_id
    }


    @I_black_box("error")
    void set_headers() {
        if (is_not_null(c().GC_ACCEPT)) {
            set_header(GC_ACCEPT, c().GC_ACCEPT)
        }
        if (is_not_null(c().GC_ACCEPT_LANGUAGE)) {
            set_header(GC_ACCEPT_LANGUAGE, c().GC_ACCEPT_LANGUAGE)
        }
        if (is_not_null(c().GC_CONNECTION)) {
            set_header(GC_CONNECTION, c().GC_CONNECTION)
        }
        if (is_not_null(c().GC_CONTENT_TYPE)) {
            set_header(GC_CONTENT_TYPE, c().GC_CONTENT_TYPE)
        }
        if (is_not_null(c().GC_HOST)) {
            set_header(GC_HOST, c().GC_HOST)
        }
        if (is_not_null(c().GC_SOAPACTION)) {
            set_header(GC_SOAPACTION, c().GC_SOAPACTION)
        }
        if (is_not_null(c().GC_USER_AGENT)) {
            set_header(GC_USER_AGENT, c().GC_USER_AGENT)
        }
    }

}
