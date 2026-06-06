package com.airtribe.meditrack.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DataStore<T> {
    private final List<T> dataStorage = new ArrayList<>();

    public void addData(T data){
        dataStorage.add(data);
    }

    public boolean removeData(T data){
        return dataStorage.remove(data);
    }


    public Optional<T> findFirst(Predicate<T> p){
        return dataStorage.stream().filter(p).findFirst();
    }

    public List<T> findAll(Predicate<T> p){
        return dataStorage.stream().filter(p).toList();
    }

    public List<T> getAllData(){
        return new ArrayList<>(dataStorage);
    }
}