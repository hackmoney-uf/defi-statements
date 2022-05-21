import { Box, Button, Center, Link } from "@chakra-ui/react";
import Web3Modal from "web3modal";
import { ethers } from "ethers";
import { providerOptions } from "../utils/providerOptions";
import { useContext, useEffect, useState } from "react";
import { useRouter } from "next/router";
import { CommonContext } from "../contexts/CommonContext";
import { MUMBAI_CHAIN_ID } from "../utils/constants";
import { switchNetwork } from "../utils/eth";

const Header = () => {

  const router = useRouter();
  const { setProvider, setAccount, account } = useContext(CommonContext);

  const [web3Modal, setWeb3Modal] = useState<Web3Modal | undefined>();

  useEffect(() => {
    if (!window) {
      return;
    }

    const web3Modal = new Web3Modal({ providerOptions });
    setWeb3Modal(web3Modal);
  }, []);

  const connectWallet = async () => {
    if (!web3Modal) {
      console.log("no Web3Modal");
      return;

    }
    const instance = await web3Modal.connect();
    const provider = new ethers.providers.Web3Provider(instance);
    const network = await provider.getNetwork();
    if (network.chainId != MUMBAI_CHAIN_ID) {
      if (instance.isWalletConnect) {
        alert("Please connect to Polygon Mumbai network");
        return;
      }

      await switchNetwork(provider, MUMBAI_CHAIN_ID);
    }

    setProvider(new ethers.providers.Web3Provider(instance));

    const account = (await provider.listAccounts())[0];
    setAccount(account);
  }
  const disconnectWallet = async () => {
    if (!web3Modal) {
      console.log("no Web3Modal");
      return;
    }

    await web3Modal.clearCachedProvider();
    setProvider(undefined);
    setAccount('');
  }

  return (
    <>
      <Box
        display="flex"
        justifyContent={"space-between"}
        px="10%"
        py="20px"
        borderBottomWidth="1px"
      >
        <Center>
          <Link onClick={() => router.push("/")}>
            Logo here
          </Link>
        </Center>
        <Center textAlign="right">
          {account ? <Center>{account}</Center> : ''}
          <Center w={'13rem'}>
            {
              !account ?
                <Button onClick={connectWallet} textAlign="center">Connect wallet</Button> :
                <Button onClick={disconnectWallet} textAlign="center">Disconnect wallet</Button>
            }
          </Center>
        </Center>
      </Box>
    </>
  );
}

export default Header;