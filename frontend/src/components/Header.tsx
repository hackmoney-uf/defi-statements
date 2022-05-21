import { Box, Button, Center } from "@chakra-ui/react";
import Web3Modal from "web3modal";
import { ethers } from "ethers";
import { providerOptions } from "../utils/providerOptions";
import { useEffect, useState } from "react";

const Header = () => {

  const [web3Modal, setWeb3Modal] = useState<Web3Modal | undefined>();
  const [account, setAccount] = useState<string>("");

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
    const account = (await provider.listAccounts())[0];
    setAccount(account);
  }
  const disconnectWallet = async () => {
    if (!web3Modal) {
      console.log("no Web3Modal");
      return;
    }

    await web3Modal.clearCachedProvider();
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
          Logo here
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