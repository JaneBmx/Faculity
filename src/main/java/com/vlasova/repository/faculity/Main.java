package com.vlasova.repository.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.repository.user.UserRepository;
import com.vlasova.specification.faculity.FindAllFaculties;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        FacultyRepository repository = FacultyRepositoryImpl.getInstance();
        try {
            Set<Faculty> faculties = repository.query(new FindAllFaculties());

            for(Faculty faculty:faculties){
                System.out.println(faculty);
            }
        } catch (RepositoryException e) {
            System.out.println("Жопа");
        }

    }
}
