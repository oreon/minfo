package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService  {
    
@Autowired
private PersonRepository personRepository;

public Person createPerson(Person person) {
return personRepository.save(person);
}

public Person getPerson(Long id) {
    return new Person();
//return personRepository.findOne(id);
}

public Person editPerson(Person person) {
return personRepository.save(person);
}

public void deletePerson(Person person) {
personRepository.delete(person);
}

public void deletePerson(Long id) {
//personRepository.delete(id);
}

public List<Person> getAllPersons(int pageNumber, int pageSize) {
return personRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent();
}

public List<Person> getAllPersons() {
return personRepository.findAll();
}
}