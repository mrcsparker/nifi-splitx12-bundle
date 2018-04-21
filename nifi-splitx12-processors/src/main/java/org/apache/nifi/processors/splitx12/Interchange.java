package org.apache.nifi.processors.splitx12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This class is essentially a struct.  No getters and setters needed.
 * Just load the data
 */
class Interchange {
    private final String segmentSeparator;
    private final String elementSeparator;

    String isa;
    String gs;
    String ge;
    String iea;
    final List<Transaction> transactions = new ArrayList<>();

    Interchange(Character interchangeSegmentSeparator, Character interchangeElementSeparator) {
        segmentSeparator = interchangeSegmentSeparator.toString();
        elementSeparator = interchangeElementSeparator.toString();
    }

    private static String generateFileName(String elementSeparator, String isa, String gs, String st) {
        String[] isaElements = isa.split(Pattern.quote(elementSeparator));
        String[] gsElements = gs.split(Pattern.quote(elementSeparator));
        String[] stElements = st.split(Pattern.quote(elementSeparator));

        String result = String.format(
                "%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s.%s",
                isaElements[6].trim(),
                isaElements[8].trim(),
                isaElements[12].trim(),
                isaElements[13].trim(),

                gsElements[1].trim(),
                gsElements[2].trim(),
                gsElements[3].trim(),
                gsElements[4].trim(),
                gsElements[5].trim(),
                gsElements[8].trim(),

                stElements[2].trim(),
                stElements[1].trim()
        );

        return result.replace(" ", "_");
    }

    Map<String, String> writer() {

        Map<String, String> results = new HashMap<>();

        transactions.forEach(t -> {

            StringBuilder result = new StringBuilder();
            String fileName = generateFileName(elementSeparator, isa, gs, t.st);

            result.append(isa).append(segmentSeparator);
            result.append(gs).append(segmentSeparator);

            t.segments.forEach(s -> result.append(s).append(segmentSeparator));

            result.append(ge).append(segmentSeparator);
            result.append(iea).append(segmentSeparator);

            results.put(fileName, result.toString());
        });

        return results;
    }
}
