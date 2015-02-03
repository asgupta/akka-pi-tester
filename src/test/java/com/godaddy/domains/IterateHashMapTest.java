package com.godaddy.domains;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IterateHashMapTest extends TestCase {

    public void testIterateHashMap() throws Exception {
        IterateHashMap ite = new IterateHashMap();

        Map<String,Object> map = new HashMap<>();
        map.put("something", "something");
        map.put("int" , 212112);
        map.put("List", new ArrayList<String>());
        Map<String, Map<String, Object>> RegistryCache = new HashMap<>();
        RegistryCache.put("Uniregistry", map);

        assertTrue(ite.IterateOverHashMap(RegistryCache));
        
    }
}