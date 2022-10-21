package ua.goit.jdbc.repository;

import ua.goit.jdbc.model.dao.ProjectDao;

public interface Crud<T> {
    T create(T t);
    T readById(Integer recordNumber);
    T updateById(Integer projectId);

//    ProjectDao updateById(Integer projectId, String newProjectName);

    T deleteById();
}
