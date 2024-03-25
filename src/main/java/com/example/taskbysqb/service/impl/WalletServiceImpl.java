package com.example.taskbysqb.service.impl;

import com.example.taskbysqb.entity.WalletEntity;
import com.example.taskbysqb.repository.WalletEntityRepository;
import com.example.taskbysqb.service.PaymentService;
import com.example.taskbysqb.service.TransactionServiceImpl;
import com.example.taskbysqb.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import wsdl.GenericParam;
import wsdl.GetInformationArguments;
import wsdl.GetInformationResult;
import wsdl.PerformTransactionArguments;
import wsdl.PerformTransactionResult;

import javax.xml.datatype.DatatypeFactory;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static com.example.taskbysqb.service.impl.WalletServiceImpl.*;

/**
 * Created on 25/03/2024.
 *
 * @author Javokhir Jaloliddinov
 */
@Service(value = BEAN_ID)
@RequiredArgsConstructor
public class WalletServiceImpl implements PaymentService {
    public static final String BEAN_ID = "1";
    private final WalletEntityRepository walletEntityRepository;
    private final TransactionServiceImpl transactionService;


    @Override
    public PerformTransactionResult performTransaction(PerformTransactionArguments arguments) {
        WalletEntity wallet = getWallet(arguments.getParameters());
        Long successTransactionId = transactionService.createSuccessTransaction(arguments);
        WalletEntity topped = topUpWallet(arguments.getAmount(), wallet);
        return mapSuccessPerformTransactionResponse(topped, successTransactionId);
    }

    @Override
    @SneakyThrows
    public GetInformationResult getInformation(GetInformationArguments arguments) {
        WalletEntity wallet = getWallet(arguments.getParameters());

        GetInformationResult result = new GetInformationResult();
        result.setStatus(1);
        result.setTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(Instant.now().toString()));
        result.getParameters().addAll(List.of(
                generateGenericParam("balance", wallet.getBalance().toString()),
                generateGenericParam("name", wallet.getOwnerFullName())

        ));
        return result;
    }

    private static GenericParam generateGenericParam(String key, String value) {
        GenericParam genericParam = new GenericParam();
        genericParam.setKey(key);
        genericParam.setValue(value);
        return genericParam;
    }

    private WalletEntity getWallet(List<GenericParam> parameters) {
        WalletEntity wallet;
        String account = Utils.getValueByKey(parameters, "account");
        if (Objects.nonNull(account)) {
            isValidLuhn(account);
            wallet = walletEntityRepository.findByAccount(account).orElseThrow(() -> new RuntimeException("Wallet is not found"));
        } else {
            String phone = Objects.requireNonNull(Utils.getValueByKey(parameters, "phone"));
            wallet = walletEntityRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("Wallet is not found"));
        }
        return wallet;
    }

    @SneakyThrows
    private PerformTransactionResult mapSuccessPerformTransactionResponse(WalletEntity entity, Long transactionId) {
        PerformTransactionResult result = new PerformTransactionResult();
        result.setProviderTrnId(transactionId);
        result.setTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(Instant.now().toString()));
        result.setStatus(1);

        result.getParameters().addAll(List.of(
                generateGenericParam("account", entity.getAccount()),
                generateGenericParam("balance", entity.getBalance().toString())
        ));
        return result;
    }

    public WalletEntity topUpWallet(Long amount, WalletEntity wallet) {
        wallet.setBalance(wallet.getBalance() + amount);
        return walletEntityRepository.save(wallet);
    }

    private void isValidLuhn(String account) {
        int sum = 0;
        boolean alternate = false;
        for (int i = account.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(account.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        boolean isValid = sum % 10 == 0;
        if (!isValid)
            throw new RuntimeException("Wrong account number");
    }
}
