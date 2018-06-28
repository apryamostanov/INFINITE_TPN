package implementation

import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.transform.ToString
import base.T_tpn_base_6_util
import other.E_application_exception

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_tpn_conversion_module extends T_tpn_base_6_util {

    @I_black_box("error")
    static I_http_message std2external(T_tpn_http_message i_http_message) {
        T_tpn_std_message_format l_tpn_standard_message_format = i_http_message.get_tpn_standard_message_format()
        l_tpn_standard_message_format.UserName = c().GC_TPN_EXTERNAL_USERNAME
        l_tpn_standard_message_format.Password = c().GC_TPN_EXTERNAL_PASSWORD
        l_tpn_standard_message_format.UniqueID = i_http_message.get_tpn_internal_unique_id()
        if (nvl(i_http_message.get_retry_count(), GC_ZERO) > GC_ONE_ONLY) {
            l_tpn_standard_message_format.UniqueIDFlag = GC_UNIQUE_ID_FLAG_RETRY
            l_tpn_standard_message_format.UniqueIDflag = GC_UNIQUE_ID_FLAG_RETRY
        } else {
            l_tpn_standard_message_format.UniqueIDFlag = GC_UNIQUE_ID_FLAG_NORMAL
            l_tpn_standard_message_format.UniqueIDflag = GC_UNIQUE_ID_FLAG_NORMAL
        }
        Map l_template_variable_map = [
                "std_format": l_tpn_standard_message_format
                , "http_message": i_http_message
        ]
        if (is_null(l_tpn_standard_message_format.networkResponseCode)) {
            i_http_message.set_payload(get_template_bank().make(l_template_variable_map).toString())
        } else {
            i_http_message.set_payload(get_template_scheme().make(l_template_variable_map).toString())
        }
        return i_http_message
    }

    @I_black_box("error")
    static I_http_message otp2external(T_tpn_http_message i_http_message) {
        T_tpn_otp_message_format l_tpn_otp_message_format = i_http_message.get_otp_otp_message_format()
        l_tpn_otp_message_format.user = c().GC_TPN_EXTERNAL_USERNAME
        l_tpn_otp_message_format.password = c().GC_TPN_EXTERNAL_PASSWORD
        l_tpn_otp_message_format.from = c().GC_TPN_EXTERNAL_FROM
        Map l_template_variable_map = [
                "otp_format": l_tpn_otp_message_format
                , "http_message": i_http_message
        ]
        String l_payload = GC_EMPTY_STRING
        String l_template_name_accessor_name_lang = l_tpn_otp_message_format.accessor_name + GC_UNDERSCORE + l_tpn_otp_message_format.language + GC_UNDERSCORE + i_http_message.get_channel_name()
        String l_template_name_accessor_name_default = l_tpn_otp_message_format.accessor_name + GC_UNDERSCORE + i_http_message.get_channel_name()
        String l_template_name_source_lang = l_tpn_otp_message_format.source + GC_UNDERSCORE + l_tpn_otp_message_format.language + GC_UNDERSCORE + i_http_message.get_channel_name()
        String l_template_name_source_default = l_tpn_otp_message_format.source + GC_UNDERSCORE + i_http_message.get_channel_name()
        String l_template_name_lang = l_tpn_otp_message_format.language + GC_UNDERSCORE + i_http_message.get_channel_name()
        String l_template_name_default = l_tpn_otp_message_format.language + GC_UNDERSCORE + i_http_message.get_channel_name()
        try {
            l_payload = get_template_manager().get_template(l_template_name_accessor_name_lang).make(l_template_variable_map).toString()
        } catch (E_application_exception ignored) {}
        if (is_null(l_payload)) {
            try {
                l_payload = get_template_manager().get_template(l_template_name_accessor_name_default).make(l_template_variable_map).toString()
            } catch (E_application_exception ignored) {}
        }
        if (is_null(l_payload)) {
            try {
                l_payload = get_template_manager().get_template(l_template_name_source_lang).make(l_template_variable_map).toString()
            } catch (E_application_exception ignored) {}
        }
        if (is_null(l_payload)) {
            try {
                l_payload = get_template_manager().get_template(l_template_name_source_default).make(l_template_variable_map).toString()
            } catch (E_application_exception ignored) {}
        }
        if (is_null(l_payload)) {
            try {
                l_payload = get_template_manager().get_template(l_template_name_lang).make(l_template_variable_map).toString()
            } catch (E_application_exception ignored) {}
        }
        if (is_null(l_payload)) {
            try {
                l_payload = get_template_manager().get_template(l_template_name_default).make(l_template_variable_map).toString()
            } catch (E_application_exception ignored) {}
        }
        if (is_null(l_payload)) {
            throw new E_application_exception(s.Unable_to_find_OTP_template_for_accessor_name_Z1_source_Z2_channel_Z3_language_Z4, l_tpn_otp_message_format.accessor_name, l_tpn_otp_message_format.source, i_http_message.get_channel_name(), l_tpn_otp_message_format.language)
        }
        i_http_message.set_payload(l_payload)
        return i_http_message
    }

}
