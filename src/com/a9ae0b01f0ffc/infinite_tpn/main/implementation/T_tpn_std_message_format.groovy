package implementation

import base.T_tpn_base_6_util

class T_tpn_std_message_format extends T_tpn_base_6_util {

    String UserName = GC_EMPTY_STRING
    String Password = GC_EMPTY_STRING
    String UniqueID = GC_EMPTY_STRING
    String UniqueIDFlag = GC_EMPTY_STRING
    String UniqueIDflag = GC_EMPTY_STRING
    String productID = GC_EMPTY_STRING
    String productName = GC_EMPTY_STRING
    String programManager = GC_EMPTY_STRING
    String productCategory = GC_EMPTY_STRING
    String subProductType = GC_EMPTY_STRING
    String sourceBusinessName = GC_EMPTY_STRING
    String sourceBusinessAccountNumber = GC_EMPTY_STRING
    String businessName = GC_EMPTY_STRING
    String businessAccountNumber = GC_EMPTY_STRING
    String sourceCardNumber = GC_EMPTY_STRING
    String sourceProxyNumber = GC_EMPTY_STRING
    String cardNumber = GC_EMPTY_STRING
    String proxyNumber = GC_EMPTY_STRING
    String cardSequenceNumber = GC_EMPTY_STRING
    String sourceAccountNumber = GC_EMPTY_STRING
    String sourceAccountCurrency = GC_EMPTY_STRING
    String sourceAccountBalanceInt = GC_EMPTY_STRING
    String sourceAccountBalanceDec = GC_EMPTY_STRING
    String AccountNumber = GC_EMPTY_STRING
    String AccountCurrency = GC_EMPTY_STRING
    String AccountBalanceInt = GC_EMPTY_STRING
    String AccountBalanceDec = GC_EMPTY_STRING
    String transactionId = GC_EMPTY_STRING
    String transactionCode = GC_EMPTY_STRING
    String transactionDescription = GC_EMPTY_STRING
    String transactionTimeStamp = GC_EMPTY_STRING
    String transactionLocalDate = GC_EMPTY_STRING
    String transactionLocalTime = GC_EMPTY_STRING
    String transactionLifecycleState = GC_EMPTY_STRING
    String transactionOriginalId = GC_EMPTY_STRING
    String messageTypeIdentifier = GC_EMPTY_STRING
    String transactionResponseCode = GC_EMPTY_STRING
    String debitCreditFlag = GC_EMPTY_STRING
    String logicModule = GC_EMPTY_STRING
    String transactionPostingDate = GC_EMPTY_STRING
    String transactionPostingTime = GC_EMPTY_STRING
    String postingFlag = GC_EMPTY_STRING
    String postingReference = GC_EMPTY_STRING
    String postingTransactionSource = GC_EMPTY_STRING
    String originalTransactionSource = GC_EMPTY_STRING
    String networkName = GC_EMPTY_STRING
    String networkTransactionId = GC_EMPTY_STRING
    String networkProcessingCode = GC_EMPTY_STRING
    String networkRRN = GC_EMPTY_STRING
    String networkSTAN = GC_EMPTY_STRING
    String networkResponseCode = GC_EMPTY_STRING
    String sourcePostedCurrency = GC_EMPTY_STRING
    String sourcePostedAmountInt = GC_EMPTY_STRING
    String sourcePostedAmountDec = GC_EMPTY_STRING
    String transactionCurrency = GC_EMPTY_STRING
    String transactionAmountInt = GC_EMPTY_STRING
    String transactionAmountDec = GC_EMPTY_STRING
    String billingCurrency = GC_EMPTY_STRING
    String billingAmountInt = GC_EMPTY_STRING
    String billingAmountDec = GC_EMPTY_STRING
    String postedCurrency = GC_EMPTY_STRING
    String postedAmountInt = GC_EMPTY_STRING
    String postedAmountDec = GC_EMPTY_STRING
    String additionalAmountCurrency = GC_EMPTY_STRING
    String additionalAmountInt = GC_EMPTY_STRING
    String additionalAmountDec = GC_EMPTY_STRING
    String acquirerId = GC_EMPTY_STRING
    String acquirerCountry = GC_EMPTY_STRING
    String acquirerInstitutionIDCode = GC_EMPTY_STRING
    String storeNumber = GC_EMPTY_STRING
    String terminalId = GC_EMPTY_STRING
    String terminalType = GC_EMPTY_STRING
    String terminalCategory = GC_EMPTY_STRING
    String terminalEntry = GC_EMPTY_STRING
    String cardAcceptorIDCode = GC_EMPTY_STRING
    String cardAcceptorterminalIDCode = GC_EMPTY_STRING
    String merchantId = GC_EMPTY_STRING
    String merchantCode = GC_EMPTY_STRING
    String merchantName = GC_EMPTY_STRING
    String merchantAddress = GC_EMPTY_STRING
    String merchantCity = GC_EMPTY_STRING
    String merchantCountry = GC_EMPTY_STRING
    String merchantType = GC_EMPTY_STRING
    String posEntryMode = GC_EMPTY_STRING
    String posConditionCode = GC_EMPTY_STRING
    String posPINCaptureCode = GC_EMPTY_STRING
    String nationalPOSGeographicData = GC_EMPTY_STRING
    String chipCondition = GC_EMPTY_STRING
    String chipTransactionFlag = GC_EMPTY_STRING
    String paymentIndicator = GC_EMPTY_STRING
    String cardholderId = GC_EMPTY_STRING
    String purchasingData = GC_EMPTY_STRING
    String Purchase_Type = GC_EMPTY_STRING
    String Service_Type = GC_EMPTY_STRING
    String Fuel_Type = GC_EMPTY_STRING
    String Unit_Measure = GC_EMPTY_STRING
    String Quantity = GC_EMPTY_STRING
    String Unit_Cost = GC_EMPTY_STRING
    String Gross_Fuel_Price = GC_EMPTY_STRING
    String Net_Fuel_Price = GC_EMPTY_STRING
    String Gross_NonFuel_Price = GC_EMPTY_STRING
    String Net_NonFuel_Price = GC_EMPTY_STRING
    String Odometer_Reading = GC_EMPTY_STRING
    String VAT_Rate = GC_EMPTY_STRING
    String Misc_FuelTax_ExStatus = GC_EMPTY_STRING
    String Misc_FuelTax = GC_EMPTY_STRING
    String Misc_NonFuelTax_ExStatus = GC_EMPTY_STRING
    String Misc_NonFuelTax = GC_EMPTY_STRING
    String Local_Tax_Incl = GC_EMPTY_STRING
    String Local_Tax = GC_EMPTY_STRING
    String National_Tax_Incl = GC_EMPTY_STRING
    String National_Tax = GC_EMPTY_STRING
    String Other_Tax = GC_EMPTY_STRING
    String Merchant_VAT_Reg = GC_EMPTY_STRING
    String Customer_VAT_Reg = GC_EMPTY_STRING
    String Customer_Ref_No = GC_EMPTY_STRING
    String Message_Identifier = GC_EMPTY_STRING
    String Addtl_Data_Flag = GC_EMPTY_STRING
    String Summ_Comm_Code = GC_EMPTY_STRING
    String Non_FuelCode_01 = GC_EMPTY_STRING
    String Non_FuelCode_02 = GC_EMPTY_STRING
    String Non_FuelCode_03 = GC_EMPTY_STRING
    String Non_FuelCode_04 = GC_EMPTY_STRING
    String Non_FuelCode_05 = GC_EMPTY_STRING
    String Non_FuelCode_06 = GC_EMPTY_STRING
    String Non_FuelCode_07 = GC_EMPTY_STRING
    String Non_FuelCode_08 = GC_EMPTY_STRING
    String Fuel_Brand = GC_EMPTY_STRING
    String Fuel_Txn_VResults = GC_EMPTY_STRING
    String Fuel_Accpt_Mode = GC_EMPTY_STRING
    String Driver_Id = GC_EMPTY_STRING
    String Job_Number = GC_EMPTY_STRING
    String Fleet_Number = GC_EMPTY_STRING
    String Vehicle_Reg_No = GC_EMPTY_STRING
    String Product_Qualifier = GC_EMPTY_STRING
    String Expanded_Fuel_Type = GC_EMPTY_STRING
    String expNonFuelCode01 = GC_EMPTY_STRING
    String expNonFuelCodeQty01 = GC_EMPTY_STRING
    String expNonFuelCodeCost01 = GC_EMPTY_STRING
    String expNonFuelCode02 = GC_EMPTY_STRING
    String expNonFuelCodeQty02 = GC_EMPTY_STRING
    String expNonFuelCodeCost02 = GC_EMPTY_STRING
    String expNonFuelCode03 = GC_EMPTY_STRING
    String expNonFuelCodeQty03 = GC_EMPTY_STRING
    String expNonFuelCodeCost03 = GC_EMPTY_STRING
    String expNonFuelCode04 = GC_EMPTY_STRING
    String expNonFuelCodeQty04 = GC_EMPTY_STRING
    String expNonFuelCodeCost04 = GC_EMPTY_STRING
    String expNonFuelCode05 = GC_EMPTY_STRING
    String expNonFuelCodeQty05 = GC_EMPTY_STRING
    String expNonFuelCodeCost05 = GC_EMPTY_STRING
    String expNonFuelCode06 = GC_EMPTY_STRING
    String expNonFuelCodeQty06 = GC_EMPTY_STRING
    String expNonFuelCodeCost06 = GC_EMPTY_STRING
    String expNonFuelCode07 = GC_EMPTY_STRING
    String expNonFuelCodeQty07 = GC_EMPTY_STRING
    String expNonFuelCodeCost07 = GC_EMPTY_STRING
    String expNonFuelCode08 = GC_EMPTY_STRING
    String expNonFuelCodeQty08 = GC_EMPTY_STRING
    String expNonFuelCodeCost08 = GC_EMPTY_STRING
    String availableBalance = GC_EMPTY_STRING
    String currentBalance = GC_EMPTY_STRING
    String lastCreditAmount = GC_EMPTY_STRING
    String lastCreditDate = GC_EMPTY_STRING
    String lastTransactionDate = GC_EMPTY_STRING
    String isDeclined = GC_EMPTY_STRING
    String transactionType = GC_EMPTY_STRING
    String settleAmount = GC_EMPTY_STRING
    String settleCurrency = GC_EMPTY_STRING
    String fees = GC_EMPTY_STRING

}
