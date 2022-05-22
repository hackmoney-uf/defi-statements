import '../styles/globals.css'
import type { AppProps } from 'next/app'
import { ChakraProvider } from '@chakra-ui/react'
import Layout from "../components/Layout";
import { CommonContextProvider } from "../contexts/CommonContext";
import { theme } from "../themes";
import { ToastContainer } from "react-toastify";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <ChakraProvider theme={theme}>
      <CommonContextProvider>
        <ToastContainer/>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </CommonContextProvider>
    </ChakraProvider>
  )
}

export default MyApp
