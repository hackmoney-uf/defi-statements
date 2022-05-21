import WalletConnectProvider from "@walletconnect/web3-provider";
import CoinbaseWalletSDK from "@coinbase/wallet-sdk";

export const providerOptions = {
  walletconnect: {
    package: WalletConnectProvider,
    options: {
      infuraId: 'dfc96fa5f5a740dfbb804e243ca83e17',
    }
  },
  coinbasewallet: {
    package: CoinbaseWalletSDK,
    options: {
      appName: "DeFi Statements",
      infuraId: 'dfc96fa5f5a740dfbb804e243ca83e17',
    }
  }
};