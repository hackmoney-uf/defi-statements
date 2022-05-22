import type { NextPage } from 'next'
import { Button, Center, Text, VStack } from "@chakra-ui/react";
import { useRouter } from "next/router";

const Home: NextPage = () => {

  const router = useRouter();

  return (
    <>
      <Center>
        <VStack>
          <Text fontSize='5xl'
                bgGradient='linear(to-r, #4556ec, #a467ffff)'
                bgClip='text'
                fontWeight='extrabold'
          >
            On-chain transactions report generator
          </Text>
          <Text fontSize='2xl'
                paddingX={10}
                fontWeight='bold'
          >
            Generate an official summary of financial transactions occurring within a given period for each wallet you
            own.
          </Text>
          <Text fontSize='2xl'
                paddingX={10}
                paddingBottom={5}
          >
            All requested reports are delivered to you on-chain and always available for downland.
          </Text>
          <Button className={"blockRep-button"}
                  background={"white"}
                  width={"15rem"}
                  onClick={() => router.push("/statements")}>
            Generate My Report
          </Button>
        </VStack>
      </Center>
    </>
  )
}

export default Home
