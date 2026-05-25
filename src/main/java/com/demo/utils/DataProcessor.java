package com.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;

/**
 * Utility wrapper for processing serialized data.
 * Uses Apache Commons Collections for data transformation.
 */
public class DataProcessor {

    /**
     * Deserialize binary data into an Object.
     * VULNERABLE: Uses ObjectInputStream without validation,
     * allowing gadget chain execution if payload contains malicious serialized objects.
     *
     * @param data Binary serialized data
     * @return Deserialized object
     * @throws Exception if deserialization fails
     */
    public static Object deserializeData(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

    /**
     * Process collection data using Apache Commons Collections transformers.
     * This demonstrates the use of commons-collections in the utilities library.
     */
    public static String processCollection(Object collection) {
        Transformer[] transformers = new Transformer[0];
        ChainedTransformer chain = new ChainedTransformer(transformers);
        return "Collection type: " + collection.getClass().getName();
    }

}
