import { useContext, useEffect, useState } from "react";
import { CommonContext } from "../contexts/CommonContext";
import { Link, Table, TableContainer, Tbody, Td, Th, Thead, Tr } from "@chakra-ui/react";
import { IPFS_LINK_PREFIX } from "../utils/constants";
import { ethers } from "ethers";

type Statement = {
  from: Date,
  to: Date,
  processed: boolean,
  link: string
}

const StatementsList = () => {
  const { statementsContract, account } = useContext(CommonContext);

  const [statements, setStatements] = useState<Statement[]>([]);

  useEffect(() => {
    if (!statementsContract || !account) {
      return;
    }

    statementsContract.allRequestsFor(account)
      .then(it => {
        const result: Statement[] = [];
        it.map(s => result.push({
          from: new Date(s.from.toNumber()),
          to: new Date(s.to.toNumber()),
          processed: s.processed,
          link: s.processed ? generateLink(s.cid) : ''
        }));
        setStatements(result);
      })
      .catch(console.log);
  }, [statementsContract, account]);

  const generateLink = (hexCid: string) => {
    return IPFS_LINK_PREFIX + new TextDecoder().decode(ethers.utils.arrayify(hexCid));
  }

  return (
    <TableContainer>
      <Table variant='simple'>
        <Thead>
          <Tr>
            <Th>From</Th>
            <Th>To</Th>
            <Th>State</Th>
            <Th>Link</Th>
          </Tr>
        </Thead>
        <Tbody>
          {
            statements.map((s, i) => {
              return (
                <Tr key={i}>
                  <Td>{s.from.toLocaleDateString()}</Td>
                  <Td>{s.to.toLocaleDateString()}</Td>
                  <Td>{s.processed ? "Processed" : "Pending"}</Td>
                  <Td>
                    {s.link ? <Link href={s.link}>Link</Link> : ''}
                  </Td>
                </Tr>
              );
            })
          }

        </Tbody>
      </Table>
    </TableContainer>
  );
}

export default StatementsList;