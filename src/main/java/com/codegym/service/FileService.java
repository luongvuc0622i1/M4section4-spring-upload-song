package com.codegym.service;

import com.codegym.model.File;

import java.util.ArrayList;
import java.util.List;

public class FileService implements IFileService {
    private List<File> files = new ArrayList<>();
    @Override
    public List<File> findAll() {
        return files;
    }

    @Override
    public void save(File file) {
        files.add(file);
    }

    @Override
    public File findById(int id) {
        return files.get(id);
    }

    @Override
    public void update(int id, File file) {
        for (File f : files) {
            if (f.getId() == id) {
                f = file;
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getId() == id) {
                files.remove(i);
                break;
            }
        }
    }
}
