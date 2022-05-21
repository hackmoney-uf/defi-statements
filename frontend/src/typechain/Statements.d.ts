/* Autogenerated file. Do not edit manually. */
/* tslint:disable */
/* eslint-disable */

import {
  ethers,
  EventFilter,
  Signer,
  BigNumber,
  BigNumberish,
  PopulatedTransaction,
  BaseContract,
  ContractTransaction,
  Overrides,
  PayableOverrides,
  CallOverrides,
} from "ethers";
import { BytesLike } from "@ethersproject/bytes";
import { Listener, Provider } from "@ethersproject/providers";
import { FunctionFragment, EventFragment, Result } from "@ethersproject/abi";
import type { TypedEventFilter, TypedEvent, TypedListener } from "./common";

interface StatementsInterface extends ethers.utils.Interface {
  functions: {
    "appAddress()": FunctionFragment;
    "baseFee()": FunctionFragment;
    "markProcessed(address,uint256,bytes)": FunctionFragment;
    "multiplier()": FunctionFragment;
    "owner()": FunctionFragment;
    "requestStatement(uint256,uint256)": FunctionFragment;
    "requests(address,uint256)": FunctionFragment;
    "withdrawAll()": FunctionFragment;
  };

  encodeFunctionData(
    functionFragment: "appAddress",
    values?: undefined
  ): string;
  encodeFunctionData(functionFragment: "baseFee", values?: undefined): string;
  encodeFunctionData(
    functionFragment: "markProcessed",
    values: [string, BigNumberish, BytesLike]
  ): string;
  encodeFunctionData(
    functionFragment: "multiplier",
    values?: undefined
  ): string;
  encodeFunctionData(functionFragment: "owner", values?: undefined): string;
  encodeFunctionData(
    functionFragment: "requestStatement",
    values: [BigNumberish, BigNumberish]
  ): string;
  encodeFunctionData(
    functionFragment: "requests",
    values: [string, BigNumberish]
  ): string;
  encodeFunctionData(
    functionFragment: "withdrawAll",
    values?: undefined
  ): string;

  decodeFunctionResult(functionFragment: "appAddress", data: BytesLike): Result;
  decodeFunctionResult(functionFragment: "baseFee", data: BytesLike): Result;
  decodeFunctionResult(
    functionFragment: "markProcessed",
    data: BytesLike
  ): Result;
  decodeFunctionResult(functionFragment: "multiplier", data: BytesLike): Result;
  decodeFunctionResult(functionFragment: "owner", data: BytesLike): Result;
  decodeFunctionResult(
    functionFragment: "requestStatement",
    data: BytesLike
  ): Result;
  decodeFunctionResult(functionFragment: "requests", data: BytesLike): Result;
  decodeFunctionResult(
    functionFragment: "withdrawAll",
    data: BytesLike
  ): Result;

  events: {
    "StatementRequest(address,uint256)": EventFragment;
  };

  getEvent(nameOrSignatureOrTopic: "StatementRequest"): EventFragment;
}

export type StatementRequestEvent = TypedEvent<
  [string, BigNumber] & { requestor: string; index: BigNumber }
>;

export class Statements extends BaseContract {
  connect(signerOrProvider: Signer | Provider | string): this;
  attach(addressOrName: string): this;
  deployed(): Promise<this>;

  listeners<EventArgsArray extends Array<any>, EventArgsObject>(
    eventFilter?: TypedEventFilter<EventArgsArray, EventArgsObject>
  ): Array<TypedListener<EventArgsArray, EventArgsObject>>;
  off<EventArgsArray extends Array<any>, EventArgsObject>(
    eventFilter: TypedEventFilter<EventArgsArray, EventArgsObject>,
    listener: TypedListener<EventArgsArray, EventArgsObject>
  ): this;
  on<EventArgsArray extends Array<any>, EventArgsObject>(
    eventFilter: TypedEventFilter<EventArgsArray, EventArgsObject>,
    listener: TypedListener<EventArgsArray, EventArgsObject>
  ): this;
  once<EventArgsArray extends Array<any>, EventArgsObject>(
    eventFilter: TypedEventFilter<EventArgsArray, EventArgsObject>,
    listener: TypedListener<EventArgsArray, EventArgsObject>
  ): this;
  removeListener<EventArgsArray extends Array<any>, EventArgsObject>(
    eventFilter: TypedEventFilter<EventArgsArray, EventArgsObject>,
    listener: TypedListener<EventArgsArray, EventArgsObject>
  ): this;
  removeAllListeners<EventArgsArray extends Array<any>, EventArgsObject>(
    eventFilter: TypedEventFilter<EventArgsArray, EventArgsObject>
  ): this;

  listeners(eventName?: string): Array<Listener>;
  off(eventName: string, listener: Listener): this;
  on(eventName: string, listener: Listener): this;
  once(eventName: string, listener: Listener): this;
  removeListener(eventName: string, listener: Listener): this;
  removeAllListeners(eventName?: string): this;

  queryFilter<EventArgsArray extends Array<any>, EventArgsObject>(
    event: TypedEventFilter<EventArgsArray, EventArgsObject>,
    fromBlockOrBlockhash?: string | number | undefined,
    toBlock?: string | number | undefined
  ): Promise<Array<TypedEvent<EventArgsArray & EventArgsObject>>>;

  interface: StatementsInterface;

