import { ethers } from "hardhat";
import * as dotenv from "dotenv";

dotenv.config();

const APP_ADDRESS = process.env.APP_ADDRESS;

const deploy = async () => {
  const Statements = await ethers.getContractFactory("Statements");
  const statements = await Statements.deploy(`${APP_ADDRESS}`);

  await statements.deployed();

  console.log("Statements contract deployed to:", statements.address);
}

deploy()
  .catch((error) => {
    console.error(error);
    process.exitCode = 1;
  });
