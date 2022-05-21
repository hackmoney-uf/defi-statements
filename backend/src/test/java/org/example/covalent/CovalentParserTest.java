package org.example.covalent;

import org.example.model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;

import static java.nio.file.Files.readString;
import static java.nio.file.Paths.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.model.Transaction.Builder.transaction;

class CovalentParserTest {

    @Test
    void should_parse_native_tokens_transactions() throws URISyntaxException, IOException {
        // given
        final var input = readString(get(CovalentParserTest.class.getResource("/covalentResponse-native-tokens.json").toURI()));

        // when
        final var result = CovalentParser.parseTransactions(input, "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404");

        // then
        assertThat(result).containsExactly(
            transaction()
                .hash("0x72edbf76c093c91de2f22b1c55fa12ba7bb3e5268c242f0165de1ca31b3fb76d")
                .from("0xb23360ccdd9ed1b15d45e5d3824bb409c8d7c267")
                .to("0x1ee01F7a3425f00931B00aAd20A5A3E559D4f404")
                .value("201200000000000000")
                .valueQuote(BigDecimal.valueOf(562.7212177734375))
                .feesPaid("734000000000000")
                .signedAt(ZonedDateTime.parse("2022-05-07T07:38:29Z"))
                .build(),
            transaction()
                .hash("0x894444d5bc83c9a15ed77ec03888d4130381672b94d38a42d85ce13ae9ab12ca")
                .from("0x1ee01F7a3425f00931B00aAd20A5A3E559D4f404")
                .to("0xba17eeb3f0413b76184ba8ed73067063fba6e3eb")
                .value("4000000000000000")
                .valueQuote(BigDecimal.valueOf(9.4073154296875))
                .feesPaid("1161237170356000")
                .signedAt(ZonedDateTime.parse("2022-04-29T14:24:05Z"))
                .build());
    }

    @Test
    void should_parse_native_to_erc20_transactions() throws URISyntaxException, IOException {
        // given
        final var input = readString(get(CovalentParserTest.class.getResource("/covalentResponse-native-to-erc20.json").toURI()));

        // when
        final var result = CovalentParser.parseTransactions(input, "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404");

        // then
        assertThat(result).containsExactly(
            transaction()
                .hash("0xa487f35f32e8cf8ca1b62abe7abca708c2ec3a06822f16a20de0184a4c18b7bf")
                .from("0x1ee01f7a3425f00931b00aad20a5a3e559d4f404")
                .to("0x68b3465833fb72a70ecdf485e0e4c7bd8665fc45")
                .value("100000000000000000")
                .valueQuote(BigDecimal.valueOf(0.06559156775474549))
                .feesPaid("248828453299233")
                .signedAt(ZonedDateTime.parse("2022-05-20T15:02:48Z"))
                .erc20Transactions(List.of(
                    new Transaction.Erc20Transaction("0x99d59d73bad8be070fea364717400043490866c9", "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404", "242488345102", "WETH", "0xa6fa4fb5f76172d178d61b04b0ecd319c5d1c0aa")))
                .build(),
            transaction()
                .hash("0x167b7b03cdec2dc2379cdffdfccf5fae1106085633926bd79f72cd4d7e19fde2")
                .from("0x1ee01f7a3425f00931b00aad20a5a3e559d4f404")
                .to("0x8954afa98594b838bda56fe4c12a09d7739d179b")
                .value("2584369926905016792")
                .valueQuote(BigDecimal.valueOf(1.7046556914407023))
                .feesPaid("2392263844940476")
                .signedAt(ZonedDateTime.parse("2022-05-20T19:58:39Z"))
                .erc20Transactions(List.of(
                    new Transaction.Erc20Transaction("0x3a6ed047db6543b646474028afcfed1d39b9b06a", "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404", "2000000", "USDT", "0x3813e82e6f7098b9583fc0f33a962d02018b6803")))
                .build());
    }

    @Test
    void should_parse_erc20_to_erc20_transactions() throws URISyntaxException, IOException {
        // given
        final var input = readString(get(CovalentParserTest.class.getResource("/covalentResponse-erc20-to-erc20.json").toURI()));

        // when
        final var result = CovalentParser.parseTransactions(input, "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404");

        // then
        assertThat(result).containsExactly(
            transaction()
                .hash("0x6a2bd6c307c0162a68d137cef82afe173016dc98515e3eb973d96a017c16545a")
                .from("0x1ee01f7a3425f00931b00aad20a5a3e559d4f404")
                .to("0x8954afa98594b838bda56fe4c12a09d7739d179b")
                .value("0")
                .valueQuote(BigDecimal.valueOf(0.0))
                .feesPaid("2088662757333000")
                .signedAt(ZonedDateTime.parse("2022-05-21T13:28:04Z"))
                .erc20Transactions(List.of(
                    new Transaction.Erc20Transaction("0xc0ec4271d306f0ea4a70298c0243ea59a58bfd7f", "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404", "1391518415626272670", "DAI", "0xcb1e72786a6eb3b44c2a2429e317c8a2462cfeb1"),
                    new Transaction.Erc20Transaction("0x1ee01f7a3425f00931b00aad20a5a3e559d4f404", "0x3a6ed047db6543b646474028afcfed1d39b9b06a", "3000000", "USDT", "0x3813e82e6f7098b9583fc0f33a962d02018b6803")))
                .build());
    }

    @Test
    void should_parse_mixed_transaction_from_long_log() throws IOException, URISyntaxException {
        // given
        final var input = readString(get(CovalentParserTest.class.getResource("/covalentResponse-all.json").toURI()));

        // when
        final var result = CovalentParser.parseTransactions(input, "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404");

        // then
        assertThat(result).hasSize(19);
    }

}