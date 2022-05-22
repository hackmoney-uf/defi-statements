import { useContext, useEffect, useState } from "react";
import { CommonContext } from "../contexts/CommonContext";
import { Box, Link, Spinner, Table, TableContainer, Tbody, Td, Text, Th, Thead, Tr, VStack } from "@chakra-ui/react";
import { IPFS_LINK_PREFIX } from "../utils/constants";
import { ethers } from "ethers";
import { Statements } from "../typechain";

type Statement = {
  from: Date,
  to: Date,
  processed: boolean,
  link: string
}

const StatementsList = () => {
  const { statementsContract, account } = useContext(CommonContext);

  const [statements, setStatements] = useState<Statement[]>([]);
  const [statementsLoading, setStatementsLoading] = useState<boolean>(false);

  useEffect(() => {
    if (!statementsContract || !account) {
      setStatements([]);
      return;
    }

    const generateLink = (hexCid: string) => {
      return IPFS_LINK_PREFIX + new TextDecoder().decode(ethers.utils.arrayify(hexCid));
    }

    const toStatement = (s: any) => {
      return {
        from: new Date(s.from.toNumber()),
        to: new Date(s.to.toNumber()),
        processed: s.processed,
        link: s.processed ? generateLink(s.cid) : ''
      }
    }

    const loadStatements = async (statementsContract: Statements) => {
      const statements = await statementsContract.allRequestsFor(account);
      setStatements(statements.map(s => toStatement(s)).reverse())
    }

    setStatementsLoading(true);
    loadStatements(statementsContract)
      .catch(console.log)
      .finally(() => setStatementsLoading(false));

    const statementsPooling = setInterval(() => {
      if (!statementsContract) {
        return;
      }

      loadStatements(statementsContract).catch(console.log);
    }, 1000);

    return () => clearTimeout(statementsPooling);
  }, [statementsContract, account]);


  const toTableRows = (statements: Statement[]) => {
    return statements.map((s, i) => {
      return (
        <Tr key={i}>
          <Td>{s.from.toLocaleDateString()}</Td>
          <Td>{s.to.toLocaleDateString()}</Td>
          <Td>{s.processed ? "Processed ✅" : "Pending ⌛"}</Td>
          <Td>{s.link ? <Link isExternal={true} color={'#747ccc'} href={s.link}>Open</Link> : ''}</Td>
        </Tr>
      );
    });
  }

  return (
    <Box display={"flex"} width={"100%"} paddingTop={100} paddingX={20}>
      <VStack width={"100%"}>
        <Text color='#747ccc' fontSize='2xl' fontWeight='bold'>Previous requests:</Text>
        {!account ?
          <Text color='#747ccc' fontSize='xl' fontWeight='bold'>Connect you wallet to see your requests</Text> :
          <TableContainer width={"80%"} overflowY={'auto'}>
            {statementsLoading ?
              <Spinner color='#747ccc'/> :
              <Table variant='striped'>
                <Thead>
                  <Tr>
                    <Th>From</Th>
                    <Th>To</Th>
                    <Th>State</Th>
                    <Th>Report</Th>
                  </Tr>
                </Thead>
                <Tbody>
                  {toTableRows(statements)}
                </Tbody>
              </Table>}
          </TableContainer>
        }
      </VStack>
    </Box>
  );
}

export default StatementsList;