/* Autogenerated file. Do not edit manually. */
/* tslint:disable */
/* eslint-disable */

import { Signer, utils, Contract, ContractFactory, Overrides } from "ethers";
import { Provider, TransactionRequest } from "@ethersproject/providers";
import type { Statements, StatementsInterface } from "../Statements";

const _abi = [
  {
    inputs: [
      {
        internalType: "address",
        name: "_appAddress",
        type: "address",
      },
    ],
    stateMutability: "nonpayable",
    type: "constructor",
  },
  {
    anonymous: false,
    inputs: [
      {
        indexed: false,
        internalType: "address",
        name: "requestor",
        type: "address",
      },
      {
        indexed: false,
        internalType: "uint256",
        name: "index",
        type: "uint256",
      },
    ],
    name: "StatementRequest",
    type: "event",
  },
  {
    inputs: [],
    name: "appAddress",
    outputs: [
      {
        internalType: "address",
        name: "",
        type: "address",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [],
    name: "baseFee",
    outputs: [
      {
        internalType: "uint256",
        name: "",
        type: "uint256",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "address",
        name: "requestInitiator",
        type: "address",
      },
      {
        internalType: "uint256",
        name: "index",
        type: "uint256",
      },
      {
        internalType: "bytes",
        name: "cid",
        type: "bytes",
      },
    ],
    name: "markProcessed",
    outputs: [],
    stateMutability: "nonpayable",
    type: "function",
  },
  {
    inputs: [],
    name: "multiplier",
    outputs: [
      {
        internalType: "uint256",
        name: "",
        type: "uint256",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [],
    name: "owner",
    outputs: [
      {
        internalType: "address",
        name: "",
        type: "address",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "uint256",
        name: "from",
        type: "uint256",
      },
      {
        internalType: "uint256",
        name: "to",
        type: "uint256",
      },
    ],
    name: "requestStatement",
    outputs: [],
    stateMutability: "payable",
    type: "function",
  },
  {
    inputs: [
      {
        internalType: "address",
        name: "",
        type: "address",
      },
      {
        internalType: "uint256",
        name: "",
        type: "uint256",
      },
    ],
    name: "requests",
    outputs: [
      {
        internalType: "uint256",
        name: "from",
        type: "uint256",
      },
      {
        internalType: "uint256",
        name: "to",
        type: "uint256",
      },
      {
        internalType: "bool",
        name: "processed",
        type: "bool",
      },
      {
        internalType: "bytes",
        name: "cid",
        type: "bytes",
      },
    ],
    stateMutability: "view",
    type: "function",
  },
  {
    inputs: [],
    name: "withdrawAll",
    outputs: [],
    stateMutability: "nonpayable",
    type: "function",
  },
];

const _bytecode =
  "0x60806040523480156200001157600080fd5b50604051620011003803806200110083398181016040528101906200003791906200012a565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550506200015c565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620000f282620000c5565b9050919050565b6200010481620000e5565b81146200011057600080fd5b50565b6000815190506200012481620000f9565b92915050565b600060208284031215620001435762000142620000c0565b5b6000620001538482850162000113565b91505092915050565b610f94806200016c6000396000f3fe60806040526004361061007b5760003560e01c80638a6f42261161004e5780638a6f4226146101165780638da5cb5b146101325780639ecebe2a1461015d578063e72132d71461019d5761007b565b80631b3ed722146100805780635a073ca6146100ab5780636ef25c3a146100d4578063853828b6146100ff575b600080fd5b34801561008c57600080fd5b506100956101c8565b6040516100a2919061087a565b60405180910390f35b3480156100b757600080fd5b506100d260048036038101906100cd919061098e565b6101cf565b005b3480156100e057600080fd5b506100e961034b565b6040516100f6919061087a565b60405180910390f35b34801561010b57600080fd5b50610114610357565b005b610130600480360381019061012b9190610a02565b6103c2565b005b34801561013e57600080fd5b506101476105e0565b6040516101549190610a51565b60405180910390f35b34801561016957600080fd5b50610184600480360381019061017f9190610a6c565b610606565b6040516101949493929190610b60565b60405180910390f35b3480156101a957600080fd5b506101b26106e8565b6040516101bf9190610a51565b60405180910390f35b6298968081565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461025f576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161025690610c09565b60405180910390fd5b60008060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002084815481106102b1576102b0610c29565b5b906000526020600020906004020190508060020160009054906101000a900460ff1615610313576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161030a90610ca4565b60405180910390fd5b60018160020160006101000a81548160ff021916908315150217905550828282600301919061034392919061070e565b505050505050565b67016345785d8a000081565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc479081150290604051600060405180830381858888f193505050501580156103bf573d6000803e3d6000fd5b50565b808210610404576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103fb90610d36565b60405180910390fd5b6298968082826104149190610d85565b61041e9190610db9565b67016345785d8a00006104319190610e13565b341015610473576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161046a90610eb5565b60405180910390fd5b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208054905090506104c1610794565b838160000181815250508281602001818152505060008160400190151590811515815250506000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819080600181540180825580915050600190039060005260206000209060040201600090919091909150600082015181600001556020820151816001015560408201518160020160006101000a81548160ff021916908315150217905550606082015181600301908051906020019061059e9291906107be565b5050507faceb8d2c44d3717189bbb3ab6524fa0c31652725883788a5fc72e0f012b3b2cb33836040516105d2929190610ed5565b60405180910390a150505050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000602052816000526040600020818154811061062257600080fd5b9060005260206000209060040201600091509150508060000154908060010154908060020160009054906101000a900460ff169080600301805461066590610f2d565b80601f016020809104026020016040519081016040528092919081815260200182805461069190610f2d565b80156106de5780601f106106b3576101008083540402835291602001916106de565b820191906000526020600020905b8154815290600101906020018083116106c157829003601f168201915b5050505050905084565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b82805461071a90610f2d565b90600052602060002090601f01602090048101928261073c5760008555610783565b82601f1061075557803560ff1916838001178555610783565b82800160010185558215610783579182015b82811115610782578235825591602001919060010190610767565b5b5090506107909190610844565b5090565b60405180608001604052806000815260200160008152602001600015158152602001606081525090565b8280546107ca90610f2d565b90600052602060002090601f0160209004810192826107ec5760008555610833565b82601f1061080557805160ff1916838001178555610833565b82800160010185558215610833579182015b82811115610832578251825591602001919060010190610817565b5b5090506108409190610844565b5090565b5b8082111561085d576000816000905550600101610845565b5090565b6000819050919050565b61087481610861565b82525050565b600060208201905061088f600083018461086b565b92915050565b600080fd5b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006108ca8261089f565b9050919050565b6108da816108bf565b81146108e557600080fd5b50565b6000813590506108f7816108d1565b92915050565b61090681610861565b811461091157600080fd5b50565b600081359050610923816108fd565b92915050565b600080fd5b600080fd5b600080fd5b60008083601f84011261094e5761094d610929565b5b8235905067ffffffffffffffff81111561096b5761096a61092e565b5b60208301915083600182028301111561098757610986610933565b5b9250929050565b600080600080606085870312156109a8576109a7610895565b5b60006109b6878288016108e8565b94505060206109c787828801610914565b935050604085013567ffffffffffffffff8111156109e8576109e761089a565b5b6109f487828801610938565b925092505092959194509250565b60008060408385031215610a1957610a18610895565b5b6000610a2785828601610914565b9250506020610a3885828601610914565b9150509250929050565b610a4b816108bf565b82525050565b6000602082019050610a666000830184610a42565b92915050565b60008060408385031215610a8357610a82610895565b5b6000610a91858286016108e8565b9250506020610aa285828601610914565b9150509250929050565b60008115159050919050565b610ac181610aac565b82525050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610b01578082015181840152602081019050610ae6565b83811115610b10576000848401525b50505050565b6000601f19601f8301169050919050565b6000610b3282610ac7565b610b3c8185610ad2565b9350610b4c818560208601610ae3565b610b5581610b16565b840191505092915050565b6000608082019050610b75600083018761086b565b610b82602083018661086b565b610b8f6040830185610ab8565b8181036060830152610ba18184610b27565b905095945050505050565b600082825260208201905092915050565b7f63616c6c6572206973206e6f7420616e20617070000000000000000000000000600082015250565b6000610bf3601483610bac565b9150610bfe82610bbd565b602082019050919050565b60006020820190508181036000830152610c2281610be6565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f63616e27742070726f6365737320726571756573742074776963650000000000600082015250565b6000610c8e601b83610bac565b9150610c9982610c58565b602082019050919050565b60006020820190508181036000830152610cbd81610c81565b9050919050565b7f2766726f6d272074696d657374616d702069732067726561746572207468656e60008201527f2027746f272074696d657374616d700000000000000000000000000000000000602082015250565b6000610d20602f83610bac565b9150610d2b82610cc4565b604082019050919050565b60006020820190508181036000830152610d4f81610d13565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610d9082610861565b9150610d9b83610861565b925082821015610dae57610dad610d56565b5b828203905092915050565b6000610dc482610861565b9150610dcf83610861565b9250817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0483118215151615610e0857610e07610d56565b5b828202905092915050565b6000610e1e82610861565b9150610e2983610861565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115610e5e57610e5d610d56565b5b828201905092915050565b7f7061796d656e7420697320746f6f206c6f770000000000000000000000000000600082015250565b6000610e9f601283610bac565b9150610eaa82610e69565b602082019050919050565b60006020820190508181036000830152610ece81610e92565b9050919050565b6000604082019050610eea6000830185610a42565b610ef7602083018461086b565b9392505050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610f4557607f821691505b602082108103610f5857610f57610efe565b5b5091905056fea26469706673582212209fc93cf91b28aa5882b086f629a201958c24006905aab0a4a86b4e6813c08fac64736f6c634300080d0033";

export class Statements__factory extends ContractFactory {
  constructor(
    ...args: [signer: Signer] | ConstructorParameters<typeof ContractFactory>
  ) {
    if (args.length === 1) {
      super(_abi, _bytecode, args[0]);
    } else {
      super(...args);
    }
  }

  deploy(
    _appAddress: string,
    overrides?: Overrides & { from?: string | Promise<string> }
  ): Promise<Statements> {
    return super.deploy(_appAddress, overrides || {}) as Promise<Statements>;
  }
  getDeployTransaction(
    _appAddress: string,
    overrides?: Overrides & { from?: string | Promise<string> }
  ): TransactionRequest {
    return super.getDeployTransaction(_appAddress, overrides || {});
  }
  attach(address: string): Statements {
    return super.attach(address) as Statements;
  }
  connect(signer: Signer): Statements__factory {
    return super.connect(signer) as Statements__factory;
  }
  static readonly bytecode = _bytecode;
  static readonly abi = _abi;
  static createInterface(): StatementsInterface {
    return new utils.Interface(_abi) as StatementsInterface;
  }
  static connect(
    address: string,
    signerOrProvider: Signer | Provider
  ): Statements {
    return new Contract(address, _abi, signerOrProvider) as Statements;
  }
}
