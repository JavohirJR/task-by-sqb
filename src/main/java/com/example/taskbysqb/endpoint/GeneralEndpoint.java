package com.example.taskbysqb.endpoint;

import com.example.taskbysqb.security.CheckCredential;
import com.example.taskbysqb.service.PaymentService;
import com.example.taskbysqb.service.PaymentServiceFactory;
import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import wsdl.CancelTransactionArguments;
import wsdl.CancelTransactionResult;
import wsdl.ChangePasswordArguments;
import wsdl.ChangePasswordResult;
import wsdl.CheckTransactionArguments;
import wsdl.CheckTransactionResult;
import wsdl.GetInformationArguments;
import wsdl.GetInformationResult;
import wsdl.GetStatementArguments;
import wsdl.GetStatementResult;
import wsdl.PerformTransactionArguments;
import wsdl.PerformTransactionResult;

import javax.xml.namespace.QName;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */


@Endpoint
@RequiredArgsConstructor
public class GeneralEndpoint {

    private static final String NAMESPACE_URI = "http://uws.provider.com/";

    private final PaymentServiceFactory paymentServiceFactory;

    @CheckCredential
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PerformTransactionArguments")
    public JAXBElement<PerformTransactionResult> performTransaction(@RequestPayload JAXBElement<PerformTransactionArguments> performTransactionArguments) {
        PaymentService service = paymentServiceFactory.getService(String.valueOf(performTransactionArguments.getValue().getServiceId()));
        return createJaxbElement(service.performTransaction(performTransactionArguments.getValue()), PerformTransactionResult.class);
    }

    @CheckCredential
    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetInformationArguments")
    public JAXBElement<GetInformationResult> getInformation(@RequestPayload JAXBElement<GetInformationArguments> informationArguments) {
        PaymentService service = paymentServiceFactory.getService(String.valueOf(informationArguments.getValue().getServiceId()));
        return createJaxbElement(service.getInformation(informationArguments.getValue()), GetInformationResult.class);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckTransactionArguments")
    public JAXBElement<CheckTransactionResult> checkTransaction(@RequestPayload JAXBElement<CheckTransactionArguments> checkTransactionArguments) {
        return createJaxbElement(new CheckTransactionResult(), CheckTransactionResult.class);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CancelTransactionArguments")
    public JAXBElement<CancelTransactionResult> cancelTransaction(@RequestPayload JAXBElement<CancelTransactionArguments> cancelTransactionArguments) {
        return createJaxbElement(new CancelTransactionResult(), CancelTransactionResult.class);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStatementArguments")
    public JAXBElement<GetStatementResult> getStatement(@RequestPayload JAXBElement<GetStatementArguments> getStatementArguments) {
        return createJaxbElement(new GetStatementResult(), GetStatementResult.class);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangePasswordArguments")
    public JAXBElement<ChangePasswordResult> changePassword(@RequestPayload JAXBElement<ChangePasswordArguments> changePasswordArguments) {
        return createJaxbElement(new ChangePasswordResult(), ChangePasswordResult.class);
    }



    private <T> JAXBElement<T> createJaxbElement(T object, Class<T> clazz) {
        return new JAXBElement<>(new QName(clazz.getSimpleName()), clazz, object);
    }

}
