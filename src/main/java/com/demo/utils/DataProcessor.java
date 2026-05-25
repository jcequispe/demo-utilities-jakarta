package com.demo.utils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * Utility wrapper for processing serialized data.
 *
 * This class provides methods that use Apache Commons Collections,
 * but does NOT declare it as a dependency. The dependency is provided
 * by the consuming project (demo-app-vulnerable) via the BOM.
 */
public class DataProcessor {

    /**
     * Deserialize binary data into an Object.
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
     * Process collection data (uses Apache Commons Collections).
     * This method is available because commons-collections is provided
     * by the consuming application, not by this utilities library.
     */
    public static String processCollection(Object collection) {
        return "Collection type: " + collection.getClass().getName();
    }

}
