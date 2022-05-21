package org.example.covalent;

import org.example.model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Optional;

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
    void should_parse_erc20_transactions() throws URISyntaxException, IOException {
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
                .erc20Transaction(Optional.of(new Transaction.Erc20Transaction("0x99d59d73bad8be070fea364717400043490866c9", "0x1ee01f7a3425f00931b00aad20a5a3e559d4f404", "242488345102", "WETH", "0xa6fa4fb5f76172d178d61b04b0ecd319c5d1c0aa")))
                .build());
    }

}