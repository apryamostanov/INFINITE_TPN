package base

import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException
import groovy.json.JsonBuilder
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.slurpersupport.GPathResult
import implementation.T_tpn_http_message
import implementation.T_tpn_otp_message_format
import implementation.T_tpn_std_message_format
import other.T_logger

import java.sql.SQLException
import java.sql.SQLRecoverableException

@I_fix_variable_scopes
class T_tpn_base_6_util extends T_tpn_base_5_context {

    @I_black_box("error")
    static void sql_update(String i_sql_string, Object[] i_bind_variables = GC_SKIPPED_ARGS) {
        try {
            l().log_send_sql("update", i_sql_string, i_bind_variables)
            if (method_arguments_present(i_bind_variables)) {
                get_sql().executeUpdate(i_sql_string, i_bind_variables)
            } else {
                get_sql().executeUpdate(i_sql_string)
            }
            l().log_receive_sql("updated", get_sql().getUpdateCount())
            commit()
        } catch (SQLRecoverableException e_others) {
            l().log_warning(s.MySQL_connection_lost_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            sql_update(i_sql_string, i_bind_variables)
        } catch (MySQLNonTransientConnectionException e_others) {
            l().log_warning(s.MySQL_connection_lost_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            sql_update(i_sql_string, i_bind_variables)
        }
    }

    @I_black_box("error")
    static T_tpn_otp_message_format parse_otp_message_format(T_tpn_http_message i_http_message) {
        T_tpn_otp_message_format l_tpn_standard_message_format = new T_tpn_otp_message_format()
        JsonBuilder l_json_builder = i_http_message.get_json_builder()
        l_tpn_standard_message_format.language = l_json_builder.content.language
        l_tpn_standard_message_format.OTP = l_json_builder.content.OTP
        l_tpn_standard_message_format.phone = l_json_builder.content.phone
        l_tpn_standard_message_format.scope = l_json_builder.content.scope
        l_tpn_standard_message_format.source = i_http_message.p_source
        l_tpn_standard_message_format.accessor_name = l_json_builder.content.accessor_name
        return l_tpn_standard_message_format
    }

    @I_black_box("error")
    static T_tpn_std_message_format parse_std_message_format(T_tpn_http_message i_http_message) {
        T_tpn_std_message_format l_tpn_standard_message_format = new T_tpn_std_message_format()
        GPathResult l_gpath_result = new XmlSlurper().parseText(i_http_message.get_payload())
        l_tpn_standard_message_format.fiid = l_gpath_result?.Body?.TransactionNotificationRequest?.product?.institutionID?.text()
        l_tpn_standard_message_format.productID = l_gpath_result?.Body?.TransactionNotificationRequest?.product?.productID?.text()
        l_tpn_standard_message_format.productName = l_gpath_result?.Body?.TransactionNotificationRequest?.product?.productName?.text()
        l_tpn_standard_message_format.programManager = l_gpath_result?.Body?.TransactionNotificationRequest?.product?.programManager?.text()
        l_tpn_standard_message_format.productCategory = l_gpath_result?.Body?.TransactionNotificationRequest?.product?.productCategory?.text()
        l_tpn_standard_message_format.subProductType = l_gpath_result?.Body?.TransactionNotificationRequest?.product?.subProductType?.text()
        l_tpn_standard_message_format.sourceBusinessName = l_gpath_result?.Body?.TransactionNotificationRequest?.businessAccount?.sourceBusinessName?.text()
        l_tpn_standard_message_format.sourceBusinessAccountNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.businessAccount?.sourceBusinessAccountNumber?.text()
        l_tpn_standard_message_format.businessName = l_gpath_result?.Body?.TransactionNotificationRequest?.businessAccount?.businessName?.text()
        l_tpn_standard_message_format.businessAccountNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.businessAccount?.businessAccountNumber?.text()
        l_tpn_standard_message_format.sourceCardNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.card?.sourceCardNumber?.text()
        l_tpn_standard_message_format.sourceProxyNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.card?.sourceProxyNumber?.text()
        l_tpn_standard_message_format.cardNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.card?.cardNumber?.text()
        l_tpn_standard_message_format.proxyNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.card?.proxyNumber?.text()
        l_tpn_standard_message_format.cardSequenceNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.card?.cardSequenceNumber?.text()
        l_tpn_standard_message_format.sourceAccountNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.sourceAccountNumber?.text()
        l_tpn_standard_message_format.sourceAccountCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.sourceAccountCurrency?.text()
        l_tpn_standard_message_format.sourceAccountBalanceInt = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.sourceAccountBalanceInt?.text()
        l_tpn_standard_message_format.sourceAccountBalanceDec = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.sourceAccountBalanceDec?.text()
        l_tpn_standard_message_format.AccountNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.AccountNumber?.text()
        l_tpn_standard_message_format.AccountCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.AccountCurrency?.text()
        l_tpn_standard_message_format.AccountBalanceInt = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.AccountBalanceInt?.text()
        l_tpn_standard_message_format.AccountBalanceDec = l_gpath_result?.Body?.TransactionNotificationRequest?.account?.AccountBalanceDec?.text()
        l_tpn_standard_message_format.bankingCustomerId = l_gpath_result?.Body?.TransactionNotificationRequest?.card?.bankingCustomerId?.text()
        l_tpn_standard_message_format.transactionId = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionId?.text()
        l_tpn_standard_message_format.transactionCode = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionCode?.text()
        l_tpn_standard_message_format.transactionDescription = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionDescription?.text()
        l_tpn_standard_message_format.transactionTimeStamp = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionTimeStamp?.text()
        l_tpn_standard_message_format.transactionLocalDate = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionLocalDate?.text()
        l_tpn_standard_message_format.transactionLocalTime = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionLocalTime?.text()
        l_tpn_standard_message_format.transactionLifecycleState = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionLifecycleState?.text()
        l_tpn_standard_message_format.transactionOriginalId = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionOriginalId?.text()
        l_tpn_standard_message_format.messageTypeIdentifier = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.messageTypeIdentifier?.text()
        l_tpn_standard_message_format.transactionResponseCode = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionResponseCode?.text()
        l_tpn_standard_message_format.debitCreditFlag = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.debitCreditFlag?.text()
        l_tpn_standard_message_format.logicModule = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.logicModule?.text()
        l_tpn_standard_message_format.transactionPostingDate = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionPostingDate?.text()
        l_tpn_standard_message_format.transactionPostingTime = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.transactionPostingTime?.text()
        l_tpn_standard_message_format.postingFlag = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.postingFlag?.text()
        l_tpn_standard_message_format.postingReference = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.postingReference?.text()
        l_tpn_standard_message_format.postingTransactionSource = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.postingTransactionSource?.text()
        l_tpn_standard_message_format.originalTransactionSource = l_gpath_result?.Body?.TransactionNotificationRequest?.transaction?.originalTransactionSource?.text()
        l_tpn_standard_message_format.networkName = l_gpath_result?.Body?.TransactionNotificationRequest?.network?.networkName?.text()
        l_tpn_standard_message_format.networkTransactionId = l_gpath_result?.Body?.TransactionNotificationRequest?.network?.networkTransactionId?.text()
        l_tpn_standard_message_format.networkProcessingCode = l_gpath_result?.Body?.TransactionNotificationRequest?.network?.networkProcessingCode?.text()
        l_tpn_standard_message_format.networkRRN = l_gpath_result?.Body?.TransactionNotificationRequest?.network?.networkRRN?.text()
        l_tpn_standard_message_format.networkSTAN = l_gpath_result?.Body?.TransactionNotificationRequest?.network?.networkSTAN?.text()
        l_tpn_standard_message_format.networkResponseCode = l_gpath_result?.Body?.TransactionNotificationRequest?.network?.networkResponseCode?.text()
        l_tpn_standard_message_format.sourcePostedCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.sourcePostedCurrency?.text()
        l_tpn_standard_message_format.sourcePostedAmountInt = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.sourcePostedAmountInt?.text()
        l_tpn_standard_message_format.sourcePostedAmountDec = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.sourcePostedAmountDec?.text()
        l_tpn_standard_message_format.transactionCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.transactionCurrency?.text()
        l_tpn_standard_message_format.transactionAmountInt = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.transactionAmountInt?.text()
        l_tpn_standard_message_format.transactionAmountDec = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.transactionAmountDec?.text()
        l_tpn_standard_message_format.billingCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.billingCurrency?.text()
        l_tpn_standard_message_format.billingAmountInt = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.billingAmountInt?.text()
        l_tpn_standard_message_format.billingAmountDec = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.billingAmountDec?.text()
        l_tpn_standard_message_format.postedCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.postedCurrency?.text()
        l_tpn_standard_message_format.postedAmountInt = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.postedAmountInt?.text()
        l_tpn_standard_message_format.postedAmountDec = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.postedAmountDec?.text()
        l_tpn_standard_message_format.additionalAmountCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.additionalAmountCurrency?.text()
        l_tpn_standard_message_format.additionalAmountInt = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.additionalAmountInt?.text()
        l_tpn_standard_message_format.additionalAmountDec = l_gpath_result?.Body?.TransactionNotificationRequest?.amounts?.additionalAmountDec?.text()
        l_tpn_standard_message_format.acquirerId = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.acquirerId?.text()
        l_tpn_standard_message_format.acquirerCountry = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.acquirerCountry?.text()
        l_tpn_standard_message_format.acquirerInstitutionIDCode = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.acquirerInstitutionIDCode?.text()
        l_tpn_standard_message_format.storeNumber = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.storeNumber?.text()
        l_tpn_standard_message_format.terminalId = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.terminalId?.text()
        l_tpn_standard_message_format.terminalType = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.terminalType?.text()
        l_tpn_standard_message_format.terminalCategory = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.terminalCategory?.text()
        l_tpn_standard_message_format.terminalEntry = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.terminalEntry?.text()
        l_tpn_standard_message_format.cardAcceptorIDCode = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.cardAcceptorIDCode?.text()
        l_tpn_standard_message_format.cardAcceptorterminalIDCode = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.cardAcceptorterminalIDCode?.text()
        l_tpn_standard_message_format.merchantId = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.merchantId?.text()
        l_tpn_standard_message_format.merchantCode = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.merchantCode?.text()
        l_tpn_standard_message_format.merchantName = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.merchantName?.text()
        l_tpn_standard_message_format.merchantAddress = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.merchantAddress?.text()
        l_tpn_standard_message_format.merchantCity = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.merchantCity?.text()
        l_tpn_standard_message_format.merchantCountry = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.merchantCountry?.text()
        l_tpn_standard_message_format.merchantType = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.merchantType?.text()
        l_tpn_standard_message_format.posEntryMode = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.posEntryMode?.text()
        l_tpn_standard_message_format.posConditionCode = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.posConditionCode?.text()
        l_tpn_standard_message_format.posPINCaptureCode = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.posPINCaptureCode?.text()
        l_tpn_standard_message_format.nationalPOSGeographicData = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.nationalPOSGeographicData?.text()
        l_tpn_standard_message_format.chipCondition = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.chipCondition?.text()
        l_tpn_standard_message_format.chipTransactionFlag = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.chipTransactionFlag?.text()
        l_tpn_standard_message_format.paymentIndicator = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.paymentIndicator?.text()
        l_tpn_standard_message_format.cardholderId = l_gpath_result?.Body?.TransactionNotificationRequest?.pos_merchant?.cardholderId?.text()
        l_tpn_standard_message_format.availableBalance = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.availableBalance?.text()
        l_tpn_standard_message_format.currentBalance = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.currentBalance?.text()
        l_tpn_standard_message_format.lastCreditAmount = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.lastCreditAmount?.text()
        l_tpn_standard_message_format.lastCreditDate = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.lastCreditDate?.text()
        l_tpn_standard_message_format.lastTransactionDate = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.lastTransactionDate?.text()
        l_tpn_standard_message_format.isDeclined = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.isDeclined?.text()
        l_tpn_standard_message_format.transactionType = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.transactionType?.text()
        l_tpn_standard_message_format.settleAmount = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.settleAmount?.text()
        l_tpn_standard_message_format.settleCurrency = l_gpath_result?.Body?.TransactionNotificationRequest?.additionalData?.settleCurrency?.text()
        l_tpn_standard_message_format.purchasingData = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_48_data?.purchasingData?.text()
        l_tpn_standard_message_format.Purchase_Type = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Purchase_Type?.text()
        l_tpn_standard_message_format.Service_Type = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Service_Type?.text()
        l_tpn_standard_message_format.Fuel_Type = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Fuel_Type?.text()
        l_tpn_standard_message_format.Unit_Measure = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Unit_Measure?.text()
        l_tpn_standard_message_format.Quantity = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Quantity?.text()
        l_tpn_standard_message_format.Unit_Cost = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Unit_Cost?.text()
        l_tpn_standard_message_format.Gross_Fuel_Price = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Gross_Fuel_Price?.text()
        l_tpn_standard_message_format.Net_Fuel_Price = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Net_Fuel_Price?.text()
        l_tpn_standard_message_format.Gross_NonFuel_Price = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Gross_NonFuel_Price?.text()
        l_tpn_standard_message_format.Net_NonFuel_Price = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Net_NonFuel_Price?.text()
        l_tpn_standard_message_format.Odometer_Reading = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Odometer_Reading?.text()
        l_tpn_standard_message_format.VAT_Rate = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.VAT_Rate?.text()
        l_tpn_standard_message_format.Misc_FuelTax_ExStatus = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Misc_FuelTax_ExStatus?.text()
        l_tpn_standard_message_format.Misc_FuelTax = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Misc_FuelTax?.text()
        l_tpn_standard_message_format.Misc_NonFuelTax_ExStatus = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Misc_NonFuelTax_ExStatus?.text()
        l_tpn_standard_message_format.Misc_NonFuelTax = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Misc_NonFuelTax?.text()
        l_tpn_standard_message_format.Local_Tax_Incl = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Local_Tax_Incl?.text()
        l_tpn_standard_message_format.Local_Tax = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Local_Tax?.text()
        l_tpn_standard_message_format.National_Tax_Incl = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.National_Tax_Incl?.text()
        l_tpn_standard_message_format.National_Tax = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.National_Tax?.text()
        l_tpn_standard_message_format.Other_Tax = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Other_Tax?.text()
        l_tpn_standard_message_format.Merchant_VAT_Reg = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Merchant_VAT_Reg?.text()
        l_tpn_standard_message_format.Customer_VAT_Reg = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Customer_VAT_Reg?.text()
        l_tpn_standard_message_format.Customer_Ref_No = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Customer_Ref_No?.text()
        l_tpn_standard_message_format.Message_Identifier = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Message_Identifier?.text()
        l_tpn_standard_message_format.Addtl_Data_Flag = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Addtl_Data_Flag?.text()
        l_tpn_standard_message_format.Summ_Comm_Code = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Summ_Comm_Code?.text()
        l_tpn_standard_message_format.Non_FuelCode_01 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_01?.text()
        l_tpn_standard_message_format.Non_FuelCode_02 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_02?.text()
        l_tpn_standard_message_format.Non_FuelCode_03 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_03?.text()
        l_tpn_standard_message_format.Non_FuelCode_04 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_04?.text()
        l_tpn_standard_message_format.Non_FuelCode_05 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_05?.text()
        l_tpn_standard_message_format.Non_FuelCode_06 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_06?.text()
        l_tpn_standard_message_format.Non_FuelCode_07 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_07?.text()
        l_tpn_standard_message_format.Non_FuelCode_08 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Non_FuelCode_08?.text()
        l_tpn_standard_message_format.Fuel_Brand = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Fuel_Brand?.text()
        l_tpn_standard_message_format.Fuel_Txn_VResults = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Fuel_Txn_VResults?.text()
        l_tpn_standard_message_format.Fuel_Accpt_Mode = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Fuel_Accpt_Mode?.text()
        l_tpn_standard_message_format.Driver_Id = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Driver_Id?.text()
        l_tpn_standard_message_format.Job_Number = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Job_Number?.text()
        l_tpn_standard_message_format.Fleet_Number = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Fleet_Number?.text()
        l_tpn_standard_message_format.Vehicle_Reg_No = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Vehicle_Reg_No?.text()
        l_tpn_standard_message_format.Product_Qualifier = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Product_Qualifier?.text()
        l_tpn_standard_message_format.Expanded_Fuel_Type = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_104_data?.Expanded_Fuel_Type?.text()
        l_tpn_standard_message_format.expNonFuelCode01 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode01?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty01 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty01?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost01 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost01?.text()
        l_tpn_standard_message_format.expNonFuelCode02 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode02?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty02 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty02?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost02 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost02?.text()
        l_tpn_standard_message_format.expNonFuelCode03 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode03?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty03 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty03?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost03 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost03?.text()
        l_tpn_standard_message_format.expNonFuelCode04 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode04?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty04 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty04?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost04 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost04?.text()
        l_tpn_standard_message_format.expNonFuelCode05 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode05?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty05 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty05?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost05 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost05?.text()
        l_tpn_standard_message_format.expNonFuelCode06 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode06?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty06 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty06?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost06 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost06?.text()
        l_tpn_standard_message_format.expNonFuelCode07 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode07?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty07 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty07?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost07 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost07?.text()
        l_tpn_standard_message_format.expNonFuelCode08 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCode08?.text()
        l_tpn_standard_message_format.expNonFuelCodeQty08 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeQty08?.text()
        l_tpn_standard_message_format.expNonFuelCodeCost08 = l_gpath_result?.Body?.TransactionNotificationRequest?.fleet_125_data?.expNonFuelCodeCost08?.text()
        l_gpath_result?.Body?.TransactionNotificationRequest?.fees?.fee?.each() { l_fee->
        l_tpn_standard_message_format.fees += """<a:fee><a:feeName>${l_fee.feeName?.text()}</a:feeName>
          <a:feeAmountCurrency>${l_fee.feeAmountCurrency?.text()}</a:feeAmountCurrency>
          <a:feeAmountInt>${l_fee.feeAmountInt?.text()}</a:feeAmountInt>
          <a:feeAmountDec>${l_fee.feeAmountDec?.text()}</a:feeAmountDec></a:fee>""".toString()
        }
        return l_tpn_standard_message_format
    }

    @I_black_box("error")
    static void inject_header(T_tpn_http_message i_http_message) {
        Node l_parsed_xml = new XmlParser().parseText(i_http_message.get_payload())
        Node l_soap_body_node = (Node) l_parsed_xml.children().get(GC_SECOND_INDEX)
        Node l_payload_node = (Node) l_soap_body_node.children().get(GC_FIRST_INDEX)
        Node l_header_node = new Node(l_payload_node, "header", ["xmlns:a": "https://wp1.wirecard.com/TransactionNotification/Fleet"])
        Node l_username_node = new Node(l_header_node, "a:UserName")
        Node l_password_node = new Node(l_header_node, "a:Password")
        Node l_unique_id_node = new Node(l_header_node, "a:UniqueID")
        Node l_unique_id_flag_node = new Node(l_header_node, "a:UniqueIDFlag")
        l_username_node.setValue(c().GC_TPN_EXTERNAL_USERNAME)
        l_password_node.setValue(c().GC_TPN_EXTERNAL_PASSWORD)
        l_unique_id_node.setValue(i_http_message.get_tpn_internal_unique_id())
        if (nvl(i_http_message.get_retry_count(), GC_ZERO) > GC_ONE_ONLY) {
            l_unique_id_flag_node.setValue(GC_UNIQUE_ID_FLAG_RETRY)
        } else {
            l_unique_id_flag_node.setValue(GC_UNIQUE_ID_FLAG_NORMAL)
        }

        l_payload_node.children().remove(l_header_node)
        l_payload_node.children().add(GC_FIRST_INDEX, l_header_node)
        StringWriter l_new_payload = new StringWriter()
        XmlNodePrinter l_xml_node_printer = new XmlNodePrinter(new PrintWriter(l_new_payload))
        l_xml_node_printer.with {
            preserveWhitespace = true
            expandEmptyElements = true
            expandEmptyElements = false
        }
        l_xml_node_printer.print(l_parsed_xml)
        i_http_message.set_payload(l_new_payload.toString())
    }

    @I_black_box("error")
    static String serialize_for_db(I_http_message l_http_response) {
        return substr(l_http_response.toString(), GC_FIRST_CHAR, GC_MYSQL_VARCHAR_LIMIT)
    }

    @I_black_box("error")
    static void commit() {
        try {
            l().log_send_sql("commit")
            get_sql().commit()
            l().log_receive_sql("commit_finished")
        } catch (SQLRecoverableException e_others) {
            l().log_warning(s.MySQL_connection_lost_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            commit()
        } catch (MySQLNonTransientConnectionException e_others) {
            l().log_warning(s.MySQL_connection_lost_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            commit()
        }
    }

    @I_black_box("error")
    static Sql get_sql() {
        if ((System.currentTimeMillis() - get_context().p_sql_last_init_time_millis) >= new Long(c().GC_SQL_SESSION_REFRESH_INTERVAL_MILLISECONDS)) {
            init_sql()
        }
        return get_context().p_sql
    }

    @I_black_box("error")
    static void each_row(String i_sql, Closure i_closure) throws SQLException {
        try {
            get_sql().eachRow(i_sql, i_closure)
        } catch (SQLRecoverableException e_others) {
            l().log_warning(s.MySQL_healthcheck_failed, e_others)
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            each_row(i_sql, i_closure)
        } catch (MySQLNonTransientConnectionException e_others) {
            l().log_warning(s.MySQL_connection_lost_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            each_row(i_sql, i_closure)
        }
    }

    @I_black_box("error")
    static GroovyRowResult first_row(String i_sql) throws SQLException {
        try {
            return get_sql().firstRow(i_sql)
        } catch (SQLRecoverableException e_others) {
            l().log_warning(s.MySQL_connection_lost_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            return first_row(i_sql)
        } catch (MySQLNonTransientConnectionException e_others) {
            l().log_warning(s.MySQL_connection_lost_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            while (not(reconnect())) {
                sleep(GC_SQL_RECONNECT_RETRY_PERIOD_MILLISECONDS)
            }
            l().log_warning(s.MySQL_connectivity_restored)
            return first_row(i_sql)
        }
    }

    private static Boolean reconnect() {
        Integer LC_IS_OK_TRUE = 1
        String l_healthcheck_sql = """select 1 as is_ok"""
        try {
            l().log_warning(s.Trying_to_reconnect)
            init_sql()
            get_context().p_sql.eachRow(l_healthcheck_sql) { l_healthcheck_row ->
                if (l_healthcheck_row.is_ok != LC_IS_OK_TRUE) {
                    l().log_warning(s.Unexpected_healthcheck_result_Z1, l_healthcheck_row.is_ok)
                    return GC_FALSE
                }
            }
        } catch (SQLRecoverableException e_others) {
            l().log_warning(s.Exception_during_MySQL_reconnect_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            return GC_FALSE
        } catch (MySQLNonTransientConnectionException e_others) {
            l().log_warning(s.Exception_during_MySQL_reconnect_Z1_Z2, e_others.getClass().getSimpleName(), e_others.getMessage())
            return GC_FALSE
        }
        return GC_TRUE
    }

    @I_black_box("error")
    static T_logger l() {
        return T_logging_base_5_context.l()
    }

}
