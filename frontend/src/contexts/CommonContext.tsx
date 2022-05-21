import { createContext, useMemo, useState } from "react";
import { ethers } from "ethers";
import { Statements, Statements__factory } from "../typechain";
import { STATEMENTS_CONTRACT_ADDRESS } from "../utils/constants";

export interface Context {
  provider: ethers.providers.Web3Provider | undefined,
  setProvider: (p: ethers.providers.Web3Provider | undefined) => void,
  account: string,
  setAccount: (a: string) => void,
  statementsContract: Statements | undefined
}

export const CommonContext = createContext<Context>({
  provider: undefined,
  setProvider: (p) => {
  },
  account: '',
  setAccount: (a) => {
  },
  statementsContract: undefined
});

export const CommonContextProvider = ({ children }: any) => {
  const [account, setAccount] = useState<string>('');
  const [provider, setProvider] = useState<ethers.providers.Web3Provider | undefined>();

  const statementsContract = useMemo(() => {
    if (!provider) {
      return;
    }

    console.log('Creating Statements contract');
    return new ethers.Contract(STATEMENTS_CONTRACT_ADDRESS, Statements__factory.abi, provider.getSigner(0)) as Statements
  }, [provider]);

  return (
    <CommonContext.Provider value={{
      provider,
      setProvider,
      account,
      setAccount,
      statementsContract
    }}>
      {children}
    </CommonContext.Provider>
  );
};