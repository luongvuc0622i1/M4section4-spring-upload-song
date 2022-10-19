package com.codegym.service;

import com.codegym.model.File;

import java.util.List;

public interface IFileService {
    List<File> findAll();

    void save(File file);

    File findById(int id);

    void update(int id, File file);

    void remove(int id);
}
