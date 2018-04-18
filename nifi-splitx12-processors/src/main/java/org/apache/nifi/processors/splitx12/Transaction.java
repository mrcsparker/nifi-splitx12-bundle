package org.apache.nifi.processors.splitx12;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is essentially a struct.  No getters and setters needed.
 * Just load the data
 */

public class Transaction {
    public final String st;
    public final List<String> segments = new ArrayList<>();

    public Transaction(String transactionSt) {
        st = transactionSt;
    }
}
