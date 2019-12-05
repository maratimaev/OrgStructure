package ru.homework.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;
import ru.homework.dto.DepartmentView;
import ru.homework.dto.EmployeeView;
import ru.homework.model.Department;
import ru.homework.model.Employee;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MapperFacadeImpl implements MapperFacade {

    private MapperFactory mapperFactory;

    @PostConstruct
    private void init(){
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Department.class, DepartmentView.class)
                .customize(new CustomMapper<Department, DepartmentView>() {
                    @Override
                    public void mapAtoB(Department department, DepartmentView departmentView, MappingContext mappingContext) {
                        if (department.getHeadDepartment() != null) {
                            departmentView.setHeadDepartmentId(department.getHeadDepartment().getId());
                        }
                    }
                })
                .byDefault()
                .register();
        mapperFactory.classMap(Employee.class, EmployeeView.class)
                .customize(new CustomMapper<Employee, EmployeeView>() {
                    @Override
                    public void mapAtoB(Employee employee, EmployeeView employeeView, MappingContext mappingContext) {
                        if (employee.getPosition() != null) {
                            employeeView.setPositionId(employee.getPosition().getId());
                        }
                        if (employee.getDepartment() != null) {
                            employeeView.setDepartmentId(employee.getDepartment().getId());
                        }
                    }
                })
                .byDefault()
                .register();
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
}