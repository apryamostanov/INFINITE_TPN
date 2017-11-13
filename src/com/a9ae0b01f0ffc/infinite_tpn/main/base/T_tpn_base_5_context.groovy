package base

import base.T_logging_base_5_context
import base.T_logging_base_6_util
import implementation.T_tpn_conf
import groovy.sql.Sql
import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import base.T_middleware_base_5_app_context
import other.T_template_manager

class T_tpn_base_5_context extends T_tpn_base_4_const {

    protected static ThreadLocal<T_tpn_base_5_context> p_context_thread_local = new ThreadLocal<T_tpn_base_5_context>()
    protected Sql p_sql = GC_NULL_OBJ_REF as Sql
    private T_tpn_conf p_commons = GC_NULL_OBJ_REF as T_tpn_conf
    protected Long p_sql_last_init_time_millis = GC_NULL_OBJ_REF as Long
    protected Template p_template_scheme = GC_NULL_OBJ_REF as Template
    protected Template p_template_bank = GC_NULL_OBJ_REF as Template
    protected T_template_manager p_otp_template_manager = GC_NULL_OBJ_REF as T_template_manager

    static void init_custom(String i_commons_conf_file_name) {
        get_context().p_commons = new T_tpn_conf(i_commons_conf_file_name)
        T_logging_base_5_context.init_custom(c().GC_BLACK_BOX_CONFIG)
        T_middleware_base_5_app_context l_middleware_base_5_app_context = new T_middleware_base_5_app_context()
        l_middleware_base_5_app_context.init_app_context(i_commons_conf_file_name)
        init_sql()
        init_templates()
    }

    static synchronized void init_templates() {
        if (is_not_null(c().GC_TEMPLATE_NAME_SCHEME) && is_not_null(c().GC_TEMPLATE_NAME_BANK)) {
            get_context().p_template_scheme = new SimpleTemplateEngine().createTemplate(new File(c().GC_TEMPLATE_NAME_SCHEME))
            get_context().p_template_bank = new SimpleTemplateEngine().createTemplate(new File(c().GC_TEMPLATE_NAME_BANK))
        }
        if (is_not_null(c().GC_OTP_TEMPLATES_PATH)) {
            get_context().p_otp_template_manager = new T_template_manager()
            get_context().p_otp_template_manager.init_templates(c().GC_OTP_TEMPLATES_PATH)
        }
    }

    static T_template_manager get_template_manager() {
        return get_context().p_otp_template_manager
    }

    static Template get_template_scheme() {
        return get_context().p_template_scheme
    }


    static Template get_template_bank() {
        return get_context().p_template_bank
    }

    static void init_sql() {
        T_logging_base_6_util.l().log_send_sql("Connecting")
        get_context().p_sql = Sql.newInstance(c().GC_MYSQL_CONNECTION_STRING, c().GC_MYSQL_USERNAME, c().GC_MYSQL_PASSWORD, c().GC_MYSQL_DRIVER)
        get_context().p_sql.getConnection().setAutoCommit(GC_FALSE)
        get_context().p_sql_last_init_time_millis = System.currentTimeMillis()
        T_logging_base_6_util.l().log_receive_sql("Connected")
    }

    static T_tpn_base_5_context get_context() {
        if (is_null(p_context_thread_local.get())) {
            p_context_thread_local.set(new T_tpn_base_5_context())
        }
        return p_context_thread_local.get()
    }

    static T_tpn_conf c() {
        return get_context().p_commons
    }

}
