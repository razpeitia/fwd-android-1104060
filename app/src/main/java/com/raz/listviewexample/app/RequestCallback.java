package com.raz.listviewexample.app;

import java.util.List;

/**
 * Created by raz on 11/07/14.
 */
public interface RequestCallback {
    void onResults(List<Category> categoryList);
}
