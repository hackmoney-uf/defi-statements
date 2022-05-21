import { useContext, useState } from "react";
import { Button, Flex, Text, VStack } from "@chakra-ui/react";
import { RangeDatepicker } from "chakra-dayzed-datepicker";
import { CommonContext } from "../contexts/CommonContext";

const StatementsRequest = () => {

  const { statementsContract } = useContext(CommonContext);
  const [selectedDates, setSelectedDates] = useState<Date[]>([new Date(), new Date()]);

  const requestStatement = async () => {
    if (!statementsContract) {
      alert('no contract');
      return;
    }

    console.log('Request statement', 'From:', selectedDates[0], 'To:', selectedDates[1]);

    const from = selectedDates[0].getTime();
    const to = selectedDates[1].getTime();

    const value = await statementsContract.requiredFee(from, to);
    const result = await statementsContract.requestStatement(from, to,
      { value: value }
    );

    await result.wait(1);
    console.log('result', result);
  }

  return (
    <Flex width="50%" align="center" justifyContent="center">
      <VStack width={'30%'} align="left">
        <Text>Dates:</Text>
        <RangeDatepicker
          selectedDates={selectedDates}
          onDateChange={setSelectedDates}
        />
        <Button width="full" mt={6} onClick={requestStatement}>Request statement</Button>
      </VStack>
    </Flex>
  );
}

export default StatementsRequest;