import StatementsRequest from "../components/StatementsRequest";
import StatementsList from "../components/StatementsList";
import { Box, Center, Divider } from "@chakra-ui/react";

const Statements = () => {
  return (
    <>
      <Box display={"flex"}>
        <StatementsRequest/>
        <Center height='100%'>
          <Divider orientation='vertical'/>
        </Center>
        <StatementsList/>
      </Box>
    </>
  );
}

export default Statements;