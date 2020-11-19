package com.solutionia.flexerp.dao;

import java.util.List;

public interface IDataAccessObject<T> {
    public T find(Integer id);
    public Integer create(T entity);
    public List<T> listAll();
    public  Integer delete(Integer id);
    public  Integer update(T product);
}
