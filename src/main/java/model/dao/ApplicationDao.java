package model.dao;

import model.entity.Application;

import java.util.List;

public interface ApplicationDao extends GenericDao<Application, Integer>{
    List<Application> getEntityByUserId(int id);
}
