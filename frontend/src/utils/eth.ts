import { ethers } from "ethers";

type ChainData = {
  chainName: string,
  rpcUrl: string,
  blockExplorerUrl: string,
  nativeCurrency: string,
  nativeCurrencyDecimals: number
}

const chains = new Map<number, ChainData>([
  [80001, {
    chainName: 'Polygon Mumbai',
    rpcUrl: 'https://rpc-mumbai.matic.today',
    blockExplorerUrl: 'https://rpc-mumbai.matic.today',
    nativeCurrency: 'MATIC',
    nativeCurrencyDecimals: 18,
  }]
]);

export const switchNetwork = async (provider: ethers.providers.Web3Provider, networkId: number) => {
  if (!provider.provider.request) {
    return
  }

  const chainId = ethers.utils.hexlify(networkId);
  try {
    await provider.provider.request({
      method: "wallet_switchEthereumChain",
      params: [{ chainId: chainId }]
    });
  } catch (error: any) {
    const chainData = chains.get(networkId);
    if (error.code === 4902 && chainData) {
      await provider.provider.request({
        method: "wallet_addEthereumChain",
        params: [{
          chainId: chainId,
          chainName: chainData.chainName,
          rpcUrls: [chainData.rpcUrl],
          blockExplorerUrls: [chainData.blockExplorerUrl],
          nativeCurrency: {
            symbol: chainData.nativeCurrency,
            decimals: chainData.nativeCurrencyDecimals
          }
        }]
      });
    }
    throw error;
  }
}