  functions: {
    appAddress(overrides?: CallOverrides): Promise<[string]>;

    baseFee(overrides?: CallOverrides): Promise<[BigNumber]>;

    markProcessed(
      requestInitiator: string,
      index: BigNumberish,
      cid: BytesLike,
      overrides?: Overrides & { from?: string | Promise<string> }
    ): Promise<ContractTransaction>;

    multiplier(overrides?: CallOverrides): Promise<[BigNumber]>;

    owner(overrides?: CallOverrides): Promise<[string]>;

    requestStatement(
      from: BigNumberish,
      to: BigNumberish,
      overrides?: PayableOverrides & { from?: string | Promise<string> }
    ): Promise<ContractTransaction>;

    requests(
      arg0: string,
      arg1: BigNumberish,
      overrides?: CallOverrides
    ): Promise<
      [BigNumber, BigNumber, boolean, string] & {
        from: BigNumber;
        to: BigNumber;
        processed: boolean;
        cid: string;
      }
    >;

    withdrawAll(
      overrides?: Overrides & { from?: string | Promise<string> }
    ): Promise<ContractTransaction>;
  };

  appAddress(overrides?: CallOverrides): Promise<string>;

  baseFee(overrides?: CallOverrides): Promise<BigNumber>;

  markProcessed(
    requestInitiator: string,
    index: BigNumberish,
    cid: BytesLike,
    overrides?: Overrides & { from?: string | Promise<string> }
  ): Promise<ContractTransaction>;

  multiplier(overrides?: CallOverrides): Promise<BigNumber>;

  owner(overrides?: CallOverrides): Promise<string>;

  requestStatement(
    from: BigNumberish,
    to: BigNumberish,
    overrides?: PayableOverrides & { from?: string | Promise<string> }
  ): Promise<ContractTransaction>;

  requests(
    arg0: string,
    arg1: BigNumberish,
    overrides?: CallOverrides
  ): Promise<
    [BigNumber, BigNumber, boolean, string] & {
      from: BigNumber;
      to: BigNumber;
      processed: boolean;
      cid: string;
    }
  >;

  withdrawAll(
    overrides?: Overrides & { from?: string | Promise<string> }
  ): Promise<ContractTransaction>;

  callStatic: {
    appAddress(overrides?: CallOverrides): Promise<string>;

    baseFee(overrides?: CallOverrides): Promise<BigNumber>;

    markProcessed(
      requestInitiator: string,
      index: BigNumberish,
      cid: BytesLike,
      overrides?: CallOverrides
    ): Promise<void>;

    multiplier(overrides?: CallOverrides): Promise<BigNumber>;

    owner(overrides?: CallOverrides): Promise<string>;

    requestStatement(
      from: BigNumberish,
      to: BigNumberish,
      overrides?: CallOverrides
    ): Promise<void>;

    requests(
      arg0: string,
      arg1: BigNumberish,
      overrides?: CallOverrides
    ): Promise<
      [BigNumber, BigNumber, boolean, string] & {
        from: BigNumber;
        to: BigNumber;
        processed: boolean;
        cid: string;
      }
    >;

    withdrawAll(overrides?: CallOverrides): Promise<void>;
  };

  filters: {
    "StatementRequest(address,uint256)"(
      requestor?: null,
      index?: null
    ): TypedEventFilter<
      [string, BigNumber],
      { requestor: string; index: BigNumber }
    >;

    StatementRequest(
      requestor?: null,
      index?: null
    ): TypedEventFilter<
      [string, BigNumber],
      { requestor: string; index: BigNumber }
    >;
  };

  estimateGas: {
    appAddress(overrides?: CallOverrides): Promise<BigNumber>;

    baseFee(overrides?: CallOverrides): Promise<BigNumber>;

    markProcessed(
      requestInitiator: string,
      index: BigNumberish,
      cid: BytesLike,
      overrides?: Overrides & { from?: string | Promise<string> }
    ): Promise<BigNumber>;

    multiplier(overrides?: CallOverrides): Promise<BigNumber>;

    owner(overrides?: CallOverrides): Promise<BigNumber>;

    requestStatement(
      from: BigNumberish,
      to: BigNumberish,
      overrides?: PayableOverrides & { from?: string | Promise<string> }
    ): Promise<BigNumber>;

    requests(
      arg0: string,
      arg1: BigNumberish,
      overrides?: CallOverrides
    ): Promise<BigNumber>;

    withdrawAll(
      overrides?: Overrides & { from?: string | Promise<string> }
    ): Promise<BigNumber>;
  };

  populateTransaction: {
    appAddress(overrides?: CallOverrides): Promise<PopulatedTransaction>;

    baseFee(overrides?: CallOverrides): Promise<PopulatedTransaction>;

    markProcessed(
      requestInitiator: string,
      index: BigNumberish,
      cid: BytesLike,
      overrides?: Overrides & { from?: string | Promise<string> }
    ): Promise<PopulatedTransaction>;

    multiplier(overrides?: CallOverrides): Promise<PopulatedTransaction>;

    owner(overrides?: CallOverrides): Promise<PopulatedTransaction>;

    requestStatement(
      from: BigNumberish,
      to: BigNumberish,
      overrides?: PayableOverrides & { from?: string | Promise<string> }
    ): Promise<PopulatedTransaction>;

    requests(
      arg0: string,
      arg1: BigNumberish,
      overrides?: CallOverrides
    ): Promise<PopulatedTransaction>;

    withdrawAll(
      overrides?: Overrides & { from?: string | Promise<string> }
    ): Promise<PopulatedTransaction>;
  };
}