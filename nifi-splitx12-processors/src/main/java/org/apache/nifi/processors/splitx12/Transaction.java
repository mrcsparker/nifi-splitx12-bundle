package org.apache.nifi.processors.splitx12;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is essentially a struct.  No getters and setters needed.
 * Just load the data
 */

class Transaction {
    final String st;
    final List<String> segments = new ArrayList<>();

    Transaction(String transactionSt) {
        st = transactionSt;
    }
}
