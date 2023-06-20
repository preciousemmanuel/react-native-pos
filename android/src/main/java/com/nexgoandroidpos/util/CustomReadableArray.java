package com.nexgoandroidpos.util;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import com.nexgoandroidpos.model.Item;
//import com.cicod.util.ReadableType;

public abstract class CustomReadableArray implements ReadableArray {
    private List<Item> mData = new ArrayList<>();

    public CustomReadableArray(List<Item> data) {
        mData = data;
    }

    //@Override
    public int size() {
        return mData.size();
    }

    //@Override
    public ReadableType getType(int index) {
        // You need to return the correct type for the data at the given index
        return ReadableType.String;
    }
    //@Override
    public boolean isNull(int index) {
        return mData.get(index) == null;
    }

    //@Override
    public double getDouble(int index) {
        // You need to handle conversion of the data at the given index to a double
        return 0.0;
    }
   // @Override
    public int getInt(int index) {
        // You need to handle conversion of the data at the given index to an int
        return 0;
    }
    //@Override
    public String getString(int index) {
        // You need to handle conversion of the data at the given index to a string
        return mData.get(index).toString();
    }
// ... other methods from the ReadableArray interface

    //@Override
    public boolean getBoolean(int index) {
        return false;
    }
    //@Override
    public ReadableArray getArray(int index) {
        WritableArray array = Arguments.createArray();
        array.pushString("value1");
        array.pushString("value2");
        return array;
    }
    //@Override
    public ReadableMap getMap(int index) {
        WritableMap map = Arguments.createMap();
        map.putString("key1", "value1");
        map.putString("key2", "value2");
        return map;
    }
//    @Override
//    public Dynamic getDynamic(int index) {
//        return Dynamic.from("0");
//    }

   // @Override
    public ArrayList<Object> toArrayList() {
        return new ArrayList<>(0);
    }
}
