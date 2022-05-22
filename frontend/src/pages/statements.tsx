import StatementsRequest from "../components/StatementsRequest";
import StatementsList from "../components/StatementsList";
import { Box } from "@chakra-ui/react";

const Statements = () => {
  return (
    <>
      <Box display={"flex"}>
        <StatementsRequest/>
        <StatementsList/>
      </Box>
    </>
  );
}

export default Statements;