import { useContext, useEffect, useState } from "react";
import { Button, Center, Text, VStack } from "@chakra-ui/react";
import { RangeDatepicker } from "chakra-dayzed-datepicker";
import { CommonContext } from "../contexts/CommonContext";
import { ethers } from "ethers";
import { toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

const StatementsRequest = () => {

  const { statementsContract } = useContext(CommonContext);
  const startDate = new Date();
  startDate.setDate(0);
  const [selectedDates, setSelectedDates] = useState<Date[]>([startDate, new Date()]);
  const [price, setPrice] = useState<string>('');
  const [requestInProgress, setRequestInProgress] = useState<boolean>(false);

  useEffect(() => {
    if (!statementsContract || selectedDates.length != 2) {
      setPrice('N/A');
      return;
    }

    const from = selectedDates[0].getTime();
    const to = selectedDates[1].getTime();
    statementsContract.requiredFee(from, to)
      .then(it => {
        setPrice(ethers.utils.formatUnits(it.toString(), "ether") + "MATIC");
      })
      .catch(console.log);
  }, [selectedDates, statementsContract]);

  const requestStatement = async () => {
    if (!statementsContract) {
      alert('no contract');
      return;
    }

    setRequestInProgress(true);
    console.log('Request statement', 'From:', selectedDates[0], 'To:', selectedDates[1]);

    const from = selectedDates[0].getTime();
    const to = selectedDates[1].getTime();

    toast.promise(async () => {
      const value = await statementsContract.requiredFee(from, to);
      const result = await statementsContract.requestStatement(from, to,
        { value: value }
      );

      await result.wait(1);
      console.log('result', result);
    }, {
      pending: 'Transaction in progress',
      success: 'Transaction successfully submitted',
      error: 'Transaction failed'
    }).finally(() => setRequestInProgress(false));
  }

  return (
    <Center width={"100%"}>
      <VStack width={'40%'} align="left">
        <Text color='#747ccc' fontSize='2xl' fontWeight='bold'>Dates:</Text>
        <RangeDatepicker
          selectedDates={selectedDates}
          onDateChange={setSelectedDates}
          disabled={!statementsContract || requestInProgress}
        />
        <Text color='#747ccc'>Report price: {price}</Text>
        <Button className={"blockRep-button"}
                background={"white"}
                width="full"
                mt={6}
                onClick={requestStatement}
                disabled={!statementsContract}
                isLoading={requestInProgress}
        >
          Request statement
        </Button>
      </VStack>
    </Center>
  );
}

export default StatementsRequest;