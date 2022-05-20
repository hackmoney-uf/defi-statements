import "@nomiclabs/hardhat-ethers"
import "@nomiclabs/hardhat-waffle"

import { expect } from "chai";
import { ethers } from "hardhat";
import { Statements } from "../typechain";
import { SignerWithAddress } from "@nomiclabs/hardhat-ethers/signers";

describe("Statements", async () => {
  let appSigner: SignerWithAddress;
  let statements: Statements;

  beforeEach(async () => {
    appSigner = (await ethers.getSigners())[1];
    const Statements = await ethers.getContractFactory("Statements",);
    const localStatements = await Statements.deploy(appSigner.address) as Statements;
    statements = await localStatements.deployed();
  });

  it("should persist statement request", async () => {
    // given
    const from = 0;
    const to = 1000;

    // when
    const transaction = await statements.requestStatement(from, to,
      { value: ethers.utils.parseEther("1") });

    // then
    const account = (await ethers.getSigners())[0];
    expect(transaction).to.emit(statements, 'StatementRequest')
      .withArgs(account.address, 0);

    const request = await statements.requests(account.address, 0);
    expect(request.from).to.be.equal(from);
    expect(request.to).to.be.equal(to);
    expect(request.processed).to.be.false;
    expect(request.cid).to.be.equal("0x");
  });

  it("should revert if 'from' is greater than 'to'", async () => {
    // given
    const from = 1000;
    const to = 0;

    // then
    await expect(statements.requestStatement(from, to, { value: ethers.utils.parseEther("1") }))
      .revertedWith("'from' timestamp is greater then 'to' timestamp");
  });

  it("should revert if provided payment is too low", async () => {
    // given
    const from = 0;
    const to = 1000;

    // then
    await expect(statements.requestStatement(from, to, { value: ethers.utils.parseEther("0.5") }))
      .revertedWith("payment is too low");
  });

  it("should revert if markProcessed() is called not by app", async () => {
    // given
    const requestInitiator = (await ethers.getSigners())[0];
    const index = 0;
    const cid = ethers.utils.toUtf8Bytes("bafybeigdyrzt5sfp7udm7hu76uh7y26nf3efuylqabf3oclgtqy55fbzdi");

    // then
    await expect(statements.markProcessed(requestInitiator.address, index, cid))
      .revertedWith("caller is not an app");
  });

  it("should mark request processed and add cid", async () => {
    // given
    const requestInitiator = (await ethers.getSigners())[0];
    const from = 0;
    const to = 1000;

    await statements.requestStatement(from, to, { value: ethers.utils.parseEther("1") });

    const index = 0;
    const cid = "bafybeigdyrzt5sfp7udm7hu76uh7y26nf3efuylqabf3oclgtqy55fbzdi";
    const cidHex = ethers.utils.hexlify(ethers.utils.toUtf8Bytes(cid));

    // when
    await statements.connect(appSigner).markProcessed(requestInitiator.address, index, cidHex);

    // then
    const request = await statements.requests(requestInitiator.address, 0);
    expect(request.from).to.be.equal(from);
    expect(request.to).to.be.equal(to);
    expect(request.processed).to.be.true;
    expect(request.cid).to.be.equal(cidHex);
  });

  it("should fail to mark processed if no such request", async () => {
    // given
    const requestInitiator = (await ethers.getSigners())[0];
    const index = 0;
    const cid = ethers.utils.toUtf8Bytes("bafybeigdyrzt5sfp7udm7hu76uh7y26nf3efuylqabf3oclgtqy55fbzdi");

    // then
    await expect(statements.connect(appSigner).markProcessed(requestInitiator.address, index, cid)).to.be.reverted;
  });
});
