import StatementsRequest from "../components/StatementsRequest";
import StatementsList from "../components/StatementsList";
import { HStack } from "@chakra-ui/react";

const Statements = () => {
  return (
    <>
      <HStack>
        <StatementsRequest/>
        <StatementsList/>
      </HStack>
    </>
  );
}

export default Statements;