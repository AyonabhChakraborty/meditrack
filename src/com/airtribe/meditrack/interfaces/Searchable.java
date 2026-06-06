package com.airtribe.meditrack.interfaces;

import java.util.List;

public interface Searchable<T> {
    T searchById(String id);
    List<T> searchByName(String name);
    List<T> searchByAge(int age);


    default void printSearchResult(List<T> results){
        if(results==null || results.isEmpty()){
            System.out.println("No results found.");
        } else {
            System.out.println("Search Results:");
            for(T item : results){
                System.out.println("-> " + item);
            }
        }
    }
}