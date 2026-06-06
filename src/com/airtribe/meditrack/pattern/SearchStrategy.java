package com.airtribe.meditrack.pattern;

import java.util.List;

import com.airtribe.meditrack.entity.Person;

@FunctionalInterface
// This interface defines a strategy for searching doctors/patients based on different criteria
public interface SearchStrategy<T> {

    List<T> search(List<T> data, String query);

    static <T extends Person> SearchStrategy<T> byName() {
        return (data, query) -> data.stream().filter(p -> p.getName().toLowerCase().contains(query)).toList();
    }

    static <T extends Person> SearchStrategy<T> byId(){
        return (data, query) -> data.stream().filter(p -> p.getId().equalsIgnoreCase(query)).toList();
    }

    static <T extends Person> SearchStrategy<T> byAge(int min, int max){
        return (data, query) -> data.stream().filter(p -> p.getAge() >= min && p.getAge() <= max).toList();
    }

    static <T extends Person> SearchStrategy<T> byPhone() {
        return (data, query) -> data.stream().filter(p -> p.getPhone().contains(query)).toList();
    }

    static <T extends Person> SearchStrategy<T> byEmail() {
        return (data, query) -> data.stream().filter(p -> p.getEmail().toLowerCase().contains(query)).toList();
    }

}