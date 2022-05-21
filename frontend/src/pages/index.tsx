import type { NextPage } from 'next'
import { Button, Center } from "@chakra-ui/react";
import { useRouter } from "next/router";

const Home: NextPage = () => {

  const router = useRouter();

  return (
    <Center>
      <Button onClick={() => router.push("/statements")}>Statements</Button>
    </Center>
  )
}

export default Home
