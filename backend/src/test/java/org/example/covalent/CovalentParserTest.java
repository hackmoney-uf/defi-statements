package org.example.covalent;

import org.example.model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;

import static java.nio.file.Files.readString;
import static java.nio.file.Paths.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.model.Transaction.Builder.transaction;
import static org.junit.jupiter.api.Assertions.*;

class CovalentParserTest {

    @Test
    void should_parse_native_tokens_transactions() throws URISyntaxException, IOException {
        // given
        final var input = readString(get(CovalentParserTest.class.getResource("/covalentResponse-native-tokens.json").toURI()));

        // when
        final var result = CovalentParser.parseTransactions(input);

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

}