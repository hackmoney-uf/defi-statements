import * as dotenv from "dotenv";

import { HardhatUserConfig } from "hardhat/config";
import "@nomiclabs/hardhat-etherscan";
import "@nomiclabs/hardhat-waffle";
import "@typechain/hardhat";

dotenv.config();

const ALCHEMY_API_KEY = process.env.ALCHEMY_API_KEY;
const TESTNET_PRIVATE_KEY = process.env.TESTNET_PRIVATE_KEY;
const POLYGON_SCAN_API_KEY = process.env.POLYGON_SCAN_API_KEY;

const config: HardhatUserConfig = {
  solidity: "0.8.13",
  networks: {
    polygonMumbai: {
      url: `https://polygon-mumbai.g.alchemy.com/v2/${ALCHEMY_API_KEY}`,
      accounts: [`${TESTNET_PRIVATE_KEY}`]
    },
  },
  etherscan: {
    apiKey: {
      polygonMumbai: `${POLYGON_SCAN_API_KEY}`,
    }
  },
};

export default config;
