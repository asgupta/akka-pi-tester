package com.godaddy.domains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:: agupta
 * Date:: 2/3/2015
 * Purpose:: how to iterate a list like List<Map<String,Object>>
 */
public class IterateHashMap {

    public boolean IterateOverHashMap(Map<String, Map<String, Object>> RegistryCache)
    {
        boolean successful = true;
 
        for(Map.Entry<String, Map<String, Object>> entry : RegistryCache.entrySet()) {
            String key = entry.getKey();
            System.out.println("Iteratirng for the key " + key);
            Map<String, Object> value = entry.getValue();
            for(Map.Entry<String, Object> entry1 : value.entrySet()) {
                String subkey = entry1.getKey();
                System.out.println("Iteratirng for the key " + subkey);
                Object keyValue = entry1.getValue();
                if (keyValue instanceof String) {
                    String strVal = (String) keyValue;
                    System.out.println("String :: " + strVal);
                } else if (keyValue instanceof Integer) {
                    Integer intVal = (Integer) keyValue;
                    System.out.println("Int :: " + intVal);
                } else if (keyValue instanceof ArrayList) {
                    ArrayList strVal = (ArrayList) keyValue;
                    System.out.println("List :: " + strVal.size());
                } else if (keyValue instanceof HashMap) {
                    HashMap strVal = (HashMap) keyValue;
                    System.out.println("Map :: " + strVal.size());
                } else {
                    System.out.println("Unknown key");
                    successful=false;
                    break;
                }
                
            }
            if(!successful)
                break;

        }
        return successful;
    }
}
