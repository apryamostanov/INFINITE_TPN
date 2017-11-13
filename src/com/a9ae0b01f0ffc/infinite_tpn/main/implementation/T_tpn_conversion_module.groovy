package implementation

import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.transform.ToString
import base.T_tpn_base_6_util

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

        i_http_message.set_payload(get_template_manager().get_template(l_tpn_otp_message_format.language + GC_UNDERSCORE + i_http_message.get_channel_name()).make(l_template_variable_map).toString())
        return i_http_message
    }

}
