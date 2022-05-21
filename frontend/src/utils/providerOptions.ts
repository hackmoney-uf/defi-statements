import WalletConnectProvider from "@walletconnect/web3-provider";
import CoinbaseWalletSDK from "@coinbase/wallet-sdk";

const infura = 'dfc96fa5f5a740dfbb804e243ca83e17';

export const providerOptions = {
  walletconnect: {
    package: WalletConnectProvider,
    options: {
      infuraId: infura,
      rpc: {
        80001: "https://polygon-mumbai.infura.io/v3/" + infura,
      },
      network: "mumbai",
    }
  },
  coinbasewallet: {
    package: CoinbaseWalletSDK,
    options: {
      appName: "DeFi Statements",
      infuraId: infura
    }
  }
};