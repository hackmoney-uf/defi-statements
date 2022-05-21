package org.example.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Statements extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060405161094238038061094283398101604081905261002f9161005d565b600180546001600160a01b039092166001600160a01b0319928316179055600280549091163317905561008d565b60006020828403121561006f57600080fd5b81516001600160a01b038116811461008657600080fd5b9392505050565b6108a68061009c6000396000f3fe60806040526004361061007b5760003560e01c80638a6f42261161004e5780638a6f4226146100fd5780638da5cb5b146101105780639ecebe2a14610148578063e72132d71461017857600080fd5b80631b3ed722146100805780635a073ca6146100aa5780636ef25c3a146100cc578063853828b6146100e8575b600080fd5b34801561008c57600080fd5b506100976298968081565b6040519081526020015b60405180910390f35b3480156100b657600080fd5b506100ca6100c536600461067c565b610198565b005b3480156100d857600080fd5b5061009767016345785d8a000081565b3480156100f457600080fd5b506100ca6102a5565b6100ca61010b366004610703565b6102e1565b34801561011c57600080fd5b50600254610130906001600160a01b031681565b6040516001600160a01b0390911681526020016100a1565b34801561015457600080fd5b50610168610163366004610725565b61047e565b6040516100a1949392919061074f565b34801561018457600080fd5b50600154610130906001600160a01b031681565b6001546001600160a01b031633146101ee5760405162461bcd60e51b8152602060048201526014602482015273063616c6c6572206973206e6f7420616e206170760641b60448201526064015b60405180910390fd5b6001600160a01b0384166000908152602081905260408120805485908110610218576102186107bc565b60009182526020909120600490910201600281015490915060ff16156102805760405162461bcd60e51b815260206004820152601b60248201527f63616e27742070726f636573732072657175657374207477696365000000000060448201526064016101e5565b60028101805460ff1916600117905561029d600382018484610553565b505050505050565b6002546040516001600160a01b03909116904780156108fc02916000818181858888f193505050501580156102de573d6000803e3d6000fd5b50565b8082106103485760405162461bcd60e51b815260206004820152602f60248201527f2766726f6d272074696d657374616d702069732067726561746572207468656e60448201526e02027746f272074696d657374616d7608c1b60648201526084016101e5565b6298968061035683836107e8565b61036091906107ff565b6103729067016345785d8a000061081e565b3410156103b65760405162461bcd60e51b81526020600482015260126024820152717061796d656e7420697320746f6f206c6f7760701b60448201526064016101e5565b3360009081526020818152604080832080548251608081018452928301858152606080850190815288855284860188815287875260018085018655948852968690208551600485029091019081559651938701939093555160028601805460ff1916911515919091179055905180519194929384939261043c92600385019201906105d7565b505060408051338152602081018590527faceb8d2c44d3717189bbb3ab6524fa0c31652725883788a5fc72e0f012b3b2cb92500160405180910390a150505050565b6000602052816000526040600020818154811061049a57600080fd5b6000918252602090912060049091020180546001820154600283015460038401805493965091945060ff1692916104d090610836565b80601f01602080910402602001604051908101604052809291908181526020018280546104fc90610836565b80156105495780601f1061051e57610100808354040283529160200191610549565b820191906000526020600020905b81548152906001019060200180831161052c57829003601f168201915b5050505050905084565b82805461055f90610836565b90600052602060002090601f01602090048101928261058157600085556105c7565b82601f1061059a5782800160ff198235161785556105c7565b828001600101855582156105c7579182015b828111156105c75782358255916020019190600101906105ac565b506105d392915061064b565b5090565b8280546105e390610836565b90600052602060002090601f01602090048101928261060557600085556105c7565b82601f1061061e57805160ff19168380011785556105c7565b828001600101855582156105c7579182015b828111156105c7578251825591602001919060010190610630565b5b808211156105d3576000815560010161064c565b80356001600160a01b038116811461067757600080fd5b919050565b6000806000806060858703121561069257600080fd5b61069b85610660565b935060208501359250604085013567ffffffffffffffff808211156106bf57600080fd5b818701915087601f8301126106d357600080fd5b8135818111156106e257600080fd5b8860208285010111156106f457600080fd5b95989497505060200194505050565b6000806040838503121561071657600080fd5b50508035926020909101359150565b6000806040838503121561073857600080fd5b61074183610660565b946020939093013593505050565b848152600060208581840152841515604084015260806060840152835180608085015260005b818110156107915785810183015185820160a001528201610775565b818111156107a357600060a083870101525b50601f01601f19169290920160a0019695505050505050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b6000828210156107fa576107fa6107d2565b500390565b6000816000190483118215151615610819576108196107d2565b500290565b60008219821115610831576108316107d2565b500190565b600181811c9082168061084a57607f821691505b60208210810361086a57634e487b7160e01b600052602260045260246000fd5b5091905056fea26469706673582212203f74569991d734009f3f98a6a8fac7c5f1c5830114435e2ee400cf76f17c022d64736f6c634300080d0033";

    public static final String FUNC_APPADDRESS = "appAddress";

    public static final String FUNC_BASEFEE = "baseFee";

    public static final String FUNC_MARKPROCESSED = "markProcessed";

    public static final String FUNC_MULTIPLIER = "multiplier";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REQUESTSTATEMENT = "requestStatement";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_WITHDRAWALL = "withdrawAll";

    public static final Event STATEMENTREQUEST_EVENT = new Event("StatementRequest", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Statements(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Statements(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Statements(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Statements(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<StatementRequestEventResponse> getStatementRequestEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STATEMENTREQUEST_EVENT, transactionReceipt);
        ArrayList<StatementRequestEventResponse> responses = new ArrayList<StatementRequestEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StatementRequestEventResponse typedResponse = new StatementRequestEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.requestor = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<StatementRequestEventResponse> statementRequestEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, StatementRequestEventResponse>() {
            @Override
            public StatementRequestEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STATEMENTREQUEST_EVENT, log);
                StatementRequestEventResponse typedResponse = new StatementRequestEventResponse();
                typedResponse.log = log;
                typedResponse.requestor = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<StatementRequestEventResponse> statementRequestEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STATEMENTREQUEST_EVENT));
        return statementRequestEventFlowable(filter);
    }

    public RemoteFunctionCall<String> appAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_APPADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> baseFee() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BASEFEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> markProcessed(String requestInitiator, BigInteger index, byte[] cid) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MARKPROCESSED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, requestInitiator), 
                new org.web3j.abi.datatypes.generated.Uint256(index), 
                new org.web3j.abi.datatypes.DynamicBytes(cid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> multiplier() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_MULTIPLIER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> requestStatement(BigInteger from, BigInteger to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REQUESTSTATEMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(from), 
                new org.web3j.abi.datatypes.generated.Uint256(to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, Boolean, byte[]>> requests(String param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REQUESTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<DynamicBytes>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, Boolean, byte[]>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, Boolean, byte[]>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, Boolean, byte[]> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, Boolean, byte[]>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (Boolean) results.get(2).getValue(), 
                                (byte[]) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawAll() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWALL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Statements load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Statements(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Statements load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Statements(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Statements load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Statements(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Statements load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Statements(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Statements> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _appAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _appAddress)));
        return deployRemoteCall(Statements.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Statements> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _appAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _appAddress)));
        return deployRemoteCall(Statements.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Statements> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _appAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _appAddress)));
        return deployRemoteCall(Statements.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Statements> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _appAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _appAddress)));
        return deployRemoteCall(Statements.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class StatementRequestEventResponse extends BaseEventResponse {
        public String requestor;

        public BigInteger index;
    }
}
