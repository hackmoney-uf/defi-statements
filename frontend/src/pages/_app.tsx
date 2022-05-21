import '../styles/globals.css'
import type { AppProps } from 'next/app'
import { ChakraProvider } from '@chakra-ui/react'
import Layout from "../components/Layout";
import { CommonContextProvider } from "../contexts/CommonContext";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ChakraProvider>
      <CommonContextProvider>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </CommonContextProvider>
    </ChakraProvider>
  )
}

export default MyApp
