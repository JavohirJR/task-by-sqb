package com.example.taskbysqb.service;

import wsdl.GetInformationArguments;
import wsdl.GetInformationResult;
import wsdl.PerformTransactionArguments;
import wsdl.PerformTransactionResult;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
public interface PaymentService {
    PerformTransactionResult performTransaction(PerformTransactionArguments arguments);
    GetInformationResult getInformation(GetInformationArguments arguments);
}
