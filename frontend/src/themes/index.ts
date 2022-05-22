import { extendTheme } from '@chakra-ui/react'

const mainColor = '#747ccc';

export const theme = extendTheme({
  config: {
    initialColorMode: 'light',
    useSystemColorMode: false
  },
  styles: {
    global: {
      body: {
        bg: 'White',
      },
      'html, body, #__next': {
        height: "100%",
      },
      'main, main > div': {
        height: "80%",
      },
      ".blockRep-button": {
        fontWeight: 'bold',
        textTransform: 'uppercase',
        borderRadius: '2px',
        width: '12rem',
        border: '2px solid ' + mainColor,
        color: mainColor,
        background: '#FFF'
      }
    }
  },
  components: {},
})