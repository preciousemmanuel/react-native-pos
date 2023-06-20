package com.nexgoandroidpos.util;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.List;
import com.nexgoandroidpos.model.Item;

import com.facebook.proguard.annotations.DoNotStrip;

/** Defines the type of an object stored in a {@link ReadableArray} or {@link ReadableMap}. */
@DoNotStrip
public enum ReadableType {
    Null,
    Boolean,
    Number,
    String,
    Map,
    Array,
    Dynamic,
    Item
}
