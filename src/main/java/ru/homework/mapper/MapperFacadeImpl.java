package ru.homework.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import ru.homework.dto.DepartmentView;
import ru.homework.model.Department;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MapperFacadeImpl implements MapperFacade {

    private MapperFactory mapperFactory;

    @PostConstruct
    private void init(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }

    @Override
    public DepartmentView mapToDepartmentView(Department department, DepartmentView departmentView) {
        mapperFactory.getMapperFacade(Department.class, DepartmentView.class).map(department, departmentView);
        departmentView.setHeadDepartmentId(department.getHeadDepartment().getId());
        return departmentView;
    }
}