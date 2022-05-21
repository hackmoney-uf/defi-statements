package org.example.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

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

    public static final String BINARY = "608060405234801561001057600080fd5b50604051610ba5380380610ba583398101604081905261002f9161005d565b600180546001600160a01b039092166001600160a01b0319928316179055600280549091163317905561008d565b60006020828403121561006f57600080fd5b81516001600160a01b038116811461008657600080fd5b9392505050565b610b098061009c6000396000f3fe6080604052600436106100915760003560e01c80638710661a116100595780638710661a146101405780638a6f4226146101605780638da5cb5b146101735780639ecebe2a146101ab578063e72132d7146101db57600080fd5b80631b3ed722146100965780631c3b7814146100c05780635a073ca6146100ed5780636ef25c3a1461010f578063853828b61461012b575b600080fd5b3480156100a257600080fd5b506100ad6298968081565b6040519081526020015b60405180910390f35b3480156100cc57600080fd5b506100e06100db366004610826565b6101fb565b6040516100b7919061088e565b3480156100f957600080fd5b5061010d61010836600461091b565b61032f565b005b34801561011b57600080fd5b506100ad67016345785d8a000081565b34801561013757600080fd5b5061010d61043c565b34801561014c57600080fd5b506100ad61015b3660046109a2565b610478565b61010d61016e3660046109a2565b6104ab565b34801561017f57600080fd5b50600254610193906001600160a01b031681565b6040516001600160a01b0390911681526020016100b7565b3480156101b757600080fd5b506101cb6101c63660046109c4565b610628565b6040516100b794939291906109ee565b3480156101e757600080fd5b50600154610193906001600160a01b031681565b6001600160a01b038116600090815260208181526040808320805482518185028101850190935280835260609492939192909184015b82821015610324578382906000526020600020906004020160405180608001604052908160008201548152602001600182015481526020016002820160009054906101000a900460ff1615151515815260200160038201805461029390610a1f565b80601f01602080910402602001604051908101604052809291908181526020018280546102bf90610a1f565b801561030c5780601f106102e15761010080835404028352916020019161030c565b820191906000526020600020905b8154815290600101906020018083116102ef57829003601f168201915b50505050508152505081526020019060010190610231565b505050509050919050565b6001546001600160a01b031633146103855760405162461bcd60e51b8152602060048201526014602482015273063616c6c6572206973206e6f7420616e206170760641b60448201526064015b60405180910390fd5b6001600160a01b03841660009081526020819052604081208054859081106103af576103af610a59565b60009182526020909120600490910201600281015490915060ff16156104175760405162461bcd60e51b815260206004820152601b60248201527f63616e27742070726f6365737320726571756573742074776963650000000000604482015260640161037c565b60028101805460ff191660011790556104346003820184846106fd565b505050505050565b6002546040516001600160a01b03909116904780156108fc02916000818181858888f19350505050158015610475573d6000803e3d6000fd5b50565b6000629896806104888484610a85565b6104929190610a9c565b6104a49067016345785d8a0000610abb565b9392505050565b8082106105125760405162461bcd60e51b815260206004820152602f60248201527f2766726f6d272074696d657374616d702069732067726561746572207468656e60448201526e02027746f272074696d657374616d7608c1b606482015260840161037c565b61051c8282610478565b3410156105605760405162461bcd60e51b81526020600482015260126024820152717061796d656e7420697320746f6f206c6f7760701b604482015260640161037c565b3360009081526020818152604080832080548251608081018452928301858152606080850190815288855284860188815287875260018085018655948852968690208551600485029091019081559651938701939093555160028601805460ff191691151591909117905590518051919492938493926105e69260038501920190610781565b505060408051338152602081018590527faceb8d2c44d3717189bbb3ab6524fa0c31652725883788a5fc72e0f012b3b2cb92500160405180910390a150505050565b6000602052816000526040600020818154811061064457600080fd5b6000918252602090912060049091020180546001820154600283015460038401805493965091945060ff16929161067a90610a1f565b80601f01602080910402602001604051908101604052809291908181526020018280546106a690610a1f565b80156106f35780601f106106c8576101008083540402835291602001916106f3565b820191906000526020600020905b8154815290600101906020018083116106d657829003601f168201915b5050505050905084565b82805461070990610a1f565b90600052602060002090601f01602090048101928261072b5760008555610771565b82601f106107445782800160ff19823516178555610771565b82800160010185558215610771579182015b82811115610771578235825591602001919060010190610756565b5061077d9291506107f5565b5090565b82805461078d90610a1f565b90600052602060002090601f0160209004810192826107af5760008555610771565b82601f106107c857805160ff1916838001178555610771565b82800160010185558215610771579182015b828111156107715782518255916020019190600101906107da565b5b8082111561077d57600081556001016107f6565b80356001600160a01b038116811461082157600080fd5b919050565b60006020828403121561083857600080fd5b6104a48261080a565b6000815180845260005b818110156108675760208185018101518683018201520161084b565b81811115610879576000602083870101525b50601f01601f19169290920160200192915050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b8381101561090d57888303603f190185528151805184528781015188850152868101511515878501526060908101516080918501829052906108f981860183610841565b9689019694505050908601906001016108b5565b509098975050505050505050565b6000806000806060858703121561093157600080fd5b61093a8561080a565b935060208501359250604085013567ffffffffffffffff8082111561095e57600080fd5b818701915087601f83011261097257600080fd5b81358181111561098157600080fd5b88602082850101111561099357600080fd5b95989497505060200194505050565b600080604083850312156109b557600080fd5b50508035926020909101359150565b600080604083850312156109d757600080fd5b6109e08361080a565b946020939093013593505050565b8481528360208201528215156040820152608060608201526000610a156080830184610841565b9695505050505050565b600181811c90821680610a3357607f821691505b602082108103610a5357634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b600082821015610a9757610a97610a6f565b500390565b6000816000190483118215151615610ab657610ab6610a6f565b500290565b60008219821115610ace57610ace610a6f565b50019056fea264697066735822122092ff8040eab7b74cb0cb3eef92be99f76ac603773400708d763de355c9f0e89064736f6c634300080d0033";

    public static final String FUNC_ALLREQUESTSFOR = "allRequestsFor";

    public static final String FUNC_APPADDRESS = "appAddress";

    public static final String FUNC_BASEFEE = "baseFee";

    public static final String FUNC_MARKPROCESSED = "markProcessed";

    public static final String FUNC_MULTIPLIER = "multiplier";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REQUESTSTATEMENT = "requestStatement";

    public static final String FUNC_REQUESTS = "requests";

    public static final String FUNC_REQUIREDFEE = "requiredFee";

    public static final String FUNC_WITHDRAWALL = "withdrawAll";

    public static final Event STATEMENTREQUEST_EVENT = new Event("StatementRequest",
        Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
        }, new TypeReference<Uint256>() {
        }));
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

    public RemoteFunctionCall<BigInteger> requiredFee(BigInteger from, BigInteger to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_REQUIREDFEE,
            Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(from),
                new org.web3j.abi.datatypes.generated.Uint256(to)),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
            }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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
